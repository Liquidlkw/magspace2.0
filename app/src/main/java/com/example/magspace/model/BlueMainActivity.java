package com.example.magspace.model;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.magspace.R;
import com.example.magspace.ui.BleActivity;
import com.example.magspace.ui.BlueMazeActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class BlueMainActivity extends AppCompatActivity implements OnClickListener {
    //小车进入状态判断
    public static String state = "";
    // 扫描蓝牙按钮
    private Button scan_btn;
    private Button back2;
    // 蓝牙适配器
    BluetoothAdapter mBluetoothAdapter;
    // 蓝牙信号强度
    public ArrayList<Integer> rssis;
    // 自定义Adapter
    public LeDeviceListAdapter mleDeviceListAdapter;
    // listview显示扫描到的蓝牙信息
    ListView lv;
    // 描述扫描蓝牙的状态
    private boolean mScanning;
    private boolean scan_flag;
    int REQUEST_ENABLE_BT = 1;
    // 蓝牙扫描时间
    private static final long SCAN_PERIOD = 4000;


    private final Handler mHandler = new MyHandler(this);

    private static final String TAG = "BlueMainActivity";

    private static class MyHandler extends Handler {
        private final WeakReference<BlueMainActivity> mWeakReference;

        public MyHandler(BlueMainActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BlueMainActivity activity = mWeakReference.get();
            /* 将扫描到设备的信息输出到listview的适配器 */
            activity.mScanning = false;
            activity.scan_flag = true;
            activity.scan_btn.setText("扫描蓝牙小车");
            Log.i("SCAN", "stop.....................");
            activity.mBluetoothAdapter.stopLeScan(activity.mLeScanCallback);

        }
    }


    /**
     * 蓝牙扫描回调函数 实现扫描蓝牙设备，回调蓝牙BluetoothDevice，可以获取name MAC等信息
     **/
    private final BluetoothAdapter.LeScanCallback mLeScanCallback = new MyLeScanCallback(this);


    private static final class MyLeScanCallback implements BluetoothAdapter.LeScanCallback {

        private final WeakReference<BlueMainActivity> activity;

        public MyLeScanCallback(BlueMainActivity act) {
            activity = new WeakReference<>(act);
        }

        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            final BlueMainActivity act = activity.get();
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    /* 将扫描到设备的信息输出到listview的适配器 */
                    act.mleDeviceListAdapter.addDevice(device, rssi);
                    act.mleDeviceListAdapter.notifyDataSetChanged();
                }
            });

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_main);
        //从BluechoiceActivity中获得传送过来的state
        state = getIntent().getStringExtra("state");
        Log.d("blue-text", "传送来的state是：" + state);
        //申请位置权限
        initPermissions();
        // 初始化控件
        init();
        // 初始化蓝牙
        initBle();
        scan_flag = true;
        // 自定义适配器
        mleDeviceListAdapter = new LeDeviceListAdapter();
        lv.setAdapter(mleDeviceListAdapter);

        /* listview点击函数 */
        lv.setOnItemClickListener((arg0, v, position, id) -> {
            final BluetoothDevice device = mleDeviceListAdapter.getDevice(position);
            if (device == null) {
                return;
            }

            Intent intent;
            Log.d("blue-text", "BlueMainActivity启动了");
            if (state.equals("control_car")) {
                intent = new Intent(BlueMainActivity.this, BleActivity.class);
                intent.putExtra(BleActivity.EXTRAS_DEVICE_NAME, device.getName());
                intent.putExtra(BleActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());
                intent.putExtra(BleActivity.EXTRAS_DEVICE_RSSI, rssis.get(position).toString());
            } else {
                intent = new Intent(BlueMainActivity.this, BlueMazeActivity.class);
                intent.putExtra(BlueMazeActivity.EXTRAS_DEVICE_NAME, device.getName());
                intent.putExtra(BlueMazeActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());
                intent.putExtra(BlueMazeActivity.EXTRAS_DEVICE_RSSI, rssis.get(position).toString());
            }

            if (mScanning) {
                /* 停止扫描设备 */
                mBluetoothAdapter.stopLeScan(mLeScanCallback);
                mScanning = false;
            }

            try {
                // 启动Ble_Activity或者Blue_maze_Activity
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: handle exception
            }

        });

//        scan_btn.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                startActivity(new Intent(BlueMainActivity.this, DebugActivity.class));
//                return true;
//            }
//        });

    }


    /**
     * @param
     * @return void
     * @throws
     * @Title: init
     * @Description: TODO(初始化UI控件)
     */
    private void init() {
        scan_btn = (Button) this.findViewById(R.id.scan_dev_btn);
        scan_btn.setOnClickListener(this);
        lv = (ListView) this.findViewById(R.id.lv);
        back2 = (Button) this.findViewById(R.id.back2);

        back2.setOnClickListener(view -> {
            finish();
            Log.d("blue-text", "结束BlueMainactivity");
        });
    }

    /**
     * @param
     * @return void
     * @throws
     * @Title: init_ble
     * @Description: TODO(初始化蓝牙)
     */
    private void initBle() {
        // Initializes Bluetooth adapter.
        // 获取手机本地的蓝牙适配器
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        // 打开蓝牙权限
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

    }

    /*
     * 扫描蓝牙按钮点击事件
     */
    @Override
    public void onClick(View v) {
        if (scan_flag) {
            mleDeviceListAdapter = new LeDeviceListAdapter();
            lv.setAdapter(mleDeviceListAdapter);
            scanLeDevice(true);
        } else {
            scanLeDevice(false);
            scan_btn.setText("扫描蓝牙小车");
        }
    }


    /**
     * @param enable (扫描使能，true:扫描开始,false:扫描停止)
     * @return void
     * @throws
     * @Title: scanLeDevice
     * @Description: TODO(扫描蓝牙设备)
     */
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            /* 开始扫描蓝牙设备，带mLeScanCallback 回调函数 */
            Log.i("SCAN", "begin.....................");
            mScanning = true;
            scan_flag = false;
            scan_btn.setText("停止扫描蓝牙小车");
            mBluetoothAdapter.startLeScan(mLeScanCallback);

            // Stops scanning after a pre-defined scan period.
            mHandler.sendEmptyMessageDelayed(0, SCAN_PERIOD);

        } else {
            Log.i("Stop", "stoping................");
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
            scan_flag = true;
        }

    }

    /**
     * @author 广州汇承信息科技有限公司
     * @Description: TODO<自定义适配器Adapter, 作为listview的适配器>
     * @data: 2014-10-12 上午10:46:30
     * @version: V1.0
     */
    private class LeDeviceListAdapter extends BaseAdapter {
        private final ArrayList<BluetoothDevice> mLeDevices;

        private LayoutInflater mInflator;

        public LeDeviceListAdapter() {
            super();
            rssis = new ArrayList<>();
            mLeDevices = new ArrayList<>();
            mInflator = getLayoutInflater();
        }

        public void addDevice(BluetoothDevice device, int rssi) {
            if (!mLeDevices.contains(device)) {
                mLeDevices.add(device);
                rssis.add(rssi);
            }
        }

        public BluetoothDevice getDevice(int position) {
            return mLeDevices.get(position);
        }

        public void clear() {
            mLeDevices.clear();
            rssis.clear();
        }

        @Override
        public int getCount() {
            return mLeDevices.size();
        }

        @Override
        public Object getItem(int i) {
            return mLeDevices.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        /**
         * 重写getview
         **/
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            // General ListView optimization code.
            // 加载listview每一项的视图
            view = mInflator.inflate(R.layout.item_ble, null);
            // 初始化三个textview显示蓝牙信息
            TextView deviceAddress = view.findViewById(R.id.tv_deviceAddr);
            TextView deviceName = view.findViewById(R.id.tv_deviceName);
            TextView rssi = view.findViewById(R.id.tv_rssi);

            BluetoothDevice device = mLeDevices.get(i);
            deviceAddress.setText(device.getAddress());
            deviceName.setText(device.getName());
            rssi.setText("" + rssis.get(i));

            return view;
        }
    }

    /**
     * 权限申请
     */
    private void initPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 获取wifi连接需要定位权限,没有获取权限
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_WIFI_STATE,
            }, 1);
            return;
        }
    }


    /*//打开设置面板
    private void startPale(){
        //设置权值，打开面板
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            startActivity(new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY));
        }
    }*/


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        scanLeDevice(false);

    }
}

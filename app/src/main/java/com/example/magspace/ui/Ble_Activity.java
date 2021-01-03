package com.example.magspace.ui;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.*;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import androidx.annotation.NonNull;
import com.example.magspace.R;
import com.example.magspace.model.BlueMainActivity;
import com.example.magspace.service.BluetoothLeService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.magspace.model.BlueMainActivity.*;

public class Ble_Activity extends BasActivity implements OnClickListener {

    private final static String TAG = Ble_Activity.class.getSimpleName();
    //蓝牙4.0的UUID,其中0000ffe1-0000-1000-8000-00805f9b34fb是广州汇承信息科技有限公司08蓝牙模块的UUID
    public static String HEART_RATE_MEASUREMENT = "0000ffe1-0000-1000-8000-00805f9b34fb";
    public static String EXTRAS_DEVICE_NAME = "DEVICE_NAME";;
    public static String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";
    public static String EXTRAS_DEVICE_RSSI = "RSSI";


    //蓝牙连接状态
    private boolean mConnected = false;
    private String status = "蓝牙连接小车成功";
    //蓝牙名字
    private String mDeviceName;
    //蓝牙地址
    private String mDeviceAddress;
    //蓝牙信号值
    private String mRssi;
    private Bundle b;
    private String rev_str = "";
    //蓝牙service,负责后台的蓝牙服务
    private static BluetoothLeService mBluetoothLeService;
    //文本框，显示接受的内容
    private TextView rev_tv, connect_state;
    //按钮
    private Button stop_btn;
    private Button come_btn;
    private Button left_btn;
    private Button right_btn;
    private Button back_btn;
    private Button tracing_btn;
    private Button maze_btn;
    private Button xun_btn;
    //文本编辑框
    private EditText send_et;
    private ScrollView rev_sv;
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();
    //蓝牙特征值
    private static BluetoothGattCharacteristic target_chara = null;
    public static  byte[] revDataForCharacteristic;
    private Handler mhandler = new Handler();
    private Handler myHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                // 判断发送的消息
                case 1:
                {
                    // 更新View
                    String state = msg.getData().getString("connect_state");
                    connect_state.setText(state);

                    break;
                }

            }

            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ble_activity);
        b = getIntent().getExtras();
        //从意图获取显示的蓝牙信息
        mDeviceName = b.getString(EXTRAS_DEVICE_NAME);
        mDeviceAddress = b.getString(EXTRAS_DEVICE_ADDRESS);
        mRssi = b.getString(EXTRAS_DEVICE_RSSI);

        /* 启动蓝牙service */
        Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        init();

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //解除广播接收器
        unregisterReceiver(mGattUpdateReceiver);
        mBluetoothLeService = null;
    }

    // Activity出来时候，绑定广播接收器，监听蓝牙连接服务传过来的事件
    @Override
    protected void onResume()
    {
        super.onResume();
        //绑定广播接收器
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothLeService != null)
        {
            //根据蓝牙地址，建立连接
            final boolean result = mBluetoothLeService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }
    }

    /**
     * @Title: init
     * @Description: TODO(初始化UI控件)
     * @param
     * @return void
     * @throws
     */
    private void init()
    {
        rev_sv = (ScrollView) this.findViewById(R.id.rev_sv);
        rev_tv = (TextView) this.findViewById(R.id.rev_tv);
        connect_state = (TextView) this.findViewById(R.id.connect_state);
        tracing_btn = (Button) this.findViewById(R.id.tracing_btn);
        maze_btn = (Button) this.findViewById(R.id.maze_btn);
        xun_btn = (Button) this.findViewById(R.id.xun_btn);
        come_btn = (Button) this.findViewById(R.id.come_bth);
        back_btn = (Button) this.findViewById(R.id.back_bth);
        left_btn = (Button) this.findViewById(R.id.left_bth);
        right_btn = (Button) this.findViewById(R.id.right_bth);
        stop_btn  = (Button) this.findViewById(R.id.stop_bth);
        //send_et = (EditText) this.findViewById(R.id.send_et);
        connect_state.setText(status);
        stop_btn.setOnClickListener(this);
        //返回按钮启动
        tracing_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                String data = "20";
//                new sendDataThread(data);
                //---蓝牙测试工具----
                String data = "0";
                new sendDataThread(data);
                finish();
                Log.d("blue-text", "tracing按钮启动了");
            }
        });
        xun_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                String data = "20";
//                new sendDataThread(data);
                //---蓝牙测试工具----
                String data = "6";
                new sendDataThread(data);
                Log.d("blue-text", "寻迹xun按钮启动了");
            }
        });
        //迷宫
        maze_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                String data = "5";
//                new sendDataThread(data);
                Log.d("blue-text", "寻迹功能");

            }
        });
        //前进
        come_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String data = "1";
                new sendDataThread(data);
                //---蓝牙测试工具----
                Log.d("blue-text", "come按钮启动了--发送一次"+data);
            }
        });
        //后退
        back_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String data = "2";
                new sendDataThread(data);
                //---蓝牙测试工具----
                Log.d("blue-text", "back按钮启动了--发送一次"+data);
            }
        });
        //左转
        left_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String data = "3";
                new sendDataThread(data);
                //---蓝牙测试工具----
                Log.d("blue-text", "left按钮启动了--发送一次"+data);
            }
        });
        //右转
        right_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String data = "4";
                new sendDataThread(data);
                //---蓝牙测试工具----
                Log.d("blue-text", "right按钮启动了--发送一次"+data);
            }
        });
//        stop_btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                String data = "10";
//                new sendDataThread(data);
//                //---蓝牙测试工具----
//                Log.d("blue-text", "stop按钮启动了--发送一次"+data);
//            }
//        });

    }

    /* BluetoothLeService绑定的回调函数 */
    private final ServiceConnection mServiceConnection = new ServiceConnection()
    {

        @Override
        public void onServiceConnected(ComponentName componentName,
                                       IBinder service)
        {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service)
                    .getService();
            if (!mBluetoothLeService.initialize())
            {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up
            // initialization.
            // 根据蓝牙地址，连接设备
            mBluetoothLeService.connect(mDeviceAddress);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {
            mBluetoothLeService = null;
        }

    };

    /**
     * 广播接收器，负责接收BluetoothLeService类发送的数据
     */
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action))//Gatt连接成功
            {
                mConnected = true;
                status = "connected";
                //更新连接状态
                updateConnectionState(status);
                System.out.println("BroadcastReceiver :" + "device connected");

            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED//Gatt连接失败
                    .equals(action))
            {
                mConnected = false;
                status = "disconnected";
                //更新连接状态
                updateConnectionState(status);
                System.out.println("BroadcastReceiver :"
                        + "device disconnected");

            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED//发现GATT服务器
                    .equals(action))
            {
                // Show all the supported services and characteristics on the
                // user interface.
                //获取设备的所有蓝牙服务
                displayGattServices(mBluetoothLeService
                        .getSupportedGattServices());
                System.out.println("BroadcastReceiver :"
                        + "device SERVICES_DISCOVERED");
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action))//有效数据
            {
                //处理发送过来的数据
                try {
                    if (intent.getExtras().getString(
                            BluetoothLeService.EXTRA_DATA)!=null) {
                        displayData(intent.getExtras().getString(
                                BluetoothLeService.EXTRA_DATA), intent);
                        System.out.println("BroadcastReceiver onData:"
                                + intent.getStringExtra(BluetoothLeService.EXTRA_DATA));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    };

    /* 更新连接状态 */
    private void updateConnectionState(String status)
    {
        Message msg = new Message();
        msg.what = 1;
        Bundle b = new Bundle();
        b.putString("connect_state", status);
        msg.setData(b);
        //将连接状态更新的UI的textview上
        myHandler.sendMessage(msg);
        System.out.println("connect_state:" + status);

    }

    /* 意图过滤器 */
    private static IntentFilter makeGattUpdateIntentFilter()
    {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter
                .addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    /**
     * @Title: displayData
     * @Description: TODO(接收到的数据在scrollview上显示)
     * @param @param rev_string(接受的数据)
     * @return void
     * @throws
     */
    private void displayData(String rev_string,Intent intent)
    {
        try {
            byte[] data = intent.getByteArrayExtra("BLE_BYTE_DATA");
            if(data==null)
                System.out.println("data is null!!!!!!");
            //GB2312编码
            rev_string = new String(data, 0, data.length, "GB2312");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        rev_str += rev_string;
        //更新UI
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                rev_tv.setText(rev_str);
                rev_sv.scrollTo(0, rev_tv.getMeasuredHeight());
                System.out.println("rev:" + rev_str);
            }
        });

    }
    //处理蓝牙接受的数据，

    /**
     * @Title: displayGattServices
     * @Description: TODO(处理蓝牙服务)
     * @param
     * @return void
     * @throws
     */
    private void displayGattServices(List<BluetoothGattService> gattServices)
    {

        if (gattServices == null)
            return;
        String uuid = null;
        String unknownServiceString = "unknown_service";
        String unknownCharaString = "unknown_characteristic";

        // 服务数据,可扩展下拉列表的第一级数据
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<HashMap<String, String>>();

        // 特征数据（隶属于某一级服务下面的特征值集合）
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData = new ArrayList<ArrayList<HashMap<String, String>>>();

        // 部分层次，所有特征值集合
        mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices)
        {

            // 获取服务列表
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();

            // 查表，根据该uuid获取对应的服务名称。SampleGattAttributes这个表需要自定义。

            gattServiceData.add(currentServiceData);

            System.out.println("Service uuid:" + uuid);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData = new ArrayList<HashMap<String, String>>();

            // 从当前循环所指向的服务中读取特征值列表
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService
                    .getCharacteristics();

            ArrayList<BluetoothGattCharacteristic> charas = new ArrayList<BluetoothGattCharacteristic>();

            // Loops through available Characteristics.
            // 对于当前循环所指向的服务中的每一个特征值
            for (final BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics)
            {
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();

                if (gattCharacteristic.getUuid().toString()
                        .equals(HEART_RATE_MEASUREMENT))
                {
                    // 测试读取当前Characteristic数据，会触发mOnDataAvailable.onCharacteristicRead()
                    mhandler.postDelayed(new Runnable()
                    {

                        @Override
                        public void run()
                        {
                            // TODO Auto-generated method stub
                            mBluetoothLeService
                                    .readCharacteristic(gattCharacteristic);
                        }
                    }, 200);

                    // 接受Characteristic被写的通知,收到蓝牙模块的数据后会触发mOnDataAvailable.onCharacteristicWrite()
                    mBluetoothLeService.setCharacteristicNotification(
                            gattCharacteristic, true);
                    target_chara = gattCharacteristic;
                    // 设置数据内容
                    // 往蓝牙模块写入数据
                    // mBluetoothLeService.writeCharacteristic(gattCharacteristic);
                }
                List<BluetoothGattDescriptor> descriptors = gattCharacteristic
                        .getDescriptors();
                for (BluetoothGattDescriptor descriptor : descriptors)
                {
                    System.out.println("---descriptor UUID:"
                            + descriptor.getUuid());
                    // 获取特征值的描述
                    mBluetoothLeService.getCharacteristicDescriptor(descriptor);
                    // mBluetoothLeService.setCharacteristicNotification(gattCharacteristic,
                    // true);
                }

                gattCharacteristicGroupData.add(currentCharaData);
            }
            // 按先后顺序，分层次放入特征值集合中，只有特征值
            mGattCharacteristics.add(charas);
            // 构件第二级扩展列表（服务下面的特征值）
            gattCharacteristicData.add(gattCharacteristicGroupData);

        }

    }
    /**
     * 将数据分包
     *
     * **/
    public int[] dataSeparate(int len)
    {
        int[] lens = new int[2];
        lens[0]=len/20;
        lens[1]=len%20;
        return lens;
    }

    /*
     * 数据发送线程
     *
     * */
    public class sendDataThread implements Runnable{
        public String Data;
              //----开启新线程---------
        public sendDataThread() {
            super();
            new Thread(this).start();
        }
            //----测试按钮数据发送---------
        public sendDataThread(String data) {
            super();
            this.Data = data;
            new Thread(this).start();
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub   ---TODO自动生成的方法存根
            byte[] buff =null;
            try {
                //buff =send_et.getText().toString().getBytes("GB2312");
                buff =Data.getBytes("GB2312");
                System.out.println("buff len:"+buff.length);
                Log.d("blue-text", "buff启动了字节转换");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block  --TODO自动生成的catch块
                e.printStackTrace();
            }
            int[] sendDatalens = dataSeparate(buff.length);
            for(int i=0;i<sendDatalens[0];i++)
            {
                byte[] dataFor20 = new byte[20];
                for(int j=0;j<20;j++)
                {
                    dataFor20[j]=buff[i*20+j];
                }
                System.out.println("here1");
                System.out.println("here1:"+new String(dataFor20));
                target_chara.setValue(dataFor20);
                mBluetoothLeService.writeCharacteristic(target_chara);
            }
            if(sendDatalens[1]!=0)
            {
                System.out.println("here2");
                byte[] lastData = new byte[20];
                for(int i=0;i<sendDatalens[1];i++)
                    lastData[i]=buff[sendDatalens[0]*20+i];
                String str=null;
                try {
                    str = new String(lastData, 0, sendDatalens[1],"GB2312");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("here2:"+str+"-------len:"+str.length());
                if (target_chara == null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Ble_Activity.this, "没建立连接，请检查设备...", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }
                target_chara.setValue(lastData);//   --->此行出空指针错误):
                mBluetoothLeService.writeCharacteristic(target_chara);
            }

        }


    }

    /*
     * 发送按键的响应事件，主要发送文本框的数据
     */
    @Override
    public void onClick(View v)
    {
        //String data = send_et.getText().toString();
        //new sendDataThread(data);
        //Log.d("blue-text", "send按钮启动了");
        String data = "0";
        new sendDataThread(data);
        //---蓝牙测试工具----
        Log.d("blue-text", "stop按钮启动了--发送一次"+data);
    }

}


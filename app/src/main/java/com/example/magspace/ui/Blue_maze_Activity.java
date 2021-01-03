package com.example.magspace.ui;

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
import com.example.magspace.service.BluetoothLeService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.magspace.model.BlueMainActivity.*;

public class Blue_maze_Activity extends BasActivity implements View.OnClickListener {
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
    private TextView route_tv, connect_state1;
    //文本编辑框
    private EditText send_et;
    private ScrollView route_sv;
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
                    String state = msg.getData().getString("connect_state1");
                    connect_state1.setText(state);

                    break;
                }

            }

            return false;
        }
    });
    //--------定义所有按钮控件---------------
    private Button forward_01_btn;
    private Button forward_02_btn;
    private Button back_01_btn;
    private Button right_01_btn;
    private Button left_01_btn;
    private Button right_forward_01_btn;
    private Button left_forward_01_btn;
    private Button right_back_01_btn;
    private Button left_back_01_btn;
    private Button clean_maze_btn;
    private Button start_maze_btn;
    private Button back1;
    //--------定义接收到的路线图数组---------------
    private String route[] = new String[50];
    //-------全局变量用于计数路线执行情况-------
    private int count = 0;
    //------定义开关按钮--------
    private int start = 0;
    //------定义清除按钮--------
    private int clear = 0;

    //route[count].equals("a") == true
    //遍历数组，返回数组的有效长度
    public int count_route(String data[]){
        int j = 0;
        for (String i:data
        ) {

            if(data[j] == null){
                break;
            }
            j+=1;
        }
        return j;
    }
    //----------点击按钮将按钮数据加到屏幕上并将数据添加到数组上---------
    public void  view_bth(String data){
        //new Blue_maze_Activity.sendDataThread(route[count]);//发送下一条指令
        String kk;
        if(data.equals("a") == true){
            kk = "直行0.1米";
            if(count_route(route) <= 49){
                route[count_route(route)] = "a";
                rev_str += kk + "-->";
            }
        }else if (data.equals("e") == true){
            kk = "直行0.2米";
            if(count_route(route) <= 49){
                route[count_route(route)] = "e";
                rev_str += kk + "-->";
            }
        }else if(data.equals("b") == true){
            kk = "左0.1米";
            if(count_route(route) <= 49){
                route[count_route(route)] = "b";
                rev_str += kk + "-->";
            }
        }else if(data.equals("c") == true){
            kk = "右0.1米";
            if(count_route(route) <= 49){
                route[count_route(route)] = "c";
                rev_str += kk + "-->";
            }
        }else if(data.equals("d") == true){
            kk = "后退0.1米";
            if(count_route(route) <= 49){
                route[count_route(route)] = "d";
                rev_str += kk + "-->";
            }
        }else if(data.equals("g") == true){
            kk = "左前0.1米";
            if(count_route(route) <= 49){
                route[count_route(route)] = "g";
                rev_str += kk + "-->";
            }
        }else if(data.equals("h") == true){
            kk = "右前0.1米";
            if(count_route(route) <= 49){
                route[count_route(route)] = "h";
                rev_str += kk + "-->";
            }
        }else if(data.equals("k") == true){
            kk = "左后0.1米";
            if(count_route(route) <= 49){
                route[count_route(route)] = "k";
                rev_str += kk + "-->";
            }
        }else if(data.equals("m") == true){
            kk = "右后0.1米";
            if(count_route(route) <= 49){
                route[count_route(route)] = "m";
                rev_str += kk + " --> ";
            }
        }else {
            if((count_route(route) <= 49)&&(count_route(route) >= 1)){
                kk = "结束";
                route[count_route(route)] = "o";
                rev_str += kk;
            }
        }
        //rev_str += kk;
        //更新UI
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                route_tv.setText(rev_str);
                route_sv.scrollTo(0, route_tv.getMeasuredHeight());
                System.out.println("rev:" + rev_str);
            }
        });

    }
    //----------收到返回值“f”执行下一条命令
    public void come(){
        new Blue_maze_Activity.sendDataThread(route[count]);//发送下一条指令
        count += 1 ;//计数加一
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_maze);
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
    public void onClick(View view) {
        //传入结束位“o”进入数组最后一位
        view_bth("o");
        new Blue_maze_Activity.sendDataThread("n");//开始按键

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
    private void init() {
        back1 = (Button) this.findViewById(R.id.back1);
        route_sv = (ScrollView) this.findViewById(R.id.route_sv);
        route_tv = (TextView) this.findViewById(R.id.route_tv);
        connect_state1 = (TextView) this.findViewById(R.id.route_tv);
        start_maze_btn = (Button) this.findViewById(R.id.start_maze);
        clean_maze_btn = (Button) this.findViewById(R.id.clean_maze);
        forward_01_btn = (Button) this.findViewById(R.id.top_center);
        forward_02_btn = (Button) this.findViewById(R.id.center_center);
        back_01_btn = (Button) this.findViewById(R.id.bottom_center);
        right_01_btn= (Button) this.findViewById(R.id.center_right);
        left_01_btn = (Button) this.findViewById(R.id.center_left);
        right_forward_01_btn = (Button) this.findViewById(R.id.top_right);
        left_forward_01_btn  = (Button) this.findViewById(R.id.top_left);
        left_back_01_btn  = (Button) this.findViewById(R.id.bottom_left);
        right_back_01_btn  = (Button) this.findViewById(R.id.bottom_right);
        //send_et = (EditText) this.findViewById(R.id.send_et);
        //connect_state.setText(status);
        start_maze_btn.setOnClickListener(this);
        back1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                Log.d("blue-text", "结束Blue_maze_activity");
            }
        });
        clean_maze_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                for (int i = 0 ; i < route.length ; i++){
                    route[i] = null;
                }
                //---清除路线操作----
                rev_str = "";
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        route_tv.setText(rev_str);
                        route_sv.scrollTo(0, route_tv.getMeasuredHeight());
                        System.out.println("rev:" + rev_str);
                    }
                });
                Log.d("blue-text", "清除路线执行了"+rev_str);
            }
        });
        forward_01_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                view_bth("a");
                Log.d("blue-text", "前行0.1米");
            }
        });
        forward_02_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                view_bth("e");
                Log.d("blue-text", "前行0.2米");
            }
        });
        back_01_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                view_bth("d");
                Log.d("blue-text", "后退0.1米");
            }
        });
        left_01_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                view_bth("b");
                Log.d("blue-text", "左0.1米");
            }
        });
        right_01_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                view_bth("c");
                Log.d("blue-text", "右0.1米");
            }
        });
        left_forward_01_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                view_bth("g");
                Log.d("blue-text", "左前0.1米");
            }
        });
        right_forward_01_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                view_bth("h");
                Log.d("blue-text", "右前0.1米");
            }
        });
        right_back_01_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                view_bth("m");
                Log.d("blue-text", "右后0.1米");
            }
        });
        left_back_01_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                view_bth("k");
                Log.d("blue-text", "左后0.1米");
            }
        });

    }
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
            Log.d("blue-text", "接收到的是"+rev_string);
            if (rev_string.equals("f") == true){
                Log.d("blue-text", "判断接收到的是f");
                come();
                rev_string = "x";//执行完指令将rev_string值赋值为“x”防止出错

            }else if (rev_string.equals("o") == true){
                //路线运行结束清除数据
                for (int i = 0 ; i < route.length ; i++){
                    route[i] = null;
                }
                rev_string = "x";
                count = 0;
                //----结束更新视图-----
                rev_str = "";
                route_tv.setText(rev_str);
                route_sv.scrollTo(0, route_tv.getMeasuredHeight());
                System.out.println("rev:" + rev_str);

            }

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        if (rev_string == "f"){
//            Log.d("blue-text", "判断接收到的是f");
//            come();
//            rev_string = "x";//执行完指令将rev_string值赋值为“x”防止出错
//
//        }
//        if (rev_string == "o"){
//            //路线运行结束清除数据
//            for (int i = 0 ; i < route.length ; i++){
//                route[i] = null;
//            }
//            rev_string = "x";
//            count = 0;
//
//        }
        //rev_str += rev_string;
        //更新UI
//        runOnUiThread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                route_tv.setText(rev_str);
//                route_sv.scrollTo(0, route_tv.getMeasuredHeight());
//                System.out.println("rev:" + rev_str);
//            }
//        });

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
                            Toast.makeText(Blue_maze_Activity.this, "没建立连接，请检查设备...", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }
                target_chara.setValue(lastData);//   --->此行出空指针错误):
                mBluetoothLeService.writeCharacteristic(target_chara);
            }

        }


    }

}

package com.example.magspace.model;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.magspace.Base.BaseActivity;
import com.example.magspace.Bean.BuildingBean;
import com.example.magspace.Bean.Item;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.ToastUtil;
import com.example.magspace.adapter.RecyclerAdapter;
import com.gcssloop.widget.PagerGridLayoutManager;
import com.gcssloop.widget.PagerGridSnapHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class BuildingDemoActivity extends BaseActivity {

    private List<ImageView> mImageList = new ArrayList<ImageView>();
    private List<Item> mlist = new ArrayList<>();
    private RecyclerView mRv;
    private int all_indicator;
    private LinearLayout mBottomstart;
    private LinearLayout.LayoutParams layoutParams;
    public static ArrayList<BuildingBean> list;
    public static int i;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_building_demo);
        setTitle("搭建演示");
        initView();
        Log.i("final", "onCreate: "+all_indicator);
        initData();

       for (int k =0 ;k<6;k++){
           layoutParams = new LinearLayout.LayoutParams(40, 40);
            ImageView imageView = new ImageView(BuildingDemoActivity.this);
            imageView.setLayoutParams(layoutParams);
            imageView.setBackgroundResource(R.drawable.indicator_selector);
           mBottomstart.addView(imageView);
           mBottomstart.getChildAt(k).setEnabled(false);
       }




    }


    private void initView() {
        mBottomstart = (LinearLayout) findViewById(R.id.bottomstart);
        mRv = (RecyclerView) findViewById(R.id.rv1);

        // 1.水平分页布局管理器
        PagerGridLayoutManager layoutManager = new PagerGridLayoutManager(
                4, 4, PagerGridLayoutManager.HORIZONTAL);



        // 2.设置滚动辅助工具
        PagerGridSnapHelper pageSnapHelper = new PagerGridSnapHelper();
        pageSnapHelper.attachToRecyclerView(mRv);


        layoutManager.setPageListener(new PagerGridLayoutManager.PageListener() {
            @Override
            public void onPageSizeChanged(final int i) {
                //BuildingDemoActivity.this.setAll_indicator(i);
                Log.i("1", "————————onPageSizeChanged————————————" + i);
//                all_indicator = i;
                //getall(i);
                Log.i("2", "onCreate: "+all_indicator);

//                for (int k =0 ;k<i;k++){
//                    Log.i("kkkkkk", "onPageSizeChanged: "+k);
//                    layoutParams = new LinearLayout.LayoutParams(20, 20);
//                    ImageView imageView = new ImageView(BuildingDemoActivity.this);
//                    imageView.setLayoutParams(layoutParams);
//                    imageView.setBackgroundResource(R.drawable.wenhao);
//                    mBottomstart.addView(imageView);
//                }
//                    view.setBackgroundResource(R.drawable.wenhao);
//                    view.setBackgroundColor(Color.RED);
////                    view.setEnabled(false);
//                    //添加到LinearLayout
//                    mMainLinear.addView(view,layoutParams);
                Log.i("3", "————————onPageSizeChanged————————————" + i);
                Log.i("5", "onCreate: "+all_indicator);
            }


            @Override
            public void onPageSelect(int i) {
//                Log.i("page", "————————onPageSizeSelect————————————" + i);
//                textView.setText(i+"");
                //第一次显示小白点
                for (int k= 0; k<6;k++) {
                    if (k==i){
                        mBottomstart.getChildAt(i).setEnabled(true);
                    }else {
                        mBottomstart.getChildAt(k).setEnabled(false);
                    }
                }
            }
        });

        Log.i("6", "onCreate: "+all_indicator);

        mRv.setLayoutManager(layoutManager);

        RecyclerAdapter adapter = new RecyclerAdapter(mlist);
        adapter.setOnItemClickLitener(new RecyclerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (DataUtil.isvoiceplay)
                    DataUtil.soundPool.play(7, 1, 1, 1, 0, 1);
                i=position;
                Log.i("i", "onItemClick: "+i);
                startActivity(new Intent(BuildingDemoActivity.this, BuildingdetailActivity.class));

            }
        });



        mRv.setAdapter(adapter);



    }
//    public abstract class ListUserCallback extends Callback<List<BuildingBean>>
//    {
//        private List<BuildingBean> parseNetworkResponse(Response response) throws IOException
//        {
//
//            String string = response.body().string();
//            JSONObject jsonObject = null;
//            try {
//                jsonObject = new JSONObject(string);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            String jsonstring = null;
//            try {
//                jsonstring = jsonObject.getString("data");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            Log.i("jsonArraystring", "parseNetworkResponse: "+jsonstring);
//            Gson gson = new Gson();
//            list = gson.fromJson(jsonstring,new TypeToken<ArrayList<BuildingBean>>(){}.getType());
//            Log.i("gson", "parseNetworkResponse: "+list.get(0).title);
//
//            return list;
//        }
//
//
//    }



    private void initData() {
        dialog = ProgressDialog.show(this, "提示", "正在加载中", false);
        postDataWithParam();
        for (int i = 0; i < 96; i++) {
            mlist.add(new Item("" + (i + 1), R.drawable.show_stage_lock));
        }
    }

    private void postDataWithParam() {

        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        Request request = new Request.Builder()//创建Request 对象。
                .url("http://android.magspace2015.com/magspace/android_djys_list")
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                    ToastUtil.getInstance().showToast("请检查您的网络设置");
                    dialog.dismiss();
                    finish();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    Log.d("okhttp","获取数据成功");
                    String string = response.body().string();
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(string);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String jsonstring = null;
                    try {
                        jsonstring = jsonObject.getString("data");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("jsonArraystring", "parseNetworkResponse: "+jsonstring);
                    Gson gson = new Gson();
                    list = gson.fromJson(jsonstring,new TypeToken<ArrayList<BuildingBean>>(){}.getType());
                    dialog.dismiss();

                }else{
                    Log.d("okhttp","获取数据失败");
                    ToastUtil.getInstance().showToast("获取数据失败");
                    dialog.dismiss();

                }
            }
        });

    }
}

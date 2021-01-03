package com.example.magspace.model;


import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.magspace.Base.BaseActivity;
import com.example.magspace.Bean.ProudctionBean;
import com.example.magspace.R;
import com.example.magspace.Utils.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cc.shinichi.library.ImagePreview;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProductActivity extends BaseActivity {
    private XBanner mBanner;
    private static List<String> images;
    private static List<String> titles;
    private static List<ProudctionBean> gsonlist;
    private ProgressDialog dialog;
    private static double edition= 0.9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_product);
        mBanner = (XBanner) findViewById(R.id.banner2);
        setTitle("产品展示");
        dialog = ProgressDialog.show(this, "提示", "正在加载中", false);
        postediton();

    }

    private void postediton() {

        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体

        Request request = new Request.Builder()//创建Request 对象。
                .url("http://android.magspace2015.com/magspace/android_version_get_last_version")
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                ToastUtil.getInstance().showToast("请检查您的网络设置");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    ToastUtil.getInstance().showToast("获取新版本成功");
                    Log.d("okhttp","获取新版本成功");
                    String string = response.body().string();
                    Log.i("editiondata", "------------------版本信息------------------ "+string);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(string);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String jsonstring = null;
                    String jsonedition= null;
                    try {
                        jsonstring = jsonObject.getString("data");
                        Log.i("json", "jsondata "+jsonstring);
                        jsonObject = new JSONObject(jsonstring);
                       jsonedition= jsonObject.getString("version_number");
                        Log.i("jsonedition", "jsonedition"+jsonedition);
                        if((Double.parseDouble(jsonedition))!=edition)
                        {
                            //版本有更新的时候更新数据i
                            initView();

                        }else {
                            //版本无更新的时候不去访问接口

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("jsonArraystring", "parseNetworkResponse: "+jsonstring);



                }else{
                    Log.d("okhttp","获取版本失败");
                    ToastUtil.getInstance().showToast("获取版本失败");
                    dialog.dismiss();

                }
            }
        });

    }



    private void initView() {

        postData();
        // 初始化XBanner中展示的数据

//        images.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
//        images.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
//        images.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");



        }

    private void postData() {
        images = new ArrayList<>();
        titles = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体

        Request request = new Request.Builder()//创建Request 对象。
                .url("http://android.magspace2015.com/magspace/android_cpzs_list")
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                ToastUtil.getInstance().showToast("请检查您的网络设置");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    ToastUtil.getInstance().showToast("获取数据成功");
                    Log.d("okhttp","获取数据成功");
                    String string = response.body().string();
                    Log.i("onrespone", "onResponse: "+string);
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
                    gsonlist = gson.fromJson(jsonstring,new TypeToken<ArrayList<ProudctionBean>>(){}.getType());
                    for (ProudctionBean bean : gsonlist){
                        titles.add(bean.title);
                        images.add(bean.pic);
                        addData();
                    }
                    dialog.dismiss();

                }else{
                    Log.d("okhttp","获取数据失败");
                    ToastUtil.getInstance().showToast("获取数据失败");
                    dialog.dismiss();

                }
            }
        });

    }

    private void addData() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    // 为XBanner绑定数据
                    mBanner.setData(R.layout.layout_fresco_imageview, images, titles);
                    //设置标题集合（当banner样式有显示title时）
                    mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int i, float v, int i1) {
                            Log.i("lkw", "onPageScrolled: "+i);
                        }

                        @Override
                        public void onPageSelected(int i) {
                            Log.i("lkw", "onPageSelected: "+i);
                            setTitle(titles.get(i));

                        }

                        @Override
                        public void onPageScrollStateChanged(int i) {
                            Log.i("lkw", "onPageScrollStateChanged: "+i);

                        }
                    });

//        //设置图片圆角角度
//        RoundedCorners roundedCorners= new RoundedCorners(10);//通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
//        final RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(300, 300);

//         XBanner适配数据
                    mBanner.loadImage(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, Object model, View view, int position) {
                            //此处适用Fresco加载图片
                            SimpleDraweeView draweeView = (SimpleDraweeView) view;
                            draweeView.setImageURI(Uri.parse(images.get(position)));
                        }
                    });
                    mBanner.startAutoPlay();
                    mBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                        @Override
                        public void onItemClick(XBanner banner, Object model, View view, int position) {
                            ImagePreview
                                    .getInstance()
                                    // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                                    .setContext(ProductActivity.this)
                                    // 设置从第几张开始看（索引从0开始）
                                    .setIndex(position)
                                    //=================================================================================================
                                    // 有三种设置数据集合的方式，根据自己的需求进行三选一：
                                    // 1：第一步生成的imageInfo List
//                            .setImageInfoList(imageInfoList)
                                    // 2：直接传url List
                                    .setImageList(images)
                                    // 3：只有一张图片的情况，可以直接传入这张图片的url
                                    //.setImage(String image)
                                    //=================================================================================================
                                    // 开启预览
                                    .start();
                            // 仅需一行代码,默认配置为：
                            //      显示顶部进度指示器、
                            //      显示右侧下载按钮、
                            //      隐藏左侧关闭按钮、
                            //      开启点击图片关闭、
                            //      关闭下拉图片关闭、
                            //      加载方式为手动模式
                            //      加载原图的百分比在底部
                            Toast.makeText(ProductActivity.this, "点击了第" + (position + 1) + "图片", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

    }


    @Override
    protected void onResume() {
        super.onResume();
        mBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }


}

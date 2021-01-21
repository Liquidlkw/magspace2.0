package com.example.magspace.model;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magspace.Base.BaseActivity;
import com.example.magspace.Bean.AviBean;
import com.example.magspace.R;
import com.example.magspace.Utils.DataUtil;
import com.example.magspace.Utils.ToastUtil;
import com.example.magspace.adapter.AviAdapter;
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

public class AviActivity extends BaseActivity {

    private RecyclerView mRv;
    public static List<AviBean> list;
    private ProgressDialog dialog;
    private AviAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (DataUtil.ismusicplay) {
            DataUtil.backmusic.pause();
        }
        setContentLayout(R.layout.activity_avi);
        setTitle("视频展示");
        dialog = ProgressDialog.show(this, "提示", "正在加载中", false);
        dialog.setCancelable(true);
        dialog.setOnCancelListener(dialog -> {
            dialog.dismiss();
            finish();
        });
        initdata();
    }

    protected void onResume() {
        super.onResume();
        if (DataUtil.ismusicplay && DataUtil.backmusic != null)
            DataUtil.backmusic.pause();
    }

    private void postDataWithParam() {

        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBody = new FormBody.Builder();
        Request request = new Request.Builder()
                .url("http://android.magspace2015.com/magspace/android_avi_list")
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
                if (response.isSuccessful()) {
                    Log.d("okhttp", "获取数据成功");
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
                    Log.i("jsonArraystring", "parseNetworkResponse: " + jsonstring);
                    Gson gson = new Gson();
                    list = new ArrayList<>();
                    list = gson.fromJson(jsonstring, new TypeToken<List<AviBean>>() {}.getType());

                    Log.i("list", "list: " + list.size());
//                    Toast.makeText(AviActivity.this, ""+list.get(0).title, Toast.LENGTH_SHORT).show();
                    Log.d("list", "onResponse: "+list.get(0).title);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initView();
                        }
                    });

                    dialog.dismiss();

                } else {
                    Log.d("okhttp", "获取数据失败");
                    ToastUtil.getInstance().showToast("获取数据失败");
                    dialog.dismiss();

                }
            }
        });

    }

    private void initdata() {
        postDataWithParam();
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new AviAdapter(list);
        mRv.setAdapter(adapter);


    }
}



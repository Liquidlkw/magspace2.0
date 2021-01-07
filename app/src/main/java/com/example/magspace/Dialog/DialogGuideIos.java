package com.example.magspace.Dialog;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.magspace.R;

import static android.content.Context.MODE_PRIVATE;


public class DialogGuideIos extends DialogFragment {

    //用volatile修饰的变量，
    //线程在每次使用变量的时候，都会读取变量修改后的最的值。
    //volatile很容易被误用，用来进行原子性操作。
    private static volatile DialogGuideIos dialog = null;

    //私有化构造函数
    private DialogGuideIos() {}

    //单例模式创建Dialog
    public static DialogGuideIos getInstance() {
        if (dialog == null) {
            synchronized (DialogGuideIos.class) {
                dialog = new DialogGuideIos();
            }
        }
        return dialog;
    }

    //使用onCreateView才能完全实现一个自定义Dialog
    //若是使用onCreateDialog 仅仅只能自定义对话框的内容（即边框 按钮 样式不可以自定义）
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /*
         * 先设置 无标题样式 对话框
         */
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //将弹窗的背景设置为透明色（#00000000），解决直角问题
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //点击外部不可以消失
        getDialog().setCanceledOnTouchOutside(false);
        //禁用返回键
        getDialog().setOnKeyListener((dialog, keyCode, event) -> true);

        View view = inflater.inflate(R.layout.dialog_guide_ios, container, false);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        TextView textView = getDialog().findViewById(R.id.dialog_content);
        textView.setText(Html.fromHtml("请您务必审慎阅读、充分理解“隐私政策”各条款。\n" +
                "Magspace App尊重并保护所有使用服务用户的个人隐私权。\n"
                + "<br><br>" + "你可以阅读： <font color=\"#3A76FE\">《隐私政策》</font> 了解详细信息，如你同意，请点击“同意”开始接受我们的服务。"));

        getDialog().findViewById(R.id.disagree).setOnClickListener(v -> {
            dismiss();
            System.exit(0);
        });

        getDialog().findViewById(R.id.agree).setOnClickListener(v -> {
            dismiss();
            //sp存储 下次就不会跳出来了
            //1、定义SharedPreferences对象，通过getSharedPreferences方法得到
            SharedPreferences sp = getContext().getSharedPreferences("Magspace_Sp", MODE_PRIVATE);//模式通常为MODE_PRIVATE
            //2、获得该SharedPreferences对象的编辑器Editor
            SharedPreferences.Editor editor = sp.edit();
            //3、通过编辑器向SharedPreferences中存放数据
            editor.putBoolean("agree", true);//字段名为字符串，字段值与XXX数据类型保持一致
            //4、编辑器commit提交数据，完成数据存储
            editor.commit();
        });
    }
}

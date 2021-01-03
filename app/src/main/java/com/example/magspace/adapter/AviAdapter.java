package com.example.magspace.adapter;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.magspace.Bean.AviBean;
import com.example.magspace.R;
import com.example.magspace.model.AvidetailActivity;

import java.util.List;


public class AviAdapter extends RecyclerView.Adapter<AviAdapter.ViewHolder>{
    private List<AviBean> list;
    public static int x;

    public AviAdapter(List<AviBean> list){
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_avi,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AviBean bean = list.get(i);
        viewHolder.textView.setText(bean.describe);
        Glide.with(viewHolder.itemView).load(bean.pic).placeholder(R.drawable.default_image).into(ViewHolder.imageView);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.getInstance().showToast(i);
                viewHolder.itemView.getContext().startActivity(new Intent(viewHolder.itemView.getContext(), AvidetailActivity.class));
                Log.i("iiiiiiiiiii", "onClick: "+i);
                x=i;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null&&list.size()>0)
        return list.size();
        return 0;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        static ImageView imageView;
        TextView textView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.avi_pic);
            textView = itemView.findViewById(R.id.avi_des);

        }
    }
}

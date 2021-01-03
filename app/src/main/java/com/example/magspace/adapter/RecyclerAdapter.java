package com.example.magspace.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.magspace.Bean.Item;
import com.example.magspace.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
        private List<Item> List;
         private OnItemClickLitener   mOnItemClickLitener;

    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
    }

    public RecyclerAdapter(List<Item> list) {
        List = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_item_view,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Item item  = List.get(i);
        viewHolder.image.setImageResource(item.image);
        viewHolder.title.setText(item.title);

        if(mOnItemClickLitener!= null)
        {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(v, i);
                    viewHolder.image.setImageResource(R.drawable.show_stage_star3);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {

        private final ImageView image;
        private final TextView title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.name);
        }
    }

}

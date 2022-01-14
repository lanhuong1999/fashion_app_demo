package com.example.fashionapp.ui.ao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashionapp.Common;
import com.example.fashionapp.R;
import com.example.fashionapp.models.Ao;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.List;

public class AoAdapter extends RecyclerView.Adapter<AoAdapter.ViewHolder> {

    private Context context;
    private List<Ao> aoList;
    private RecyclerViewClickItem clickItem;

    public AoAdapter(Context context, List<Ao> aoList, RecyclerViewClickItem clickItem) {
        this.context = context;
        this.aoList = aoList;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ao ao =aoList.get(position);
        Glide.with(context).load(ao.getImg_url()).into(holder.imageView);
        holder.name.setText(ao.getTen());
        holder.des.setText(ao.getMoTa());
        holder.sub.setText("Kiểu áo: "+ao.getLoaiAo());
        holder.price.setText(Common.formatGia(ao.getGia()) +"đ");
    }

    @Override
    public int getItemCount() {
        return aoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, des, sub, price;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.product_img);
            name=itemView.findViewById(R.id.product_name);
            des=itemView.findViewById(R.id.product_des);
            sub=itemView.findViewById(R.id.product_loai);
            price=itemView.findViewById(R.id.product_gia);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickItem.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}

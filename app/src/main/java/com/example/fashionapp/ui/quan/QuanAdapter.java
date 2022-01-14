package com.example.fashionapp.ui.quan;

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
import com.example.fashionapp.models.Quan;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.List;

public class QuanAdapter extends RecyclerView.Adapter<QuanAdapter.ViewHolder> {

    private Context context;
    private List<Quan>quanList;
    private RecyclerViewClickItem clickItem;

    public QuanAdapter(Context context, List<Quan> quanList, RecyclerViewClickItem clickItem) {
        this.context = context;
        this.quanList = quanList;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Quan quan=quanList.get(position);
        Glide.with(context).load(quan.getImg_url()).into(holder.imageView);
        holder.name.setText(quan.getTen());
        holder.des.setText(quan.getMoTa());
        holder.sub.setText("Kiểu quần: "+quan.getLoaiQuan());
        holder.price.setText(Common.formatGia(quan.getGia()) +"đ");

    }

    @Override
    public int getItemCount() {
        return quanList.size();
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

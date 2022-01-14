package com.example.fashionapp.ui.phukien;

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
import com.example.fashionapp.models.PhuKien;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.List;

public class PhuKienAdapter extends RecyclerView.Adapter<PhuKienAdapter.ViewHolder> {

    Context context;
    List<PhuKien> phuKienList;
    RecyclerViewClickItem clickItem;

    public PhuKienAdapter(Context context, List<PhuKien> phuKienList, RecyclerViewClickItem clickItem) {
        this.context = context;
        this.phuKienList = phuKienList;
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
        PhuKien phuKien=phuKienList.get(position);
        Glide.with(context).load(phuKien.getImg_url()).into(holder.imageView);
        holder.name.setText(phuKien.getTen());
        holder.des.setText(phuKien.getMoTa());
        holder.sub.setText("Loại phụ kiện: "+phuKien.getLoaiPK());
        holder.price.setText(Common.formatGia(phuKien.getGia()) +"đ");
    }

    @Override
    public int getItemCount() {
        return phuKienList.size();
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

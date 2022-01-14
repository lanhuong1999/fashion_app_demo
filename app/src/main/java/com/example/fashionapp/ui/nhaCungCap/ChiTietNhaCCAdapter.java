package com.example.fashionapp.ui.nhaCungCap;

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
import com.example.fashionapp.activity.adapter.RecommendAdapter;
import com.example.fashionapp.models.SanPham;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.List;

public class ChiTietNhaCCAdapter extends RecyclerView.Adapter<ChiTietNhaCCAdapter.ViewHolder> {

    private Context context;
    private List<SanPham> sanPhamList;
    private RecyclerViewClickItem clickItem;

    public ChiTietNhaCCAdapter(Context context, List<SanPham> sanPhamList, RecyclerViewClickItem clickItem) {
        this.context = context;
        this.sanPhamList = sanPhamList;
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
        SanPham sanPham = sanPhamList.get(position);
        Glide.with(context).load(sanPham.getImg_url()).into(holder.imageView);
        holder.name.setText(sanPham.getTen());
        holder.des.setText(sanPham.getMoTa());
        holder.price.setText(Common.formatGia(sanPham.getGia()));
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, des, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.product_img);
            name=itemView.findViewById(R.id.product_name);
            des=itemView.findViewById(R.id.product_des);
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

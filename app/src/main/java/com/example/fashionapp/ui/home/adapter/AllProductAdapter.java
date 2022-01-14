package com.example.fashionapp.ui.home.adapter;

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
import com.example.fashionapp.models.SanPham;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolder> {
    private Context context;
    private List<SanPham> productList;
    private RecyclerViewClickItem clickItem;

    public AllProductAdapter(Context context, List<SanPham> productList, RecyclerViewClickItem clickItem) {
        this.context = context;
        this.productList = productList;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wishlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham product=productList.get(position);
        Glide.with(context).load(product.getImg_url()).into(holder.imageView);
        holder.name.setText(product.getTen());
        holder.price.setText(Common.formatGia(product.getGia())+" đ");
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.item_wishlist_img);
            name=itemView.findViewById(R.id.item_wishlist_name);
            price=itemView.findViewById(R.id.item_wishlist_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickItem.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}

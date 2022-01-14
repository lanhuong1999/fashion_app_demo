package com.example.fashionapp.ui.category.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashionapp.R;
import com.example.fashionapp.models.SaleProduct;

import java.util.List;

public class CategoryProductAdapter extends RecyclerView.Adapter<CategoryProductAdapter.ViewHolder> {

    private Context context;
    private List<SaleProduct> saleProductList;

    public CategoryProductAdapter(Context context, List<SaleProduct> saleProductList) {
        this.context = context;
        this.saleProductList = saleProductList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SaleProduct product = saleProductList.get(position);
        Glide.with(context).load(product.getImg_url()).into(holder.imgProduct);
        holder.name.setText(product.getName());
        holder.des.setText(product.getDescription());
        //holder.sale.setText(product.getSale()+"%");
    }

    @Override
    public int getItemCount() {
        return saleProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct, detail;
        TextView name, des, sale;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct=itemView.findViewById(R.id.product_img);
            name=itemView.findViewById(R.id.product_name);
            des=itemView.findViewById(R.id.product_des);
            //sale=itemView.findViewById(R.id.product_sale);
            detail=itemView.findViewById(R.id.btn_product_detail);
        }
    }
}

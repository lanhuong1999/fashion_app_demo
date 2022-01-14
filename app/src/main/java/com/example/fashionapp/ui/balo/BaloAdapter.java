package com.example.fashionapp.ui.balo;

import android.content.Context;
import android.service.controls.actions.CommandAction;
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
import com.example.fashionapp.models.Balo;
import com.example.fashionapp.ui.RecyclerViewClickItem;
import com.example.fashionapp.ui.ao.AoAdapter;

import java.util.List;

public class BaloAdapter extends RecyclerView.Adapter<BaloAdapter.ViewHolder> {

    private Context context;
    private List<Balo> balos;
    private RecyclerViewClickItem clickItem;

    public BaloAdapter(Context context, List<Balo> balos, RecyclerViewClickItem clickItem) {
        this.context = context;
        this.balos = balos;
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
        Balo balo=balos.get(position);
        Glide.with(context).load(balo.getImg_url()).into(holder.imageView);
        holder.name.setText(balo.getTen());
        holder.des.setText(balo.getMoTa());
        holder.sub.setText("");
        holder.price.setText(Common.formatGia(balo.getGia()) +"đ");
    }

    @Override
    public int getItemCount() {
        return balos.size();
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

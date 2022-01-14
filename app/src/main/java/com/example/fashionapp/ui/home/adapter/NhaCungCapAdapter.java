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
import com.example.fashionapp.R;
import com.example.fashionapp.models.NhaCungCap;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.List;

public class NhaCungCapAdapter extends RecyclerView.Adapter<NhaCungCapAdapter.ViewHolder> {

    private Context context;
    private List<NhaCungCap> nhaCungCapList;
    private RecyclerViewClickItem clickItem;

    public NhaCungCapAdapter(Context context, List<NhaCungCap> nhaCungCapList, RecyclerViewClickItem clickItem) {
        this.context = context;
        this.nhaCungCapList = nhaCungCapList;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhacc,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NhaCungCap nhaCungCap=nhaCungCapList.get(position);
        holder.name.setText(nhaCungCap.getTenNCC());
        holder.add.setText(nhaCungCap.getDiaChi());
        Glide.with(context).load(nhaCungCap.getAnh_url()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return nhaCungCapList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_popular);
            name = itemView.findViewById(R.id.txt_name_popular);
            add = itemView.findViewById(R.id.des_popular);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickItem.onItemClick(getAdapterPosition());
                }
            });
        }
    }

}

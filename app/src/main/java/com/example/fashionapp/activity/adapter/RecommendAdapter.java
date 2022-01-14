package com.example.fashionapp.activity.adapter;

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
import com.example.fashionapp.models.SanPham;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    private Context context;
    private List<SanPham> sanPhamList;
    private RecyclerViewClickItem clickItem;

    public RecommendAdapter(Context context, List<SanPham> sanPhamList, RecyclerViewClickItem clickItem) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sanPham=sanPhamList.get(position);
        Glide.with(context).load(sanPham.getImg_url()).into(holder.itemImg);
        holder.itemName.setText(sanPham.getTen());
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImg;
        TextView itemName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg=itemView.findViewById(R.id.item_rcm_img);
            itemName=itemView.findViewById(R.id.item_rcm_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickItem.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}

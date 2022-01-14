package com.example.fashionapp.ui.timkiem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashionapp.Common;
import com.example.fashionapp.R;
import com.example.fashionapp.models.SanPham;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<SanPham> sanPhamList;
    private RecyclerViewClickItem clickItem;
    private List<SanPham> oldList;
    private List<SanPham> newList;

    public SearchAdapter(Context context, List<SanPham> sanPhamList, RecyclerViewClickItem clickItem) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.clickItem = clickItem;
        this.oldList=sanPhamList;
        this.newList=sanPhamList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sanPham=sanPhamList.get(position);
        Glide.with(context).load(sanPham.getImg_url()).into(holder.imageView);
        holder.name.setText(sanPham.getTen());
        holder.des.setText(sanPham.getMoTa());
        holder.type.setText(sanPham.getLoai());
        holder.gia.setText(Common.formatGia(sanPham.getGia())+" đ");
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String str_search=charSequence.toString();

                if(str_search.isEmpty()){
                    newList=oldList;
                }
                else{
                    String arr_srt[]=str_search.split("\\s+");
                    for(String s: arr_srt) {
                        List<SanPham> list = new ArrayList<>();
                        for (SanPham sanPham : newList) {
                            if (sanPham.getTen().toLowerCase().contains(s.toLowerCase())) {
                                list.add(sanPham);
                            }
                        }
                        newList = list;
                    }
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=newList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                sanPhamList= (List<SanPham>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, des, gia, type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.product_img);
            name=itemView.findViewById(R.id.product_name);
            des=itemView.findViewById(R.id.product_des);
            type=itemView.findViewById(R.id.product_loai);
            gia=itemView.findViewById(R.id.product_gia);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickItem.onItemClick(getAdapterPosition());
                }
            });
        }
    }
    public SanPham getItem(int position){
        return sanPhamList.get(position);
    }
}

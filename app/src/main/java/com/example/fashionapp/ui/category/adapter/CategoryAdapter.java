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
import com.example.fashionapp.models.CategoryHome;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<CategoryHome> list;
    private RecyclerViewClickItem clickItem;

    public CategoryAdapter(Context context, List<CategoryHome> list, RecyclerViewClickItem clickItem) {
        this.context = context;
        this.list = list;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryHome categoryHome = list.get(position);
        Glide.with(context).load(categoryHome.getImg_url())
                .centerCrop()
                .into(holder.imageView);
        holder.textView.setText(categoryHome.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_category2_image);
            textView = itemView.findViewById(R.id.item_category2_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickItem.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}

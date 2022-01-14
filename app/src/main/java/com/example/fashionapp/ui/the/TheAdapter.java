package com.example.fashionapp.ui.the;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashionapp.R;
import com.example.fashionapp.models.The;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.List;

public class TheAdapter extends RecyclerView.Adapter<TheAdapter.ViewHolder> {

    private Context context;
    private List<The> theList;
    private RecyclerViewClickItem clickItem;
    private XoaThe xoaThe;

    public TheAdapter(Context context, List<The> theList, RecyclerViewClickItem clickItem, XoaThe xoaThe) {
        this.context = context;
        this.theList = theList;
        this.clickItem = clickItem;
        this.xoaThe = xoaThe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_the,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        The the=theList.get(position);
        holder.tenNH.setText(the.getTenNH());
        holder.stk.setText("Số tài khoản: "+the.getSoTK());
    }

    @Override
    public int getItemCount() {
        return theList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenNH, stk;
        ImageView xoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenNH=itemView.findViewById(R.id.item_the_ten);
            stk=itemView.findViewById(R.id.item_the_stk);
            xoa=itemView.findViewById(R.id.item_the_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickItem.onItemClick(getAdapterPosition());
                }
            });

            xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    xoaThe.xoaThe(getAdapterPosition());

                }
            });
        }
    }
}

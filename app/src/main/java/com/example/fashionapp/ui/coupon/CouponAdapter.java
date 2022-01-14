package com.example.fashionapp.ui.coupon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashionapp.R;
import com.example.fashionapp.models.MaKM;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.List;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {
    private Context context;
    private List<MaKM> couponList;
    RecyclerViewClickItem clickItem;

    public CouponAdapter(Context context, List<MaKM> couponList, RecyclerViewClickItem clickItem) {
        this.context = context;
        this.couponList = couponList;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coupon,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MaKM maKM=couponList.get(position);
        holder.id_coupon.setText("ID: "+maKM.getId());
        holder.price_coupon.setText("Giảm giá: "+maKM.getGiaKM()+"%");
        holder.sl_coupon.setText("Số lượng: "+maKM.getSoLuong());
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_coupon, price_coupon, sl_coupon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_coupon=itemView.findViewById(R.id.coupon_id);
            price_coupon=itemView.findViewById(R.id.coupon_price);
            sl_coupon=itemView.findViewById(R.id.coupon_soluong);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickItem.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}

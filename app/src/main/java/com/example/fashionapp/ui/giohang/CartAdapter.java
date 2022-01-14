package com.example.fashionapp.ui.giohang;

import android.annotation.SuppressLint;
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
import com.example.fashionapp.models.GioHang;
import com.example.fashionapp.models.SanPham;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private List<GioHang> gioHangList;
    private List<SanPham> sanPhamList;
    private RecyclerViewClickItem clickItem;

    int price;

    public CartAdapter(Context context, List<GioHang> gioHangList, List<SanPham> sanPhamList, RecyclerViewClickItem clickItem) {
        this.context = context;
        this.gioHangList = gioHangList;
        this.sanPhamList = sanPhamList;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
        SanPham sanPham=sanPhamList.get(position);
        Glide.with(context).load(sanPham.getImg_url()).into(holder.img_product);
        holder.name.setText(sanPham.getTen());
        holder.unit_price.setText("Đơn giá: "+ Common.formatGia(sanPham.getGia()));
        holder.quantity.setText("Số lượng: "+gioHang.getSoluong());
        holder.total_price.setText("Tổng tiền: "+Common.formatGia(gioHang.getTongTien()));

//        price+=gioHang.getTongTien();
//        Intent intent = new Intent("total_price");
//        intent.putExtra("price",price);
//        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_product;
        TextView name,unit_price, quantity, total_price, delete_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product=itemView.findViewById(R.id.cart_img);
            name=itemView.findViewById(R.id.cart_name);
            unit_price=itemView.findViewById(R.id.cart_unit_price);
            quantity=itemView.findViewById(R.id.cart_quantity);
            total_price=itemView.findViewById(R.id.cart_total_price);
            delete_item=itemView.findViewById(R.id.cart_delete);

            delete_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickItem.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}

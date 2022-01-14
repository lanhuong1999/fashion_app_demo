package com.example.fashionapp.ui.donhang;

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
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.SanPhamDAO;
import com.example.fashionapp.database.repository.SanPhamImp;
import com.example.fashionapp.models.Donhang_has_sanpham;
import com.example.fashionapp.models.SanPham;

import java.util.List;

public class ChiTietDonHangAdapter extends RecyclerView.Adapter<ChiTietDonHangAdapter.ViewHolder> {

    private Context context;
    private List<Donhang_has_sanpham> sanphamList;

    public ChiTietDonHangAdapter(Context context, List<Donhang_has_sanpham> sanphamList) {
        this.context = context;
        this.sanphamList = sanphamList;
    }

    @NonNull
    @Override
    public ChiTietDonHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang_detail,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietDonHangAdapter.ViewHolder holder, int position) {
        Donhang_has_sanpham donhang_has_sanpham=sanphamList.get(position);
        Database database=new Database(context);
        SanPhamDAO sanPhamDAO=new SanPhamImp();
        SanPham sanPham=sanPhamDAO.getSPbyID(database,donhang_has_sanpham.getIDSP());
        Glide.with(context).load(sanPham.getImg_url()).into(holder.imageView);
        holder.txt_name.setText(sanPham.getTen());
        holder.txt_soluong.setText("Số lượng: "+String.valueOf(donhang_has_sanpham.getSoluong()));
    }

    @Override
    public int getItemCount() {
        return sanphamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_name, txt_soluong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.order_detail_img);
            txt_name=itemView.findViewById(R.id.order_detail_name);
            txt_soluong=itemView.findViewById(R.id.order_detail_quantity);

        }
    }
}

package com.example.fashionapp.ui.donhang;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashionapp.Common;
import com.example.fashionapp.R;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.MakMDAO;
import com.example.fashionapp.database.repository.MaKMImp;
import com.example.fashionapp.models.DonHang;
import com.example.fashionapp.models.MaKM;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {

    private Context context;
    private List<DonHang> donHangList;
    private RecyclerViewClickItem clickItem;

    public DonHangAdapter(Context context, List<DonHang> donHangList, RecyclerViewClickItem clickItem) {
        this.context = context;
        this.donHangList = donHangList;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonHang donHang= donHangList.get(position);
        holder.IDDH.setText("Mã đơn hàng #"+donHang.getIDDH());
        holder.sdt.setText(donHang.getSđt());
        holder.diachi.setText(donHang.getDiachi());
        holder.thanhtoan.setText(donHang.getThanhtoan());
        holder.thoigian.setText(donHang.getDate());
        holder.soluong.setText("Tổng số lượng: "+donHang.getSoluong());
        holder.tientra.setText(Common.formatGia(donHang.getTongTien())+" đ");
        int giam=0;
        if(donHang.getIDMaKM()!=-1){
            MaKM maKM=getMa(donHang.getIDMaKM());
            giam=maKM.getGiaKM();
        }
        int total=donHang.getTongTien()*100/(100-giam);
        holder.tongtien.setText(Common.formatGia(total)+" đ");
        SpannableString giam_str=new SpannableString(Common.formatGia(total-donHang.getTongTien())+" đ");
        giam_str.setSpan(new StrikethroughSpan(),0,giam_str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.giamgia.setText(giam_str);
    }

    private MaKM getMa(int id){
        MakMDAO makMDAO=new MaKMImp();
        Database database=new Database(context);
        return makMDAO.getMaKMByID(database,id);
    }

    @Override
    public int getItemCount() {
        return donHangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView IDDH, sdt, diachi, thanhtoan, thoigian, soluong, tongtien, giamgia, tientra;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            IDDH=itemView.findViewById(R.id.order_id);
            sdt=itemView.findViewById(R.id.order_phone);
            diachi=itemView.findViewById(R.id.order_add);
            thanhtoan=itemView.findViewById(R.id.order_method);
            thoigian=itemView.findViewById(R.id.order_date);
            soluong=itemView.findViewById(R.id.order_quantity);
            tongtien=itemView.findViewById(R.id.order_total);
            giamgia=itemView.findViewById(R.id.order_discount);
            tientra=itemView.findViewById(R.id.order_pay);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickItem.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}

package com.example.fashionapp.ui.nhaCungCap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fashionapp.R;
import com.example.fashionapp.activity.DetailActivity;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.NhaCungCapDAO;
import com.example.fashionapp.database.dao.SanPhamDAO;
import com.example.fashionapp.database.repository.NhaCungCapImp;
import com.example.fashionapp.database.repository.SanPhamImp;
import com.example.fashionapp.models.NhaCungCap;
import com.example.fashionapp.models.SanPham;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.ArrayList;
import java.util.List;

public class NhaCungCapFragment extends Fragment implements RecyclerViewClickItem {

    ImageView imgView;
    TextView txtTen, txtDiaChi, txtSdt;
    Database database;
    RecyclerView rec_sanpham;
    ChiTietNhaCCAdapter adapter;
    List<SanPham> sanPhamList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_nha_cung_cap, container, false);

        imgView=view.findViewById(R.id.ncc_img);
        txtTen=view.findViewById(R.id.ncc_ten);
        txtDiaChi=view.findViewById(R.id.ncc_diachi);
        txtSdt=view.findViewById(R.id.ncc_sdt);
        database=new Database(getActivity());
        rec_sanpham=view.findViewById(R.id.rec_ncc_sp);
        rec_sanpham.setLayoutManager(new LinearLayoutManager(getActivity()));
        sanPhamList=new ArrayList<>();


        Bundle bundle=getArguments();
        if(bundle!=null){
            String IDNCC= bundle.getString("id_ncc");
            setView(IDNCC);
        }


        return view;
    }

    @SuppressLint("SetTextI18n")
    private void setView(String id) {
        NhaCungCapDAO nhaCungCapDAO=new NhaCungCapImp();
        NhaCungCap nhaCungCap=nhaCungCapDAO.getNhaCungCapByID(database,id);
        Glide.with(getActivity()).load(nhaCungCap.getAnh_url()).into(imgView);
        txtTen.setText(nhaCungCap.getTenNCC());
        txtDiaChi.setText("Địa chỉ: "+nhaCungCap.getDiaChi());
        txtSdt.setText("Số điện thoại: "+nhaCungCap.getDienThoai());

        SanPhamDAO sanPhamDAO=new SanPhamImp();
        sanPhamList=sanPhamDAO.getSanPhamByIDNCC(database,id);
        adapter=new ChiTietNhaCCAdapter(getActivity(),sanPhamList,this);
        rec_sanpham.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(getActivity(), DetailActivity.class);
        SanPham sanPham=sanPhamList.get(position);
        intent.putExtra("detail",sanPham);
        startActivity(intent);
    }
}
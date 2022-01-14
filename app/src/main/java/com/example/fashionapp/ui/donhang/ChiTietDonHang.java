package com.example.fashionapp.ui.donhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fashionapp.R;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.DonHangHasSPDAO;
import com.example.fashionapp.database.repository.DonHangHasSPImp;
import com.example.fashionapp.models.Donhang_has_sanpham;

import java.util.List;

public class ChiTietDonHang extends AppCompatActivity {

    RecyclerView rec_order_detail;
    ChiTietDonHangAdapter adapter;
    List<Donhang_has_sanpham> donhangHasSanphamList;
    Database database;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);

        rec_order_detail=findViewById(R.id.rec_order_detail);
        rec_order_detail.setLayoutManager(new LinearLayoutManager(this));

        mToolbar=findViewById(R.id.toolbar_order_detail);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        database=new Database(this);
        Intent intent=getIntent();
        int IDDH = intent.getIntExtra("IDDH",0);
        donhangHasSanphamList=getSP(IDDH);
        adapter=new ChiTietDonHangAdapter(this,donhangHasSanphamList);
        rec_order_detail.setAdapter(adapter);

    }

    private List<Donhang_has_sanpham> getSP(int IDDH){
        DonHangHasSPDAO donHangHasSPDAO=new DonHangHasSPImp();
        return donHangHasSPDAO.getSPByDH(database,IDDH);
    }
}
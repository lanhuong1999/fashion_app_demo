package com.example.fashionapp.ui.donhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.fashionapp.R;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.DonHangDAO;
import com.example.fashionapp.database.repository.DonHangImp;
import com.example.fashionapp.models.DonHang;
import com.example.fashionapp.models.KhachHang;
import com.example.fashionapp.ui.RecyclerViewClickItem;
import com.google.gson.Gson;

import java.util.List;

public class DonHangActivity extends AppCompatActivity implements RecyclerViewClickItem {

    RecyclerView rec_order;
    List<DonHang> donHangList;
    DonHangAdapter donHangAdapter;
    Database database;
    int IDKH;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);

        mToolbar=findViewById(R.id.toolbar_order);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        database=new Database(this);
        IDKH=getKH().getIDKH();
        rec_order=findViewById(R.id.rec_order);
        rec_order.setLayoutManager(new LinearLayoutManager(this));
        donHangList=getDH();
        donHangAdapter=new DonHangAdapter(this,donHangList,this);
        rec_order.setAdapter(donHangAdapter);

    }
    private List<DonHang> getDH(){
        DonHangDAO donHangDAO=new DonHangImp();
        return donHangDAO.getDHByID(database,IDKH);
    }
    private KhachHang getKH(){
        SharedPreferences preferences = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("current_kh", "");
        if(json!="") {
            KhachHang khachHang = gson.fromJson(json, KhachHang.class);
            return khachHang;
        }
        return null;
    }

    @Override
    public void onItemClick(int position) {
        DonHang donHang=donHangList.get(position);
        Intent intent=new Intent(this,ChiTietDonHang.class);
        intent.putExtra("IDDH", donHang.getIDDH());
        startActivity(intent);
    }
}
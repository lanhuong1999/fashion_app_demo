package com.example.fashionapp.ui.yeuthich;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fashionapp.R;
import com.example.fashionapp.activity.DetailActivity;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.SanPhamDAO;
import com.example.fashionapp.database.dao.YeuThichDAO;
import com.example.fashionapp.database.repository.SanPhamImp;
import com.example.fashionapp.database.repository.YeuThichImp;
import com.example.fashionapp.models.KhachHang;
import com.example.fashionapp.models.SanPham;
import com.example.fashionapp.ui.RecyclerViewClickItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class YeuThichActivity extends AppCompatActivity implements RecyclerViewClickItem {

    RecyclerView rec_yeuthich;
    YeuThichAdapter yeuThichAdapter;
    List<SanPham> sanPhamList;
    List<String> yeuThichList;
    Database database;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeu_thich);

        mToolbar = findViewById(R.id.toolbar_yeuthich);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        database=new Database(this);
        rec_yeuthich=findViewById(R.id.rec_yeuthich);
        rec_yeuthich.setLayoutManager(new LinearLayoutManager(this));
        sanPhamList=new ArrayList<>();
        yeuThichList=new ArrayList<>();

//        yeuThichList=getListYeuThich();
//        sanPhamList=getSP();
//        yeuThichAdapter=new YeuThichAdapter(this,sanPhamList,this);
//        rec_yeuthich.setAdapter(yeuThichAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        yeuThichList=getListYeuThich();
        sanPhamList=getSP();
        yeuThichAdapter=new YeuThichAdapter(this,sanPhamList,this);
        rec_yeuthich.setAdapter(yeuThichAdapter);
    }


    private List<SanPham> getSP(){
        List<SanPham> sanPhamList=new ArrayList<>();
        SanPhamDAO sanPhamDAO=new SanPhamImp();
        for(String id:yeuThichList){
            SanPham sp= sanPhamDAO.getSPbyID(database,id);
            sanPhamList.add(sp);
        }
        return sanPhamList;
    }
    private List<String> getListYeuThich(){
        List<String> listIDSP=new ArrayList<>();
        KhachHang khachHang=getKH();
        if(khachHang!=null){
            YeuThichDAO yeuThichDAO=new YeuThichImp();
            listIDSP=yeuThichDAO.getIDSPbyIDKH(database,khachHang.getIDKH());
        }
        return listIDSP;
    }

    private KhachHang getKH(){
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
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
        SanPham sanPham=sanPhamList.get(position);
        Intent intent=new Intent(this, DetailActivity.class);
        intent.putExtra("detail",sanPham);
        startActivity(intent);
    }
}
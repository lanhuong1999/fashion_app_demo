package com.example.fashionapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fashionapp.Common;
import com.example.fashionapp.R;
import com.example.fashionapp.activity.adapter.RecommendAdapter;
import com.example.fashionapp.api.ApiService;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.GioHangDAO;
import com.example.fashionapp.database.dao.SanPhamDAO;
import com.example.fashionapp.database.dao.YeuThichDAO;
import com.example.fashionapp.database.repository.GioHangImp;
import com.example.fashionapp.database.repository.SanPhamImp;
import com.example.fashionapp.database.repository.YeuThichImp;
import com.example.fashionapp.models.Ao;
import com.example.fashionapp.models.Balo;
import com.example.fashionapp.models.Giay;
import com.example.fashionapp.models.GioHang;
import com.example.fashionapp.models.KhachHang;
import com.example.fashionapp.models.PhuKien;
import com.example.fashionapp.models.Quan;
import com.example.fashionapp.models.SanPham;
import com.example.fashionapp.models.SanPhamRecommend;
import com.example.fashionapp.models.YeuThich;
import com.example.fashionapp.ui.RecyclerViewClickItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements RecyclerViewClickItem {

    ImageView pro_img,img_inc, img_dec;
    TextView name, des, price, quantity;
    Button btn_add_cart;
    ImageView favourite;
    RecyclerView recyclerView_rcm;
    RecommendAdapter rcmAdapter;
    List<SanPham> list_rcm;
    Toolbar toolbar;

    int count = 0;
    boolean isFavourite;
    String IDSP;
    int donGia;
    Database database;
    String kichthuoc;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        pro_img=findViewById(R.id.detail_img);
        img_inc=findViewById(R.id.detail_btn_add);
        img_dec=findViewById(R.id.detail_btn_dec);
        name=findViewById(R.id.detail_name);
        des=findViewById(R.id.detail_des);
        price=findViewById(R.id.detail_price);
        quantity=findViewById(R.id.detail_quantity);
        btn_add_cart=findViewById(R.id.detail_btn_add_to_cart);
        favourite=findViewById(R.id.detail_ratingbar);

        recyclerView_rcm=findViewById(R.id.detail_rcm);
        recyclerView_rcm.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        list_rcm=new ArrayList<>();
        rcmAdapter=new RecommendAdapter(this,list_rcm,this);
        recyclerView_rcm.setAdapter(rcmAdapter);

        database=new Database(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar_detail);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SanPham sanPham= (SanPham) getIntent().getSerializableExtra("detail");
        Ao ao= (Ao) getIntent().getSerializableExtra("ao_detail");
        Quan quan= (Quan) getIntent().getSerializableExtra("quan_detail");
        PhuKien phuKien= (PhuKien) getIntent().getSerializableExtra("phukien_detail");
        Balo balo= (Balo) getIntent().getSerializableExtra("balo_detail");
        Giay giay=(Giay) getIntent().getSerializableExtra("giay_detail");
        
        if(sanPham!=null){
            Glide.with(getApplicationContext()).load(sanPham.getImg_url()).into(pro_img);
            name.setText(sanPham.getTen());
            des.setText(sanPham.getMoTa());
            price.setText(Common.formatGia(sanPham.getGia()) +" đ");
            IDSP=sanPham.getIDSP();
            donGia=sanPham.getGia();

        }
        if(ao!=null){
            Glide.with(getApplicationContext()).load(ao.getImg_url()).into(pro_img);
            name.setText(ao.getTen());
            des.setText(ao.getMoTa());
            price.setText(Common.formatGia(ao.getGia())+" đ");
            IDSP=ao.getIDSP();
            donGia=ao.getGia();
        }
        if(quan!=null){
            Glide.with(getApplicationContext()).load(quan.getImg_url()).into(pro_img);
            name.setText(quan.getTen());
            des.setText(quan.getMoTa());
            price.setText(Common.formatGia(quan.getGia())+" đ");
            IDSP=quan.getIDSP();
            donGia= quan.getGia();
        }
        if(phuKien!=null){
            Glide.with(getApplicationContext()).load(phuKien.getImg_url()).into(pro_img);
            name.setText(phuKien.getTen());
            des.setText(phuKien.getMoTa());
            price.setText(Common.formatGia(phuKien.getGia())+" đ");
            IDSP=phuKien.getIDSP();
            donGia=phuKien.getGia();
        }
        if(balo!=null){
            Glide.with(getApplicationContext()).load(balo.getImg_url()).into(pro_img);
            name.setText(balo.getTen());
            des.setText(balo.getMoTa());
            price.setText(Common.formatGia(balo.getGia())+" đ");
            IDSP=balo.getIDSP();
            donGia=balo.getGia();
        }
        if(giay!=null){
            Glide.with(getApplicationContext()).load(giay.getImg_url()).into(pro_img);
            name.setText(giay.getTen());
            des.setText(giay.getMoTa());
            price.setText(Common.formatGia(giay.getGia())+" đ");
            IDSP=giay.getIDSP();
            donGia=giay.getGia();
        }
        kichthuoc=getSize();

        if(!kichthuoc.equals(""))
            des.append("\nKích thước: "+kichthuoc);

        new GetDataTask().execute();

        isFavourite=getFavourite();
        if(isFavourite){
            favourite.setImageResource(R.drawable.heart_enablle);
        }
        else{
            favourite.setImageResource(R.drawable.heart_disable);
        }

        favourite.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                if(!isFavourite){
                    favourite.setImageResource(R.drawable.heart_enablle);
                    isFavourite=true;
                    addToYeuThich();
                }
                else {
                    favourite.setImageResource(R.drawable.heart_disable);
                    isFavourite=false;
                    removeToYeuThich();
                }
            }
        });

        img_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ++count;
                    quantity.setText(String.valueOf(count));
            }
        });

        img_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>0){
                    --count;
                    quantity.setText(String.valueOf(count));
                }
            }
        });

        btn_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>0) {
                   addToCart();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Vui lòng chọn lại số lượng sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private String getSize(){
        SanPhamDAO sanPhamDAO=new SanPhamImp();
        SanPham sanPham=sanPhamDAO.getSPbyID(database,IDSP);
        if(sanPham.getLoai().equals("Áo")){
            return sanPhamDAO.getSize(database,IDSP,"ao");
        }
        else if(sanPham.getLoai().equals("Quần")){
            return sanPhamDAO.getSize(database,IDSP,"quan");
        }
        else if(sanPham.getLoai().equals("Giày")){
            return sanPhamDAO.getSize(database,IDSP,"giay");
        }
        else if(sanPham.getLoai().equals("Ba lô")){
            return sanPhamDAO.getSize(database,IDSP,"balo");
        }
        else return "";
    }

    private void removeToYeuThich() {
        KhachHang khachHang=getKH();
        if(khachHang!=null){
            YeuThich yeuThich=new YeuThich(khachHang.getIDKH(),IDSP);
            YeuThichDAO yeuThichDAO=new YeuThichImp();
            yeuThichDAO.xoaYeuThich(database,yeuThich);
        }
    }

    private void addToYeuThich() {
        KhachHang khachHang=getKH();
        if(khachHang!=null){
            YeuThich yeuThich=new YeuThich(khachHang.getIDKH(),IDSP);
            YeuThichDAO yeuThichDAO=new YeuThichImp();
            yeuThichDAO.themYeuThich(database,yeuThich);
        }
    }

    private boolean getFavourite(){
        KhachHang khachHang=getKH();
        if(khachHang!=null){
            YeuThichDAO yeuThichDAO=new YeuThichImp();
            List<String> listFavourite=yeuThichDAO.getIDSPbyIDKH(database,khachHang.getIDKH());
            if(listFavourite.contains(IDSP)) return true;
            return false;
        }
        return false;
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

    private void addToCart() {
        KhachHang khachHang=getKH();
        if(khachHang!=null){
            GioHangDAO gioHangDAO =new GioHangImp();
            List<GioHang> list=gioHangDAO.getGioHang(database,khachHang.getIDKH());
            boolean c=false;
            for(GioHang g:list){
                if(g.getIDSP().equals(IDSP)){
                    c=true;
                    int new_count=count+g.getSoluong();
                    g.setSoluong(new_count);
                    g.setTongTien(new_count*donGia);
                    gioHangDAO.capNhatGioHang(database,g);
                    break;
                }
            }
            if(!c) {
                GioHang gioHang = new GioHang(0, khachHang.getIDKH(), IDSP, count, count * donGia);
                gioHangDAO.themGioHang(database, gioHang);
                Intent intent=new Intent("action_cart");
                intent.putExtra("key","add");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            }
            Toast.makeText(this,"Thêm vào giỏ hàng thành công",Toast.LENGTH_SHORT).show();
        }
    }

    private void setListRecommend(SanPhamRecommend sanPhamRecommend) {
        SanPhamDAO sanPhamDAO=new SanPhamImp();

        list_rcm.add(sanPhamDAO.getSPbyID(database,sanPhamRecommend.getItem1()));
        list_rcm.add(sanPhamDAO.getSPbyID(database,sanPhamRecommend.getItem2()));
        list_rcm.add(sanPhamDAO.getSPbyID(database,sanPhamRecommend.getItem3()));
        list_rcm.add(sanPhamDAO.getSPbyID(database,sanPhamRecommend.getItem4()));
        list_rcm.add(sanPhamDAO.getSPbyID(database,sanPhamRecommend.getItem5()));
        rcmAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(this,DetailActivity.class);
        intent.putExtra("detail",list_rcm.get(position));
        startActivity(intent);
    }


    class GetDataTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SanPhamRecommend sp=new SanPhamRecommend();
            int id=Integer.parseInt(IDSP);
            Log.d("TLH", "callApi: "+id);

            ApiService.apiService.getRecommend(id).enqueue(new Callback<SanPhamRecommend>() {
                @Override
                public void onResponse(Call<SanPhamRecommend> call, Response<SanPhamRecommend> response) {
                    Toast.makeText(DetailActivity.this,"Call api success",Toast.LENGTH_SHORT).show();
                    SanPhamRecommend sanPhamRecommend=response.body();
                    setListRecommend(sanPhamRecommend);
                }

                @Override
                public void onFailure(Call<SanPhamRecommend> call, Throwable t) {
                    Toast.makeText(DetailActivity.this,"Call api fail",Toast.LENGTH_SHORT).show();

                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
package com.example.fashionapp.database.repository;

import android.database.Cursor;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.SanPhamDAO;
import com.example.fashionapp.models.Ao;
import com.example.fashionapp.models.Balo;
import com.example.fashionapp.models.Giay;
import com.example.fashionapp.models.PhuKien;
import com.example.fashionapp.models.Quan;
import com.example.fashionapp.models.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamImp implements SanPhamDAO {
    @Override
    public List<SanPham> getSanPham(Database database) {
        List<SanPham> sanPhamList=new ArrayList<>();

        Cursor cursor = database.getData("SELECT * FROM sanpham");
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String ten=cursor.getString(1);
            String image_url=cursor.getString(2);
            int soluong=cursor.getInt(3);
            String mota=cursor.getString(4);
            String mau =cursor.getString(5);
            int gia=cursor.getInt(6);
            String IDNCC=cursor.getString(7);
            String loai=cursor.getString(8);

            sanPhamList.add(new SanPham(id,ten,image_url,soluong,mota,mau,gia,IDNCC,loai));

        }
        return sanPhamList;
    }

    @Override
    public List<SanPham> getSanPhamByIDNCC(Database database, String IDNCC) {
        List<SanPham> sanPhamList=new ArrayList<>();

        Cursor cursor = database.getData("SELECT * from sanpham WHERE IDNCC= '"+IDNCC+"'");
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String ten=cursor.getString(1);
            String image_url=cursor.getString(2);
            int soluong=cursor.getInt(3);
            String mota=cursor.getString(4);
            String mau =cursor.getString(5);
            int gia=cursor.getInt(6);
            String loai=cursor.getString(8);
            sanPhamList.add(new SanPham(id,ten,image_url,soluong,mota,mau,gia,IDNCC,loai));

        }
        return sanPhamList;
    }

    @Override
    public List<Ao> getSanPhamAo(Database database) {
        List<Ao> aoList=new ArrayList<>();
        Cursor cursor =database.getData("SELECT * FROM sanpham INNER JOIN ao on sanpham.IDSP=ao.sanpham_IDSP");
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String ten=cursor.getString(1);
            String image_url=cursor.getString(2);
            int soluong=cursor.getInt(3);
            String mota=cursor.getString(4);
            String mau =cursor.getString(5);
            int gia=cursor.getInt(6);
            String IDNCC=cursor.getString(7);
            String loai=cursor.getString(8);
            String loaiAo=cursor.getString(9);
            String mua=cursor.getString(10);
            String size=cursor.getString(11);

            aoList.add(new Ao(id,ten,image_url,soluong,mota,mau,gia,IDNCC,loai,loaiAo,mua,size));
        }
        return aoList;
    }

    @Override
    public List<Quan> getSanPhamQuan(Database database) {

        List<Quan> quanList=new ArrayList<>();
        Cursor cursor =database.getData("SELECT * FROM sanpham INNER JOIN quan on sanpham.IDSP=quan.sanpham_IDSP");
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String ten=cursor.getString(1);
            String image_url=cursor.getString(2);
            int soluong=cursor.getInt(3);
            String mota=cursor.getString(4);
            String mau =cursor.getString(5);
            int gia=cursor.getInt(6);
            String IDNCC=cursor.getString(7);
            String loai=cursor.getString(8);
            String loaiQuan=cursor.getString(10);
            String mua=cursor.getString(12);
            String size=cursor.getString(11);

            quanList.add(new Quan(id,ten,image_url,soluong,mota,mau,gia,IDNCC,loai,loaiQuan,size,mua));
        }

        return quanList;
    }

    @Override
    public List<Giay> getSanPhamGiay(Database database) {

        List<Giay> giayList = new ArrayList<>();
        Cursor cursor =database.getData("SELECT * FROM sanpham INNER JOIN giay on sanpham.IDSP=giay.sanpham_IDSP");
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String ten=cursor.getString(1);
            String image_url=cursor.getString(2);
            int soluong=cursor.getInt(3);
            String mota=cursor.getString(4);
            String mau =cursor.getString(5);
            int gia=cursor.getInt(6);
            String IDNCC=cursor.getString(7);
            String loai=cursor.getString(8);
            String size=cursor.getString(9);

            giayList.add(new Giay(id,ten,image_url,soluong,mota,mau,gia,IDNCC,loai,size));
        }
        return giayList;
    }

    @Override
    public List<PhuKien> getSanPhamPhuKien(Database database) {
        List<PhuKien> phuKienList=new ArrayList<>();
        Cursor cursor =database.getData("SELECT * FROM sanpham INNER JOIN phukien on sanpham.IDSP=phukien.sanpham_IDSP");
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String ten=cursor.getString(1);
            String image_url=cursor.getString(2);
            int soluong=cursor.getInt(3);
            String mota=cursor.getString(4);
            String mau =cursor.getString(5);
            int gia=cursor.getInt(6);
            String IDNCC=cursor.getString(7);
            String loai=cursor.getString(8);
            String chatlieu=cursor.getString(10);
            String loaiPK=cursor.getString(11);

            phuKienList.add(new PhuKien(id,ten,image_url,soluong,mota,mau,gia,IDNCC,loai,chatlieu,loaiPK));
        }

        return phuKienList;
    }

    @Override
    public List<Balo> getSanPhamBalo(Database database) {
        List<Balo> balos=new ArrayList<>();
        Cursor cursor =database.getData("SELECT * FROM sanpham INNER JOIN balo on sanpham.IDSP=balo.sanpham_IDSP");
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String ten=cursor.getString(1);
            String image_url=cursor.getString(2);
            int soluong=cursor.getInt(3);
            String mota=cursor.getString(4);
            String mau =cursor.getString(5);
            int gia=cursor.getInt(6);
            String IDNCC=cursor.getString(7);
            String loai=cursor.getString(8);
            String size=cursor.getString(9);
            int songan=cursor.getInt(10);

            balos.add(new Balo(id,ten,image_url,soluong,mota,mau,gia,IDNCC,loai,size,songan));
        }

        return balos;
    }

    @Override
    public SanPham getSPbyID(Database database, String idsp) {
        Cursor cursor = database.getData("SELECT * FROM sanpham WHERE IDSP='"+idsp+"'");
        SanPham sanPham=null;
        if(cursor.getCount()>0){
            cursor.moveToNext();
            String ten=cursor.getString(1);
            String img=cursor.getString(2);
            int soluong=cursor.getInt(3);
            String des=cursor.getString(4);
            String mau=cursor.getString(5);
            int gia=cursor.getInt(6);
            String IDNCC=cursor.getString(7);
            String loai=cursor.getString(8);
            sanPham=new SanPham(idsp,ten,img,soluong,des,mau,gia,IDNCC,loai);
        }
        return sanPham;
    }

    @Override
    public String getSize(Database database, String IDSP, String table_name) {
        String size="";
        Cursor cursor=database.getData("SELECT Size from sanpham JOIN '"+table_name+"' " +
                "WHERE IDSP= '"+IDSP+"' and sanpham_IDSP='"+IDSP+"'");
        if(cursor.getCount()>0){
            cursor.moveToNext();
            size=cursor.getString(0);
        }
        return size;
    }


}

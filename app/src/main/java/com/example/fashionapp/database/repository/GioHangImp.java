package com.example.fashionapp.database.repository;

import android.database.Cursor;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.GioHangDAO;
import com.example.fashionapp.models.GioHang;

import java.util.ArrayList;
import java.util.List;

public class GioHangImp implements GioHangDAO {


    @Override
    public void themGioHang(Database database, GioHang gioHang) {
        database.queryData("INSERT INTO giohang VALUES(null,'"+gioHang.getIDKH()+"','"+gioHang.getIDSP()+"'," +
                "'"+gioHang.getSoluong()+"','"+gioHang.getTongTien()+"')");
    }

    @Override
    public List<GioHang> getGioHang(Database database,int IDKH) {
        List<GioHang> gioHangList=new ArrayList<>();
        Cursor cursor=database.getData("SELECT * FROM giohang WHERE IDKH= '"+IDKH+"' ");
        while(cursor.moveToNext()){
            int IDGH=cursor.getInt(0);
            String IDSP=cursor.getString(2);
            int soluong=cursor.getInt(3);
            int tong=cursor.getInt(4);

            gioHangList.add(new GioHang(IDGH,IDKH,IDSP,soluong,tong));
        }
        return gioHangList;
    }

    @Override
    public void capNhatGioHang(Database database, GioHang gioHang) {
        database.queryData("UPDATE giohang SET Soluong= '"+gioHang.getSoluong()+"', Tongtien= '"+gioHang.getTongTien()+"' " +
                "WHERE IDGH= '"+gioHang.getIDGH()+"'");
    }

    @Override
    public void xoaGioHang(Database database, GioHang gioHang) {
        database.queryData("DELETE FROM giohang WHERE IDGH= '"+gioHang.getIDGH()+"' ");
    }
    
}

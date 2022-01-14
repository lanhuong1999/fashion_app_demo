package com.example.fashionapp.database.repository;

import android.database.Cursor;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.DonHangDAO;
import com.example.fashionapp.models.DonHang;

import java.util.ArrayList;
import java.util.List;

public class DonHangImp implements DonHangDAO {
    @Override
    public void themDonHang(Database database, DonHang donHang) {
        database.queryData("INSERT INTO donhang VALUES(null,'"+donHang.getIDKH()+"', '"+donHang.getTongTien()+"'," +
                " '"+donHang.getSđt()+"', '"+donHang.getDate()+"' , '"+donHang.getDiachi()+"' ," +
                "'"+donHang.getSoluong()+"','"+donHang.getThanhtoan()+"', '"+donHang.getIDMaKM()+"')");
    }

    @Override
    public int getIDDHCurrent(Database database, int IDKH) {
        Cursor cursor=database.getData("SELECT IDDH FROM donhang WHERE IDDH=(SELECT max(IDDH) FROM donhang WHERE khachhang_IDKH= '"+IDKH+"')");
        if (cursor.getCount()>0){
            cursor.moveToNext();
            return cursor.getInt(0);
        }
        return 0;
    }

    @Override
    public List<DonHang> getDHByID(Database database, int IDKH) {
        Cursor cursor=database.getData("SELECT * FROM donhang WHERE khachhang_IDKH= '"+IDKH+"'");
        List<DonHang> donHangList=new ArrayList<>();
        while (cursor.moveToNext()){
            int IDDH=cursor.getInt(0);
            int khachhang_IDKH=cursor.getInt(1);
            int total=cursor.getInt(2);
            String sdt=cursor.getString(3);
            String time=cursor.getString(4);
            String add=cursor.getString(5);
            int soluong=cursor.getInt(6);
            String pay=cursor.getString(7);
            int idkm=cursor.getInt(8);
            donHangList.add(new DonHang(IDDH,khachhang_IDKH,total,sdt,time,add,soluong,pay,idkm));
        }
        return donHangList;
    }
}

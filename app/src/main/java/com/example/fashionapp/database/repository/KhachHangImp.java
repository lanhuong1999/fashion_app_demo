package com.example.fashionapp.database.repository;

import android.database.Cursor;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.KhachHangDAO;
import com.example.fashionapp.models.KhachHang;

public class KhachHangImp implements KhachHangDAO {
    @Override
    public void themKhachHang(Database database,KhachHang khachHang) {
        database.queryData("INSERT INTO khachhang VALUES (null,'"+khachHang.getTen()+"', '"+khachHang.getDiaChi()+"', '"+khachHang.getEmail()+"','"+khachHang.getTaikhoan_TenDN()+"', null)");
    }

    @Override
    public KhachHang getKHbyTenDN(Database database, String username) {
        KhachHang khachHang=new KhachHang();
        Cursor cursor=database.getData("SELECT * FROM khachhang WHERE taikhoan_TenDN= '"+username+"' ");
        if (cursor.getCount()>0){
            cursor.moveToNext();
            int id=cursor.getInt(0);
            String ten=cursor.getString(1);
            String add=cursor.getString(2);
            String email=cursor.getString(3);
            String sdt=cursor.getString(5);
            khachHang=new KhachHang(id,ten,add,email,username,sdt);
        }
        return khachHang;
    }

    @Override
    public void updateKH(Database database, KhachHang khachHang) {
        database.queryData("UPDATE khachhang SET Ten = '"+khachHang.getTen()+"' , DiaChi= '"+khachHang.getDiaChi()+"'," +
                "Email= '"+khachHang.getEmail()+"', SoDienThoai= '"+khachHang.getSdt()+"' WHERE IDKH=  '"+khachHang.getIDKH()+"' ");
    }

}

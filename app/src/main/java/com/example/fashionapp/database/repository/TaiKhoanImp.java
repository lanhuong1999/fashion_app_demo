package com.example.fashionapp.database.repository;

import android.database.Cursor;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.TaiKhoanDAO;
import com.example.fashionapp.models.TaiKhoan;

public class TaiKhoanImp implements TaiKhoanDAO {


    @Override
    public boolean checkTaiKhoan(Database database, String username) {
        Cursor cursor=database.getData("SELECT TenDN FROM taikhoan WHERE TenDN='"+username+"'");
        if(cursor.getCount()>0) return true;
        return false;
    }

    @Override
    public void themeTaiKhoan(Database database, TaiKhoan taiKhoan) {
        database.queryData("INSERT INTO taikhoan VALUES ('"+taiKhoan.getTenDN()+"','"+taiKhoan.getMatKhau()+"')");
    }

    @Override
    public boolean checkLogin(Database database, TaiKhoan taiKhoan) {
        Cursor cursor=database.getData("SELECT Matkhau FROM taikhoan WHERE TenDN='"+taiKhoan.getTenDN()+"'");

        if(cursor.getCount()>0) {
            cursor.moveToNext();
            if(cursor.getString(0).equals(taiKhoan.getMatKhau()))
                return true;
            else return false;
        }
        return false;
    }

    @Override
    public void capNhatMatKhau(Database database, TaiKhoan taiKhoan) {
        database.queryData("UPDATE taikhoan set Matkhau= '"+taiKhoan.getMatKhau()+"' where TenDN= '"+taiKhoan.getTenDN()+"' ");
    }
}

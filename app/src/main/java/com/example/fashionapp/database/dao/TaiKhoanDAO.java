package com.example.fashionapp.database.dao;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.models.TaiKhoan;

public interface TaiKhoanDAO {
    public boolean checkTaiKhoan(Database database, String username);
    public void themeTaiKhoan(Database database, TaiKhoan taiKhoan);
    public boolean checkLogin(Database database, TaiKhoan taiKhoan);
    void capNhatMatKhau(Database database, TaiKhoan taiKhoan);
}

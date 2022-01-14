package com.example.fashionapp.database.dao;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.models.KhachHang;

public interface KhachHangDAO {
    public void themKhachHang(Database database,KhachHang khachHang);
    public KhachHang getKHbyTenDN(Database database, String username);
    public void updateKH(Database database, KhachHang khachHang);
}

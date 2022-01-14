package com.example.fashionapp.database.dao;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.models.GioHang;

import java.util.List;

public interface GioHangDAO {
    public void themGioHang(Database database,GioHang gioHang);
    public List<GioHang> getGioHang(Database database, int IDKH);
    public void capNhatGioHang(Database database, GioHang gioHang);
    public void xoaGioHang(Database database, GioHang gioHang);
}

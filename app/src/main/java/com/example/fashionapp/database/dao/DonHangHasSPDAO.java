package com.example.fashionapp.database.dao;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.models.Donhang_has_sanpham;

import java.util.List;

public interface DonHangHasSPDAO {
    public void themSanPhamDonHang(Database database, Donhang_has_sanpham donHangSanPham);
    public List<Donhang_has_sanpham> getSPByDH(Database database, int IDDH);
}

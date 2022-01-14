package com.example.fashionapp.database.dao;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.models.Ao;
import com.example.fashionapp.models.Balo;
import com.example.fashionapp.models.Giay;
import com.example.fashionapp.models.PhuKien;
import com.example.fashionapp.models.Quan;
import com.example.fashionapp.models.SanPham;

import java.util.List;

public interface SanPhamDAO {
    public List<SanPham> getSanPham(Database database);
    public List<SanPham> getSanPhamByIDNCC(Database database, String IDNCC);
    public List<Ao> getSanPhamAo(Database database);
    public List<Quan> getSanPhamQuan(Database database);
    public List<Giay> getSanPhamGiay(Database database);
    public List<PhuKien> getSanPhamPhuKien(Database database);
    public List<Balo> getSanPhamBalo(Database database);
    public SanPham getSPbyID(Database database, String idsp);
    public String getSize(Database database, String IDSP, String table_name);

}

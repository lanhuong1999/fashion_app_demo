package com.example.fashionapp.database.dao;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.models.DonHang;

import java.util.List;

public interface DonHangDAO {
    public void themDonHang(Database database, DonHang donHang);
    public int getIDDHCurrent(Database database, int IDKH);
    public List<DonHang> getDHByID(Database database, int IDKH);
}

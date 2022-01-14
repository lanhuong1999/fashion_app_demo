package com.example.fashionapp.database.dao;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.models.YeuThich;

import java.util.List;

public interface YeuThichDAO {
    public List<String> getIDSPbyIDKH(Database database,int IDKH);
    public void themYeuThich(Database database, YeuThich yeuThich);
    public void xoaYeuThich(Database database, YeuThich yeuThich);
}

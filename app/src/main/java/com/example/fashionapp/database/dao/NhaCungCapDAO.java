package com.example.fashionapp.database.dao;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.models.NhaCungCap;

import java.util.List;

public interface NhaCungCapDAO {
    public List<NhaCungCap> getNhaCungCap(Database database);
    public NhaCungCap getNhaCungCapByID(Database database, String id);
}

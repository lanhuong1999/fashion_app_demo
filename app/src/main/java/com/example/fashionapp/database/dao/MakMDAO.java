package com.example.fashionapp.database.dao;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.models.MaKM;

import java.util.List;

public interface MakMDAO {
    public List<MaKM> getMaKM(Database database);
    public MaKM getMaKMByID(Database database, int id);
    public void capNhatMa(Database database, MaKM maKM);
}

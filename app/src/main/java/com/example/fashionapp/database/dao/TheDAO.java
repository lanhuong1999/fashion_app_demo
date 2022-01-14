package com.example.fashionapp.database.dao;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.models.The;

import java.util.List;

public interface TheDAO {
    List<The> getTheByIDKH(Database database, int IDKH);
    void themThe(Database database, The the);
    void capNhatThe(Database database, The the, The updateThe);
    void xoaThe(Database database, The the);
}

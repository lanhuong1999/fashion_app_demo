package com.example.fashionapp.database.repository;

import android.database.Cursor;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.YeuThichDAO;
import com.example.fashionapp.models.YeuThich;

import java.util.ArrayList;
import java.util.List;

public class YeuThichImp implements YeuThichDAO {
    @Override
    public List<String> getIDSPbyIDKH(Database database,int IDKH) {
        List<String> listIDSP=new ArrayList<>();
        Cursor cursor=database.getData("SELECT * FROM yeuthich WHERE IDKH= '"+IDKH+"' ");
        while(cursor.moveToNext()){
            String IDSP=cursor.getString(1);
            listIDSP.add(IDSP);
        }
        return listIDSP;
    }

    @Override
    public void themYeuThich(Database database, YeuThich yeuThich) {
        database.queryData("INSERT INTO yeuthich VALUES ( '"+yeuThich.getIDKH()+"','"+yeuThich.getIDSP()+"')");
    }

    @Override
    public void xoaYeuThich(Database database, YeuThich yeuThich) {
        database.queryData("DELETE FROM yeuthich WHERE IDKH = '"+yeuThich.getIDKH()+"' AND IDSP= '"+yeuThich.getIDSP()+"'");
    }
}

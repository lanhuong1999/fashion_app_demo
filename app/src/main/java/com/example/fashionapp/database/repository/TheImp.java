package com.example.fashionapp.database.repository;

import android.database.Cursor;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.TheDAO;
import com.example.fashionapp.models.The;

import java.util.ArrayList;
import java.util.List;

public class TheImp implements TheDAO {
    @Override
    public List<The> getTheByIDKH(Database database, int IDKH) {
        List<The> theList=new ArrayList<>();
        Cursor cursor=database.getData("SELECT * FROM the WHERE IDKH= '"+IDKH+"'");

        while(cursor.moveToNext()){
            String stk=cursor.getString(1);
            String tennh=cursor.getString(2);
            theList.add(new The(IDKH,stk,tennh));
        }

        return theList;
    }

    @Override
    public void themThe(Database database, The the) {
        database.queryData("INSERT INTO the VALUES ('"+the.getIDKH()+"', '"+the.getSoTK()+"','"+the.getTenNH()+"')");
    }

    @Override
    public void capNhatThe(Database database, The the, The updateThe) {
        database.queryData("UPDATE the SET SoTK= '"+updateThe.getSoTK()+"' , TenNH= '"+updateThe.getTenNH()+"'" +
                "WHERE IDKH= '"+the.getIDKH()+"' AND SoTK= '"+the.getSoTK()+"' AND TenNH= '"+the.getTenNH()+"'");
    }

    @Override
    public void xoaThe(Database database, The the) {
        database.queryData("DELETE FROM the WHERE IDKH= '"+the.getIDKH()+"' AND SoTK= '"+the.getSoTK()+"' " +
                "AND TenNH= '"+the.getTenNH()+"'  ");
    }
}

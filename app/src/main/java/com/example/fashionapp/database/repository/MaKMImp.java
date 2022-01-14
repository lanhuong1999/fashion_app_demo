package com.example.fashionapp.database.repository;

import android.database.Cursor;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.MakMDAO;
import com.example.fashionapp.models.MaKM;

import java.util.ArrayList;
import java.util.List;

public class MaKMImp implements MakMDAO {
    @Override
    public List<MaKM> getMaKM(Database database) {
        List<MaKM> list= new ArrayList<>();
        Cursor cursor =database.getData("SELECT * FROM makm");
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            int gia=cursor.getInt(1);
            int soluong=cursor.getInt(2);
            list.add(new MaKM(id,gia,soluong));
        }
        return list;
    }

    @Override
    public MaKM getMaKMByID(Database database, int id) {
        MaKM maKM=new MaKM();
        Cursor cursor=database.getData("SELECT * FROM makm WHERE IDKM= '"+id+"'");
        if(cursor.getCount()>0){
            cursor.moveToNext();
            int idMa=cursor.getInt(0);
            int giam=cursor.getInt(1);
            int soluong=cursor.getInt(2);
            maKM=new MaKM(idMa,giam,soluong);
        }
        return maKM;
    }

    @Override
    public void capNhatMa(Database database, MaKM maKM) {
        database.queryData("UPDATE makm SET Soluong= '"+(maKM.getSoLuong()-1)+"' WHERE IDKM= '"+maKM.getId()+"'");
    }
}

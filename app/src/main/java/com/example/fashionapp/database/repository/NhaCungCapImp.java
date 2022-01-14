package com.example.fashionapp.database.repository;

import android.database.Cursor;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.NhaCungCapDAO;
import com.example.fashionapp.models.NhaCungCap;

import java.util.ArrayList;
import java.util.List;

public class NhaCungCapImp implements NhaCungCapDAO {
    @Override
    public List<NhaCungCap> getNhaCungCap(Database database) {
        List<NhaCungCap> nhaCungCapList=new ArrayList<>();
        Cursor cursor = database.getData("SELECT * FROM nhacungcap");
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String ten=cursor.getString(1);
            String diachi=cursor.getString(2);
            String dienthoai=cursor.getString(3);
            String img_url=cursor.getString(4);

            nhaCungCapList.add(new NhaCungCap(id,ten,diachi,dienthoai,img_url));
        }
        return nhaCungCapList;
    }

    @Override
    public NhaCungCap getNhaCungCapByID(Database database, String id) {
        NhaCungCap nhaCungCap=new NhaCungCap();
        Cursor cursor=database.getData("SELECT * from nhacungcap WHERE IDNCC= '"+id+"'");
        if(cursor.getCount()>0){
            cursor.moveToNext();
            String ten=cursor.getString(1);
            String diachi=cursor.getString(2);
            String dienthoai=cursor.getString(3);
            String img_url=cursor.getString(4);

            nhaCungCap=new NhaCungCap(id,ten,diachi,dienthoai,img_url);
        }
        return nhaCungCap;
    }
}

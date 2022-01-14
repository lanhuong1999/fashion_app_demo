package com.example.fashionapp.database.repository;

import android.database.Cursor;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.DonHangHasSPDAO;
import com.example.fashionapp.models.Donhang_has_sanpham;

import java.util.ArrayList;
import java.util.List;

public class DonHangHasSPImp implements DonHangHasSPDAO {
    @Override
    public void themSanPhamDonHang(Database database, Donhang_has_sanpham donHangSanPham) {
        database.queryData("INSERT INTO donhang_has_sanpham VALUES ('"+donHangSanPham.getDonhang_IDDH()+"', " +
                "'"+donHangSanPham.getIDSP()+"','"+donHangSanPham.getSoluong()+"')");
    }

    @Override
    public List<Donhang_has_sanpham> getSPByDH(Database database, int IDDH) {
        Cursor cursor=database.getData("SELECT * from donhang_has_sanpham WHERE donhang_IDDH= '"+IDDH+"' ");
        List<Donhang_has_sanpham> list=new ArrayList<>();
        while (cursor.moveToNext()){
            String IDSP=cursor.getString(1);
            int sl=cursor.getInt(2);
            list.add(new Donhang_has_sanpham(IDDH,IDSP,sl));
        }
        return list;
    }
}

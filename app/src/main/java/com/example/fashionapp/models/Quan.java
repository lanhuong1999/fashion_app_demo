package com.example.fashionapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class Quan extends SanPham implements Serializable{
    String loaiQuan;
    String size;
    String mua;

    public Quan(String IDSP, String ten, String img_url, int soLuong, String moTa, String mau, int gia, String IDNCC, String loai, String loaiQuan, String size, String mua) {
        super(IDSP, ten, img_url, soLuong, moTa, mau, gia, IDNCC, loai);
        this.loaiQuan = loaiQuan;
        this.size = size;
        this.mua = mua;
    }

    public String getLoaiQuan() {
        return loaiQuan;
    }

    public void setLoaiQuan(String loaiQuan) {
        this.loaiQuan = loaiQuan;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMua() {
        return mua;
    }

    public void setMua(String mua) {
        this.mua = mua;
    }
}

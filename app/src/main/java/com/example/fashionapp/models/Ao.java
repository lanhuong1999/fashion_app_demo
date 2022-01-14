package com.example.fashionapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;


public class Ao extends SanPham implements Serializable {
    String loaiAo;
    String mua;
    String size;

    public Ao(String IDSP, String ten, String img_url, int soLuong, String moTa, String mau, int gia, String IDNCC, String loai, String loaiAo, String mua, String size) {
        super(IDSP, ten, img_url, soLuong, moTa, mau, gia, IDNCC, loai);
        this.loaiAo = loaiAo;
        this.mua = mua;
        this.size = size;
    }

    public String getLoaiAo() {
        return loaiAo;
    }

    public void setLoaiAo(String loaiAo) {
        this.loaiAo = loaiAo;
    }

    public String getMua() {
        return mua;
    }

    public void setMua(String mua) {
        this.mua = mua;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

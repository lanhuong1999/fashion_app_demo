package com.example.fashionapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class PhuKien extends SanPham implements Serializable{
   String chatLieu;
   String loaiPK;

    public PhuKien(String IDSP, String ten, String img_url, int soLuong, String moTa, String mau, int gia, String IDNCC, String loai, String chatLieu, String loaiPK) {
        super(IDSP, ten, img_url, soLuong, moTa, mau, gia, IDNCC, loai);
        this.chatLieu = chatLieu;
        this.loaiPK = loaiPK;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getLoaiPK() {
        return loaiPK;
    }

    public void setLoaiPK(String loaiPK) {
        this.loaiPK = loaiPK;
    }
}

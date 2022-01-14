package com.example.fashionapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class Balo extends SanPham implements Serializable {
    String kichThuoc;
    int soNgan;

    public Balo(String IDSP, String ten, String img_url, int soLuong, String moTa, String mau, int gia, String IDNCC, String loai, String kichThuoc, int soNgan) {
        super(IDSP, ten, img_url, soLuong, moTa, mau, gia, IDNCC, loai);
        this.kichThuoc = kichThuoc;
        this.soNgan = soNgan;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public int getSoNgan() {
        return soNgan;
    }

    public void setSoNgan(int soNgan) {
        this.soNgan = soNgan;
    }

}

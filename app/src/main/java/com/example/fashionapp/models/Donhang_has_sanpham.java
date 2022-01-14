package com.example.fashionapp.models;

import java.io.Serializable;

public class Donhang_has_sanpham implements Serializable {
    int donhang_IDDH;
    String IDSP;
    int soluong;

    public Donhang_has_sanpham() {
    }

    public Donhang_has_sanpham(int donhang_IDDH, String IDSP, int soluong) {
        this.donhang_IDDH = donhang_IDDH;
        this.IDSP = IDSP;
        this.soluong = soluong;
    }

    public int getDonhang_IDDH() {
        return donhang_IDDH;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setDonhang_IDDH(int donhang_IDDH) {
        this.donhang_IDDH = donhang_IDDH;
    }

    public String getIDSP() {
        return IDSP;
    }

    public void setIDSP(String IDSP) {
        this.IDSP = IDSP;
    }
}

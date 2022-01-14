package com.example.fashionapp.models;

import java.io.Serializable;

public class GioHang implements Serializable {
    int IDGH;
    int IDKH;
    String IDSP;
    int soluong;
    int tongTien;

    public GioHang() {
    }

    public GioHang(int IDGH, int IDKH, String IDSP, int soluong, int tongTien) {
        this.IDGH = IDGH;
        this.IDKH = IDKH;
        this.IDSP = IDSP;
        this.soluong = soluong;
        this.tongTien = tongTien;
    }

    public int getIDGH() {
        return IDGH;
    }

    public void setIDGH(int IDGH) {
        this.IDGH = IDGH;
    }

    public int getIDKH() {
        return IDKH;
    }

    public void setIDKH(int IDKH) {
        this.IDKH = IDKH;
    }

    public String getIDSP() {
        return IDSP;
    }

    public void setIDSP(String IDSP) {
        this.IDSP = IDSP;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

}

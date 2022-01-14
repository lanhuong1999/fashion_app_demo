package com.example.fashionapp.models;

import java.io.Serializable;
import java.util.Date;

public class DonHang implements Serializable {
    int IDDH;
    int IDKH;
    int tongTien;
    String sđt;
    String date;
    String diachi;
    int soluong;
    String thanhtoan;
    int IDMaKM;

    public DonHang() {

    }

    public DonHang(int IDDH, int IDKH, int tongTien, String sđt, String date, String diachi, int soluong, String thanhtoan, int IDMaKM) {
        this.IDDH = IDDH;
        this.IDKH = IDKH;
        this.tongTien = tongTien;
        this.sđt = sđt;
        this.date = date;
        this.diachi = diachi;
        this.soluong = soluong;
        this.thanhtoan = thanhtoan;
        this.IDMaKM = IDMaKM;
    }

    public int getIDDH() {
        return IDDH;
    }

    public void setIDDH(int IDDH) {
        this.IDDH = IDDH;
    }

    public int getIDKH() {
        return IDKH;
    }

    public void setIDKH(int IDKH) {
        this.IDKH = IDKH;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getSđt() {
        return sđt;
    }

    public void setSđt(String sđt) {
        this.sđt = sđt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getIDMaKM() {
        return IDMaKM;
    }

    public void setIDMaKM(int IDMaKM) {
        this.IDMaKM = IDMaKM;
    }

    public String getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(String thanhtoan) {
        this.thanhtoan = thanhtoan;
    }
}

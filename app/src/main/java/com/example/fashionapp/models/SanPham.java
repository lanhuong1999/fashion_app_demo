package com.example.fashionapp.models;

import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class SanPham implements Serializable {
    String IDSP;
    String ten;
    String img_url;
    int soLuong;
    String moTa;
    String mau;
    int gia;
    String IDNCC;
    String loai;

    public SanPham() {

    }

    public SanPham(String IDSP, String ten, String img_url, int soLuong, String moTa, String mau, int gia, String IDNCC, String loai) {
        this.IDSP = IDSP;
        this.ten = ten;
        this.img_url = img_url;
        this.soLuong = soLuong;
        this.moTa = moTa;
        this.mau = mau;
        this.gia = gia;
        this.IDNCC = IDNCC;
        this.loai = loai;
    }

    public String getIDSP() {
        return IDSP;
    }

    public void setIDSP(String IDSP) {
        this.IDSP = IDSP;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getIDNCC() {
        return IDNCC;
    }

    public void setIDNCC(String IDNCC) {
        this.IDNCC = IDNCC;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
}

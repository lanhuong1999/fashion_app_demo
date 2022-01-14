package com.example.fashionapp.models;

import java.io.Serializable;

public class KhachHang implements Serializable {
    int IDKH;
    String ten;
    String diaChi;
    String email;
    String taikhoan_TenDN;
    String sdt;

    public KhachHang() {
    }

    public KhachHang(int IDKH, String ten, String diaChi, String email, String taikhoan_TenDN, String sdt) {
        this.IDKH = IDKH;
        this.ten = ten;
        this.diaChi = diaChi;
        this.email = email;
        this.taikhoan_TenDN = taikhoan_TenDN;
        this.sdt = sdt;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIDKH() {
        return IDKH;
    }

    public void setIDKH(int IDKH) {
        this.IDKH = IDKH;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTaikhoan_TenDN() {
        return taikhoan_TenDN;
    }

    public void setTaikhoan_TenDN(String taikhoan_TenDN) {
        this.taikhoan_TenDN = taikhoan_TenDN;
    }
}

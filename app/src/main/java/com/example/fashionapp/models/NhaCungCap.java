package com.example.fashionapp.models;

import java.io.Serializable;

public class NhaCungCap implements Serializable {
    String IDNCC;
    String tenNCC;
    String diaChi;
    String dienThoai;
    String anh_url;

    public NhaCungCap() {
    }

    public NhaCungCap(String IDNCC, String tenNCC, String diaChi, String dienThoai, String anh_url) {
        this.IDNCC = IDNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.anh_url = anh_url;
    }

    public String getIDNCC() {
        return IDNCC;
    }

    public void setIDNCC(String IDNCC) {
        this.IDNCC = IDNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getAnh_url() {
        return anh_url;
    }

    public void setAnh_url(String anh_url) {
        this.anh_url = anh_url;
    }
}

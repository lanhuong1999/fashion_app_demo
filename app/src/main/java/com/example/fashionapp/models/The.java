package com.example.fashionapp.models;

import java.io.Serializable;

public class The implements Serializable {
    int IDKH;
    String soTK;
    String tenNH;

    public The() {
    }

    public The(int IDKH, String soTK, String tenNH) {
        this.IDKH = IDKH;
        this.soTK = soTK;
        this.tenNH = tenNH;
    }

    public int getIDKH() {
        return IDKH;
    }

    public void setIDKH(int IDKH) {
        this.IDKH = IDKH;
    }

    public String getSoTK() {
        return soTK;
    }

    public void setSoTK(String soTK) {
        this.soTK = soTK;
    }

    public String getTenNH() {
        return tenNH;
    }

    public void setTenNH(String tenNH) {
        this.tenNH = tenNH;
    }
}

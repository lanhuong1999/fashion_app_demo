package com.example.fashionapp.models;

import java.io.Serializable;

public class YeuThich implements Serializable {
    int IDKH;
    String IDSP;

    public YeuThich() {
    }

    public YeuThich(int IDKH, String IDSP) {
        this.IDKH = IDKH;
        this.IDSP = IDSP;
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
}

package com.example.fashionapp.models;

import java.io.Serializable;

public class MaKM implements Serializable {
    int id;
    int giaKM;
    int soLuong;
    public MaKM() {
    }

    public MaKM(int id, int giaKM, int soLuong) {
        this.id = id;
        this.giaKM = giaKM;
        this.soLuong = soLuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGiaKM() {
        return giaKM;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGiaKM(int giaKM) {
        this.giaKM = giaKM;
    }
}

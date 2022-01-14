package com.example.fashionapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Dep")
public class Dep implements Serializable {
    @PrimaryKey()
    @ColumnInfo(name = "ID")
    String ID;
    @ColumnInfo(name = "Ten")
    String ten;
    @ColumnInfo(name = "Image_url")
    String img_url;
    @ColumnInfo(name = "SoLuong")
    int soluong;
    @ColumnInfo(name = "MoTa")
    String mota;
    @ColumnInfo(name = "Mau")
    String mau;
    @ColumnInfo(name = "Gia")
    int gia;
    @ColumnInfo(name = "Sale")
    int sale;
    @ColumnInfo(name = "ChatLieu")
    String chatLieu;
    @ColumnInfo(name = "KichThuoc")
    String kichThuoc;

    public Dep() {
    }

    public Dep(String ID, String ten, String img_url, int soluong, String mota, String mau, int gia, int sale, String chatLieu, String kichThuoc) {
        this.ID = ID;
        this.ten = ten;
        this.img_url = img_url;
        this.soluong = soluong;
        this.mota = mota;
        this.mau = mau;
        this.gia = gia;
        this.sale = sale;
        this.chatLieu = chatLieu;
        this.kichThuoc = kichThuoc;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
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

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }
}

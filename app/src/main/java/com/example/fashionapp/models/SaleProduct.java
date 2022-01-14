package com.example.fashionapp.models;

public class SaleProduct{
    String name;
    String description;
    String type;
    String img_url;
    String sale;

    public SaleProduct() {

    }

    public SaleProduct(String name, String description, String type, String img_url, String sale) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.img_url = img_url;
        this.sale = sale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}

package com.example.fashionapp;

public class Common {
    public static String url_ao_category="http://assets.myntassets.com/v1/images/style/properties/1f978791c85838728fdd3140592da89a_images.jpg";
    public static String url_quan_category="http://assets.myntassets.com/v1/images/style/properties/60311352abfe03e7e3a351a9a9c1b7c6_images.jpg";
    public static String url_balo_category="http://assets.myntassets.com/v1/images/style/properties/1e91b2c8dbfbe44174871ec3cf1cf314_images.jpg";
    public static String url_giay_category="http://assets.myntassets.com/v1/images/style/properties/d276dddde9e742e45bcb91f88372eb21_images.jpg";
    public static String url_phukien_category="http://assets.myntassets.com/v1/images/style/properties/Skagen-Men-Black-Watch_4982b2b1a76a85a85c9adc8b4b2d523a_images.jpg";

    public static String formatGia(int gia){
        String gia_str=String.valueOf(gia);
        String gia_format="";
        int cnt=0;
        for(int i=gia_str.length()-1;i>=0;i--){

            if(cnt%3==0 && cnt!=0){
                gia_format="."+gia_format;
            }
            gia_format=gia_str.charAt(i)+gia_format;
            ++cnt;
        }
        return gia_format;
    }
}

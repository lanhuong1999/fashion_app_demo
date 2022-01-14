package com.example.fashionapp.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fashionapp.R;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.KhachHangDAO;
import com.example.fashionapp.database.repository.KhachHangImp;
import com.example.fashionapp.models.KhachHang;
import com.google.gson.Gson;

public class ProfileFragment extends AppCompatActivity {

    EditText hoten, sdt, email, diachi;
    Toolbar mToolbar;
    String hoten_str, email_str, diachi_str, sdt_str;
    Button btn_save;
    KhachHang khachHang;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_profile);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        database=new Database(this);
        hoten = findViewById(R.id.profile_name);
        sdt = findViewById(R.id.profile_phone);
        email = findViewById(R.id.profile_email);
        diachi = findViewById(R.id.profile_add);
        btn_save=findViewById(R.id.profile_btn);
        btn_save.setClickable(false);
        khachHang=getKH();
        if(khachHang!=null){
            hoten.setText(khachHang.getTen());
            email.setText(khachHang.getEmail());
            diachi.setText(khachHang.getDiaChi());
            sdt.setText(khachHang.getSdt());

            hoten_str=khachHang.getTen();
            email_str=khachHang.getEmail();
            diachi_str=khachHang.getDiaChi();
            sdt_str=khachHang.getSdt();
        }
        check_edt();
        
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName=hoten.getText().toString();
                String newDiaChi=diachi.getText().toString();
                String newEmail=email.getText().toString();
                String newSdt=sdt.getText().toString();
                KhachHang kh=new KhachHang(khachHang.getIDKH(),newName, newDiaChi, newEmail, khachHang.getTaikhoan_TenDN(), newSdt);

                capNhatThongTin(kh);
                saveCurrentKH(kh);
            }
        });


    }

    private void capNhatThongTin(KhachHang kh) {
       KhachHangDAO khachHangDAO=new KhachHangImp();
        khachHangDAO.updateKH(database,kh);
        Toast.makeText(this,"Cập nhật thông tin thành công",Toast.LENGTH_SHORT).show();

    }

    private void check_edt(){
        hoten.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(hoten_str==null||!hoten_str.equals(String.valueOf(charSequence))){
                    btn_save.setClickable(true);
                }
                else{
                    btn_save.setClickable(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(email_str==null||!email_str.equals(String.valueOf(charSequence))){
                    btn_save.setClickable(true);
                }
                else{
                    btn_save.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        diachi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(diachi_str==null||!diachi_str.equals(String.valueOf(charSequence))){
                    btn_save.setClickable(true);
                }
                else{
                    btn_save.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        sdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(sdt_str==null || !sdt_str.equals(String.valueOf(charSequence))){
                    btn_save.setClickable(true);
                }
                else{
                    btn_save.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private KhachHang getKH(){
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("current_kh", "");
        if(json!="") {
            KhachHang khachHang = gson.fromJson(json, KhachHang.class);
            return khachHang;
        }
        return null;
    }

    private void saveCurrentKH(KhachHang khachHang){
        SharedPreferences preferences = getSharedPreferences("MyPreferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(khachHang);
        editor.putString("current_kh",json);
        editor.commit();
    }


}
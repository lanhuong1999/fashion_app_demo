package com.example.fashionapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashionapp.R;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.KhachHangDAO;
import com.example.fashionapp.database.dao.TaiKhoanDAO;
import com.example.fashionapp.database.repository.KhachHangImp;
import com.example.fashionapp.database.repository.TaiKhoanImp;
import com.example.fashionapp.models.KhachHang;
import com.example.fashionapp.models.TaiKhoan;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    TextView txtReg;
   // FirebaseAuth firebaseAuth;
    EditText email, password;
    Button sigin_btn;
    ProgressBar progressBar;
    Database database;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //firebaseAuth = FirebaseAuth.getInstance();
        txtReg=findViewById(R.id.txt_sign_up);
        email=findViewById(R.id.email_edt);
        password =findViewById(R.id.password_edt);
        sigin_btn=findViewById(R.id.signin_btn);
        progressBar=findViewById(R.id.progress_bar);
        checkBox=findViewById(R.id.login_remember);
        database=new Database(this);

        TaiKhoan t=getRemember();
        if(t!=null){
            startActivity(new Intent(this,MainActivity.class));
        }
        addEvents();

    }

    private void addEvents() {
        txtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

        sigin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
            }
        });

    }
    private void ghiNho(TaiKhoan taiKhoan){
        SharedPreferences preferences = getSharedPreferences("MyPreferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(taiKhoan);
        editor.putString("remember_acc",json);
        editor.commit();

    }
    private void saveCurrentAcc(TaiKhoan taiKhoan){
        SharedPreferences preferences = getSharedPreferences("MyPreferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(taiKhoan);
        editor.putString("current_acc",json);
        editor.commit();

    }
    private void saveCurrentKH(KhachHang khachHang){
        SharedPreferences preferences = getSharedPreferences("MyPreferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(khachHang);
        editor.putString("current_kh",json);
        editor.commit();
    }
    private TaiKhoan getRemember(){
        SharedPreferences preferences = getSharedPreferences("MyPreferences",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("remember_acc", "");
        if(json!="") {
            TaiKhoan tk = gson.fromJson(json, TaiKhoan.class);
            return tk;
        }
        return null;
    }
    private void removeRemember(){
        SharedPreferences preferences = getSharedPreferences("MyPreferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.remove("remember_acc").commit();

    }

    private void login() {
        TaiKhoan t = new TaiKhoan();

        String email_str=email.getText().toString();
        String pass_str=password.getText().toString();

        if(TextUtils.isEmpty(email_str)){
            Toast.makeText(LoginActivity.this, "Email is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass_str)){
            Toast.makeText(LoginActivity.this, "Password is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        t = new TaiKhoan(email_str,pass_str);
        TaiKhoanDAO taiKhoanDAO=new TaiKhoanImp();
        if(taiKhoanDAO.checkLogin(database,t)){
            if(checkBox.isChecked()) {
                ghiNho(t);
            }
            else{
                removeRemember();
            }
            saveCurrentAcc(t);
            KhachHangDAO khachHangDAO=new KhachHangImp();
            KhachHang khachHang=khachHangDAO.getKHbyTenDN(database,email_str);
            saveCurrentKH(khachHang);
            startActivity(new Intent(this,MainActivity.class));
        }
        else{
            Toast.makeText(this,"Tài khoản không chính xác",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                }
            }, 2000);

        }


    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}
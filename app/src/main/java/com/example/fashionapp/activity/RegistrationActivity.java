package com.example.fashionapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegistrationActivity extends AppCompatActivity {

    TextView txtSignin;
    EditText username, password, password_repeat, name, address,email;
    Button signup;
//    FirebaseAuth firebaseAuth;
//    FirebaseDatabase database;

    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

//        firebaseAuth = FirebaseAuth.getInstance();
//        database =FirebaseDatabase.getInstance();
        txtSignin=findViewById(R.id.txt_sign_in);
        username = findViewById(R.id.uesrname_edt);
        password=findViewById(R.id.password_edt);
        password_repeat=findViewById(R.id.password_repeat_edt);
        name=findViewById(R.id.name_edt);
        address=findViewById(R.id.add_edt);
        email=findViewById(R.id.email_edt);
        signup=findViewById(R.id.signup_btn);
        database=new Database(this);


        addEvents();


    }

    private void addEvents() {
        txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();

            }
        });
    }

    private void createUser() {
        String username_str=username.getText().toString();
        String name_str=name.getText().toString();
        String add_str=address.getText().toString();
        String pass_str=password.getText().toString();
        String pass_repeat=password_repeat.getText().toString();
        String email_str=email.getText().toString();

        if(!pass_str.equals(pass_repeat)){
            Toast.makeText(RegistrationActivity.this, "Two password aren't equal", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(username_str)){
            Toast.makeText(RegistrationActivity.this, "Username is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass_str)){
            Toast.makeText(RegistrationActivity.this, "Password isempty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass_repeat)){
            Toast.makeText(RegistrationActivity.this, "Repeat password is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(name_str)){
            Toast.makeText(RegistrationActivity.this, "Name is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(add_str)){
            Toast.makeText(RegistrationActivity.this, "Address is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email_str)){
            Toast.makeText(RegistrationActivity.this, "Email is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        TaiKhoan t=new TaiKhoan(username_str,pass_str);
        TaiKhoanDAO taiKhoanDAO=new TaiKhoanImp();
        if(taiKhoanDAO.checkTaiKhoan(database,username_str)){
            Toast.makeText(this,"Tên đăng nhập đã tồn tại",Toast.LENGTH_SHORT).show();
        }
        else {
            taiKhoanDAO.themeTaiKhoan(database,t);
            KhachHang k=new KhachHang(0,name_str,add_str,email_str,username_str,"");
            KhachHangDAO khachHangDAO=new KhachHangImp();
            khachHangDAO.themKhachHang(database,k);
            Toast.makeText(this,"Đăng ký tài khoản thành công",Toast.LENGTH_SHORT).show();
        }

//        firebaseAuth.createUserWithEmailAndPassword(username_str, pass_str).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(RegistrationActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(RegistrationActivity.this,"Lỗi: "+task.getException(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }

}
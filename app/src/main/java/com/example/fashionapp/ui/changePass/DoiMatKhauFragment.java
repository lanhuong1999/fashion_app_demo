package com.example.fashionapp.ui.changePass;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.fashionapp.R;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.TaiKhoanDAO;
import com.example.fashionapp.database.repository.TaiKhoanImp;
import com.example.fashionapp.models.TaiKhoan;
import com.google.gson.Gson;

public class DoiMatKhauFragment extends Fragment {

    EditText pass_curr, pass_new, pass_new_repeat;
    Button btn_capnhat, btn_huy;
    TaiKhoan taiKhoan;
    NavController controller;
    Database database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        pass_curr=view.findViewById(R.id.change_pass_curr);
        pass_new=view.findViewById(R.id.change_pass_new);
        pass_new_repeat=view.findViewById(R.id.change_pass_new_repeat);
        btn_capnhat=view.findViewById(R.id.change_pass_btn_capnhat);
        btn_huy=view.findViewById(R.id.change_pass_btn_huy);
        controller= Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        database=new Database(getActivity());

        taiKhoan=getCurrAcc();
    
        addEvents();
        
        return view;
    }

    private void addEvents() {
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){
                    TaiKhoanDAO taiKhoanDAO=new TaiKhoanImp();
                    taiKhoan.setMatKhau(pass_new.getText().toString());
                    saveCurrentAcc(taiKhoan);
                    taiKhoanDAO.capNhatMatKhau(database,taiKhoan);
                    Toast.makeText(getActivity(),"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                    controller.popBackStack();
                }
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.popBackStack();
            }
        });
    }

    private boolean check() {
        if(TextUtils.isEmpty(pass_curr.getText().toString())){
            Toast.makeText(getActivity(),"Vui lòng nhập mật khẩu hiện tại của bạn",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!pass_curr.getText().toString().equals(taiKhoan.getMatKhau())){
            Toast.makeText(getActivity(),"Mật khẩu hiện tại không đúng, vui lòng nhập lại",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(pass_new.getText().toString())){
            Toast.makeText(getActivity(),"Vui lòng nhập mật khẩu mới của bạn",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(pass_new_repeat.getText().toString())){
            Toast.makeText(getActivity(),"Vui lòng nhập lại mật khẩu mới của bạn",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!pass_new.getText().toString().equals(pass_new_repeat.getText().toString())){
            Toast.makeText(getActivity(),"Mật khẩu mới và nhập lại mật khẩu mới không trùng nhau",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private TaiKhoan getCurrAcc(){
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("current_acc", "");
        if(json!="") {
            TaiKhoan tk = gson.fromJson(json, TaiKhoan.class);
            return tk;
        }
        return null;
    }

    private void saveCurrentAcc(TaiKhoan taiKhoan){
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPreferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(taiKhoan);
        editor.putString("current_acc",json);
        editor.commit();

    }
}
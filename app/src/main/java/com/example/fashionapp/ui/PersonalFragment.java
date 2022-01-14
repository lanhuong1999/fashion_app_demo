package com.example.fashionapp.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fashionapp.R;
import com.example.fashionapp.activity.LoginActivity;
import com.example.fashionapp.models.KhachHang;
import com.example.fashionapp.ui.donhang.DonHangActivity;
import com.example.fashionapp.ui.profile.ProfileFragment;
import com.example.fashionapp.ui.the.TheActivity;
import com.example.fashionapp.ui.yeuthich.YeuThichActivity;
import com.google.gson.Gson;

public class PersonalFragment extends Fragment {

    TextView name;
    CardView viewProfile, viewFavourite, viewCoupon, viewOrder, viewCard, viewPass, logout;
    NavController controller;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        name=view.findViewById(R.id.personal_name);
        viewProfile=view.findViewById(R.id.personal_profile);
        viewFavourite =view.findViewById(R.id.personal_prod);
        viewCoupon=view.findViewById(R.id.personal_coupon);
        viewOrder=view.findViewById(R.id.personal_order);
        viewCard=view.findViewById(R.id.personal_card);
        viewPass=view.findViewById(R.id.personal_change_pass);
        logout=view.findViewById(R.id.personal_logout);

        controller= Navigation.findNavController(getActivity(),R.id.nav_host_fragment);

        setName();

        addEvents();

        return view;
    }

    private void setName(){
        KhachHang khachHang=getKH();
        name.setText(khachHang.getTen());
    }

    private void addEvents() {
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProfileFragment.class));
            }
        });

        viewCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.offersFragment);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        viewFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), YeuThichActivity.class));
            }
        });

        viewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DonHangActivity.class));
            }
        });

        viewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TheActivity.class));
            }
        });

        viewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.doiMatKhauFragment);
            }
        });
    }
    private void logout(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());

        builder.setMessage("Bạn có chắc chắn muốn đăng xuất không?");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences preferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.remove("remember_acc").commit();

                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    private KhachHang getKH(){
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("current_kh", "");
        if(json!="") {
            KhachHang khachHang = gson.fromJson(json, KhachHang.class);
            return khachHang;
        }
        return null;
    }
    @Override
    public void onResume() {
        super.onResume();
        setName();
    }
}
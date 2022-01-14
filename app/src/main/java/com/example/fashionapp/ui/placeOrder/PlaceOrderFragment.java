package com.example.fashionapp.ui.placeOrder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.fashionapp.Common;
import com.example.fashionapp.R;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.DonHangDAO;
import com.example.fashionapp.database.dao.DonHangHasSPDAO;
import com.example.fashionapp.database.dao.GioHangDAO;
import com.example.fashionapp.database.dao.MakMDAO;
import com.example.fashionapp.database.repository.DonHangHasSPImp;
import com.example.fashionapp.database.repository.DonHangImp;
import com.example.fashionapp.database.repository.GioHangImp;
import com.example.fashionapp.database.repository.MaKMImp;
import com.example.fashionapp.models.DonHang;
import com.example.fashionapp.models.Donhang_has_sanpham;
import com.example.fashionapp.models.GioHang;
import com.example.fashionapp.models.KhachHang;
import com.example.fashionapp.models.MaKM;
import com.example.fashionapp.models.The;
import com.example.fashionapp.ui.the.TheActivity;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PlaceOrderFragment extends Fragment {

    final int REQUEST_CODE = 100001;

    TextView total, date, add_default, txt_infor_the;
    CheckBox checkBox;
    EditText phone, add_new;
    RadioButton rad_cash, rad_card;
    RadioGroup radioGroup;
    Button btn_confirm;
    KhachHang khachHang;
    int tongTien, giamGia;
    ArrayList<GioHang> listGioHang;
    MaKM maKM;
    NavController controller;
    The the_order;

    Database database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_order, container, false);

        total = view.findViewById(R.id.place_order_total);
        txt_infor_the = view.findViewById(R.id.place_order_infor_the);
        date = view.findViewById(R.id.place_order_date);
        add_default = view.findViewById(R.id.place_order_add_default);
        checkBox = view.findViewById(R.id.place_order_check_add);
        phone = view.findViewById(R.id.place_order_phone);
        add_new = view.findViewById(R.id.place_order_add_new);
        rad_cash = view.findViewById(R.id.place_order_cash);
        rad_card = view.findViewById(R.id.place_order_card);
        btn_confirm = view.findViewById(R.id.place_order_confirm);
        radioGroup = view.findViewById(R.id.place_order_group);
        the_order = new The();
        listGioHang = new ArrayList<>();
        database = new Database(getActivity());
        controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        maKM = null;
        giamGia = 0;

        khachHang = getKH();
        if (khachHang != null) {
            add_default.setText(khachHang.getDiaChi());
            phone.setText(khachHang.getSdt());
        }

        Bundle bundle = getArguments();
        tongTien = bundle.getInt("total_price", 0);
        maKM = (MaKM) bundle.getSerializable("maKM");
        if (maKM != null) {
            if (maKM.getSoLuong() > 0) {
                giamGia = tongTien * maKM.getGiaKM() / 100;
            } else {
                giamGia = 0;
                Toast.makeText(getActivity(), "Số lượng mã khuyến mãi bạn chọn đã hết. Vui lòng chọn mã khác", Toast.LENGTH_SHORT).show();
            }
        } else {
            giamGia = 0;
        }
        total.setText(Common.formatGia(tongTien - giamGia) + " đ");

        Log.d("TLH", "onCreateView: " + tongTien);
        listGioHang = (ArrayList<GioHang>) getGioHangList(khachHang.getIDKH());
        Log.d("TLH", "onCreateView: " + listGioHang.size());

        Calendar currDate = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        String date_str = format.format(currDate.getTime());
        String time_str = format1.format(currDate.getTime());
        date.setText(time_str + " - " + date_str);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    add_new.setEnabled(false);
                } else {
                    add_new.setEnabled(true);
                }
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkOrder())
                    confirmOrder();
                else {
                    Toast.makeText(getActivity(), "Vui lòng nhập địa chỉ giao hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.place_order_card) {
                    Intent intent = new Intent(getActivity(), TheActivity.class);
                    intent.putExtra("select", "order");
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    txt_infor_the.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }

    private boolean checkOrder() {
        if (!checkBox.isChecked() && TextUtils.isEmpty(add_new.getText().toString()))
            return false;
        return true;
    }

    private void confirmOrder() {
        int idkh = khachHang.getIDKH();
        String sdt_str = phone.getText().toString();
        String date_str = date.getText().toString();
        String add = "";
        if (checkBox.isChecked()) add = add_default.getText().toString();
        else add = add_new.getText().toString();
        int soluong = 0;
        for (GioHang gioHang : listGioHang) soluong += gioHang.getSoluong();
        String thanhtoan = "";

        if (rad_cash.isChecked()) thanhtoan = "Tiền mặt";
        if (rad_card.isChecked()) {
            String txt = txt_infor_the.getText().toString();
            thanhtoan = "Thẻ: " + txt;
        }
        DonHang donHang = new DonHang();
        if (maKM != null) {
            donHang = new DonHang(0, idkh, tongTien - giamGia, sdt_str, date_str, add, soluong, thanhtoan, maKM.getId());
        } else {
            donHang = new DonHang(0, idkh, tongTien - giamGia, sdt_str, date_str, add, soluong, thanhtoan, -1);
        }
        themDH(donHang);
        themSP_DH();
        xoaGioHang();
        if(maKM!=null){
            capNhatMaKM();
        }
        Toast.makeText(getActivity(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent("action_cart");
        intent.putExtra("key","buy");
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
        controller.popBackStack(R.id.homeFragment, false);
    }

    private void capNhatMaKM() {
        MakMDAO makMDAO = new MaKMImp();
        makMDAO.capNhatMa(database, maKM);
    }

    private void xoaGioHang() {
        GioHangDAO gioHangDAO = new GioHangImp();
        for (GioHang gioHang : listGioHang) {
            gioHangDAO.xoaGioHang(database, gioHang);
        }
    }

    private void themSP_DH() {
        int idDH = getIDDHCurr();
        DonHangHasSPDAO donHangHasSPDAO = new DonHangHasSPImp();

        for (GioHang gioHang : listGioHang) {
            Donhang_has_sanpham dh_sp = new Donhang_has_sanpham(idDH, gioHang.getIDSP(), gioHang.getSoluong());
            donHangHasSPDAO.themSanPhamDonHang(database, dh_sp);
        }
    }

    private int getIDDHCurr() {
        DonHangDAO donHangDAO = new DonHangImp();
        return donHangDAO.getIDDHCurrent(database, khachHang.getIDKH());
    }

    private void themDH(DonHang donHang) {
        DonHangDAO donHangDAO = new DonHangImp();
        donHangDAO.themDonHang(database, donHang);
    }

    private KhachHang getKH() {
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("current_kh", "");
        if (json != "") {
            KhachHang khachHang = gson.fromJson(json, KhachHang.class);
            return khachHang;
        }
        return null;
    }

    private List<GioHang> getGioHangList(int idkh) {
        GioHangDAO gioHangDAO = new GioHangImp();
        List<GioHang> list = gioHangDAO.getGioHang(database, idkh);
        return list;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == getActivity().RESULT_OK) {
                The the = (The) data.getSerializableExtra("order_the");
                txt_infor_the.setVisibility(View.VISIBLE);
                txt_infor_the.setText(the.getTenNH());
                txt_infor_the.append("\nSố tài khoản: " + the.getSoTK());
            }
        }
    }
}
package com.example.fashionapp.ui.giohang;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fashionapp.Common;
import com.example.fashionapp.R;
import com.example.fashionapp.ui.RecyclerViewClickItem;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.GioHangDAO;
import com.example.fashionapp.database.dao.SanPhamDAO;
import com.example.fashionapp.database.repository.GioHangImp;
import com.example.fashionapp.database.repository.SanPhamImp;
import com.example.fashionapp.models.GioHang;
import com.example.fashionapp.models.KhachHang;
import com.example.fashionapp.models.MaKM;
import com.example.fashionapp.models.SanPham;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class MyCartFragment extends Fragment implements RecyclerViewClickItem {

    RecyclerView rec_cart;
    CartAdapter cartAdapter;
    List<GioHang> gioHangList;
    List<SanPham> sanPhamList;
//    List<String> listIDSP;
//    List<Integer> listSoLuong;
    TextView txt_total;
    Database database;
    NavController controller;
    Button btnBuy;
    int IDKH;
    int total;
    int discount=0;
    ConstraintLayout cart_voucher;
    MaKM maKM=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_cart, container, false);

        cart_voucher=root.findViewById(R.id.cart_voucher);
        controller= Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        btnBuy=root.findViewById(R.id.cart_btn_buy);
        database=new Database(getActivity());
        gioHangList=new ArrayList<>();
        sanPhamList=new ArrayList<>();
//        listIDSP=new ArrayList<>();
//        listSoLuong=new ArrayList<>();
        total=0;
        IDKH=getKH().getIDKH();
        gioHangList=getGioHang();
        sanPhamList=getSP();

        rec_cart=root.findViewById(R.id.rec_cart);
        txt_total=root.findViewById(R.id.cart_total);

        //LocalBroadcastManager.getInstance(getActivity()).registerReceiver(receiver, new IntentFilter("total_price"));

        rec_cart.setLayoutManager(new LinearLayoutManager(getActivity()));

        cartAdapter=new CartAdapter(getActivity(),gioHangList,sanPhamList,this);
        rec_cart.setAdapter(cartAdapter);
        txt_total.setText(Common.formatGia(total-discount)+" đ");
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyProduct();
            }
        });
        cart_voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putBoolean("select_coupon",true);

                controller.navigate(R.id.offersFragment,bundle);

                LocalBroadcastManager.getInstance(getActivity()).registerReceiver(receiverCoupon, new IntentFilter("get_coupon"));

            }
        });


        return root;
    }
    public BroadcastReceiver receiverCoupon = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            maKM = (MaKM) intent.getSerializableExtra("coupon");
            Log.d("TLH", "onReceive: "+maKM.getId());
            discount=total*maKM.getGiaKM()/100;

        }
    };

    private void buyProduct() {
        if(sanPhamList.size()>0) {
            Bundle bundle = new Bundle();
            //bundle.putStringArrayList("list_id", (ArrayList<String>) listIDSP);
            bundle.putInt("total_price",total);
            //bundle.putIntegerArrayList("list_quantity", (ArrayList<Integer>) listSoLuong);
            if(maKM!=null) bundle.putSerializable("maKM", maKM);
            controller.navigate(R.id.placeOrderFragment,bundle);
        }
    }

//    public BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//        int price = intent.getIntExtra("price",0);
//        txt_total.setText(price+"đ");
//        }
//    };

    private List<GioHang> getGioHang(){
        List<GioHang> gioHangList=new ArrayList<>();
        GioHangDAO gioHangDAO=new GioHangImp();
        gioHangList=gioHangDAO.getGioHang(database,IDKH);
        return gioHangList;
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
    private List<SanPham>getSP(){
        List<SanPham> sanPhamList=new ArrayList<>();
        SanPhamDAO sanPhamDAO=new SanPhamImp();
        for( GioHang gioHang : gioHangList){
//            listIDSP.add(gioHang.getIDSP());
//            listSoLuong.add(gioHang.getSoluong());
            SanPham sanPham= sanPhamDAO.getSPbyID(database,gioHang.getIDSP());
            total+=gioHang.getTongTien();
            sanPhamList.add(sanPham);
        }
        return sanPhamList;
    }

    @Override
    public void onItemClick(int position) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        builder.setMessage("Bạn có muốn xóa sản phẩm không?");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GioHang gioHang=gioHangList.get(position);
                GioHangDAO gioHangDAO=new GioHangImp();
                gioHangDAO.xoaGioHang(database,gioHang);
                gioHangList.remove(position);
                sanPhamList.remove(position);
//                listIDSP.remove(position);
//                listSoLuong.remove(position);
                total-=gioHang.getTongTien();
                if(maKM!=null) discount=total*maKM.getGiaKM()/100;
                txt_total.setText(total-discount+" đ");
                cartAdapter.notifyDataSetChanged();

                Intent intent=new Intent("action_cart");
                intent.putExtra("key","del");
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);

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
}
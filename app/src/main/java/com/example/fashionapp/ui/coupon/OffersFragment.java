package com.example.fashionapp.ui.coupon;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fashionapp.R;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.MakMDAO;
import com.example.fashionapp.database.repository.MaKMImp;
import com.example.fashionapp.models.MaKM;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.ArrayList;
import java.util.List;

public class OffersFragment extends Fragment implements RecyclerViewClickItem {

    RecyclerView rec_coupon;
    CouponAdapter couponAdapter;
    List<MaKM> maKMList;
    Database database;
    MaKM maKM;
    Boolean check;
    NavController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_offers, container, false);

        controller=Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        database=new Database(getActivity());
        rec_coupon=view.findViewById(R.id.rec_coupon);
        maKMList=new ArrayList<>();
        rec_coupon.setLayoutManager(new LinearLayoutManager(getActivity()));
        MakMDAO makMDAO=new MaKMImp();
        maKMList=makMDAO.getMaKM(database);
        couponAdapter=new CouponAdapter(getActivity(),maKMList,this);
        rec_coupon.setAdapter(couponAdapter);

        maKM=new MaKM();
        check=false;
        Bundle bundle=getArguments();
        if(bundle!=null) {
            check = bundle.getBoolean("select_coupon", false);

        }


        return view;
    }

    @Override
    public void onItemClick(int position) {
        maKM=maKMList.get(position);
        if(check) {

            Intent intent = new Intent("get_coupon");
            intent.putExtra("coupon", maKM);
            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
            controller.popBackStack();
        }
    }
}
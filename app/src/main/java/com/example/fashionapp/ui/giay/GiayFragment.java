package com.example.fashionapp.ui.giay;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fashionapp.R;
import com.example.fashionapp.activity.DetailActivity;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.SanPhamDAO;
import com.example.fashionapp.database.repository.SanPhamImp;
import com.example.fashionapp.models.Giay;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.ArrayList;
import java.util.List;

public class GiayFragment extends Fragment implements RecyclerViewClickItem {

    RecyclerView rec_giay;
    List<Giay> giayList;
    GiayAdapter adapter;
    Database database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_giay, container, false);
        database=new Database(getActivity());
        rec_giay=view.findViewById(R.id.rec_giay);
        rec_giay.setLayoutManager(new LinearLayoutManager(getActivity()));
        giayList=new ArrayList<>();
        SanPhamDAO dao=new SanPhamImp();
        giayList=dao.getSanPhamGiay(database);
        adapter=new GiayAdapter(getActivity(),giayList,this);
        rec_giay.setAdapter(adapter);
        return view;
    }

    @Override
    public void onItemClick(int position) {
        Giay giay=giayList.get(position);
        Intent intent=new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("giay_detail",giay);
        startActivity(intent);
    }
}
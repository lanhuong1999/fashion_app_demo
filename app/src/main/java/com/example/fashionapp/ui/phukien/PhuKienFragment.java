package com.example.fashionapp.ui.phukien;

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
import com.example.fashionapp.models.PhuKien;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.ArrayList;
import java.util.List;

public class PhuKienFragment extends Fragment implements RecyclerViewClickItem {

    RecyclerView rec_phukien;
    List<PhuKien> phuKienList;
    PhuKienAdapter adapter;
    Database database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_phu_kien, container, false);
        database=new Database(getActivity());

        rec_phukien=view.findViewById(R.id.rec_phukien);
        rec_phukien.setLayoutManager(new LinearLayoutManager(getActivity()));
        phuKienList=new ArrayList<>();

        SanPhamDAO dao=new SanPhamImp();
        phuKienList=dao.getSanPhamPhuKien(database);

        adapter=new PhuKienAdapter(getActivity(),phuKienList,this);
        rec_phukien.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(int position) {
        PhuKien phuKien=phuKienList.get(position);
        Intent intent=new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("phukien_detail",phuKien);
        startActivity(intent);
    }
}
package com.example.fashionapp.ui.quan;

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
import com.example.fashionapp.models.Quan;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.ArrayList;
import java.util.List;

public class QuanFragment extends Fragment implements RecyclerViewClickItem {

    RecyclerView recQuan;
    List<Quan> quanList;
    QuanAdapter adapter;
    Database database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan, container, false);
        database=new Database(getActivity());

        recQuan=view.findViewById(R.id.rec_quan);
        recQuan.setLayoutManager(new LinearLayoutManager(getActivity()));
        quanList=new ArrayList<>();

        SanPhamDAO dao=new SanPhamImp();
        quanList=dao.getSanPhamQuan(database);

        adapter=new QuanAdapter(getActivity(),quanList,this);
        recQuan.setAdapter(adapter);
        return view;
    }

    @Override
    public void onItemClick(int position) {
        Quan quan = quanList.get(position);
        Intent intent=new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("quan_detail",quan);
        startActivity(intent);
    }
}
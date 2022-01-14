package com.example.fashionapp.ui.ao;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashionapp.R;
import com.example.fashionapp.activity.DetailActivity;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.SanPhamDAO;
import com.example.fashionapp.database.repository.SanPhamImp;
import com.example.fashionapp.models.Ao;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.ArrayList;
import java.util.List;

public class AoFragment extends Fragment implements RecyclerViewClickItem {

    RecyclerView recAo;
    List<Ao> aoList;
    AoAdapter adapter;
    Database database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ao, container, false);
        database= new Database(getActivity());

        recAo=view.findViewById(R.id.rec_ao);
        recAo.setLayoutManager(new LinearLayoutManager(getActivity()));
        aoList=new ArrayList<>();

        SanPhamDAO dao = new SanPhamImp();
        aoList=dao.getSanPhamAo(database);

        adapter=new AoAdapter(getActivity(),aoList,this);
        recAo.setAdapter(adapter);


        return view;
    }

    @Override
    public void onItemClick(int position) {
        Ao ao=aoList.get(position);
        Intent intent= new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("ao_detail",ao);
        startActivity(intent);
    }
}
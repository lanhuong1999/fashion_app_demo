package com.example.fashionapp.ui.balo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fashionapp.R;
import com.example.fashionapp.activity.DetailActivity;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.SanPhamDAO;
import com.example.fashionapp.database.repository.SanPhamImp;
import com.example.fashionapp.models.Balo;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.ArrayList;
import java.util.List;


public class BaloFragment extends Fragment implements RecyclerViewClickItem {

    RecyclerView rec_balo;
    List<Balo> balos;
    BaloAdapter adapter;
    Database database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_balo, container, false);
        database=new Database(getActivity());
        rec_balo=view.findViewById(R.id.rec_balo);
        rec_balo.setLayoutManager(new LinearLayoutManager(getActivity()));
        balos=new ArrayList<>();

        SanPhamDAO dao=new SanPhamImp();
        balos=dao.getSanPhamBalo(database);

        adapter=new BaloAdapter(getActivity(),balos,this);
        rec_balo.setAdapter(adapter);
        if(balos.isEmpty()){
            Toast.makeText(getActivity(), "Danh mục sản phẩm hiện đã hết", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Balo balo=balos.get(position);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("balo_detail",balo);
        startActivity(intent);
    }
}
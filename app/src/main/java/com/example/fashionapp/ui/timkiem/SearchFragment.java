package com.example.fashionapp.ui.timkiem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fashionapp.R;
import com.example.fashionapp.activity.DetailActivity;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.SanPhamDAO;
import com.example.fashionapp.database.repository.SanPhamImp;
import com.example.fashionapp.models.SanPham;
import com.example.fashionapp.ui.RecyclerViewClickItem;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements RecyclerViewClickItem {

    private RecyclerView rec_search;
    private SearchAdapter adapter;
    private List<SanPham> sanPhamList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        rec_search=view.findViewById(R.id.rec_search);
        rec_search.setLayoutManager(new LinearLayoutManager(getActivity()));
        sanPhamList=getAllSP();
        adapter=new SearchAdapter(getActivity(),sanPhamList,this);
        rec_search.setAdapter(adapter);

        Bundle bundle=getArguments();
        String s=bundle.getString("key");


        adapter.getFilter().filter(s);

        Log.d("TLH", "onCreateView: "+sanPhamList.size());


        return view;
    }

    private List<SanPham> getAllSP(){
        Database database=new Database(getActivity());
        SanPhamDAO sanPhamDAO=new SanPhamImp();
        List<SanPham> list=sanPhamDAO.getSanPham(database);
        return list;
    }

    @Override
    public void onItemClick(int position) {
        List<SanPham> list=new ArrayList<>();
        for (int i=0;i<adapter.getItemCount();i++){
            list.add(adapter.getItem(i));
        }
        SanPham sanPham=list.get(position);
        Intent intent=new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("detail",sanPham);
        startActivity(intent);
    }
}
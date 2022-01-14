package com.example.fashionapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashionapp.R;
import com.example.fashionapp.activity.DetailActivity;
import com.example.fashionapp.ui.RecyclerViewClickItem;
import com.example.fashionapp.ui.home.adapter.NhaCungCapAdapter;
import com.example.fashionapp.ui.home.adapter.AllProductAdapter;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.NhaCungCapDAO;
import com.example.fashionapp.database.dao.SanPhamDAO;
import com.example.fashionapp.database.repository.NhaCungCapImp;
import com.example.fashionapp.database.repository.SanPhamImp;
import com.example.fashionapp.models.NhaCungCap;
import com.example.fashionapp.models.SanPham;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RecyclerViewClickItem {

    ScrollView scrollView;
    ProgressBar progressBar;

    RecyclerView recNCC, recProductlist;
    FirebaseFirestore db;

    List<NhaCungCap> nhaCungCapList;
    NhaCungCapAdapter nhaCungCapAdapter;

    List<SanPham> sanPhamList;
    AllProductAdapter allProductAdapter;
    Database database;

    NavController controller;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        NavHostFragment navHostFragment=
                (NavHostFragment) getActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);

        controller=navHostFragment.getNavController();
        database=new Database(getContext());
        scrollView=root.findViewById(R.id.scroll_view);
        progressBar=root.findViewById(R.id.home_progress_bar);

        recNCC =root.findViewById(R.id.rec_popular);
        recProductlist =root.findViewById(R.id.rec_wishlist);
        db=FirebaseFirestore.getInstance();

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        loadNCC();
        loadProduct();

        return root;
    }

    void loadNCC(){
        nhaCungCapList=new ArrayList<>();
        recNCC.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        NhaCungCapDAO nhaCungCapDAO=new NhaCungCapImp();
        nhaCungCapList=nhaCungCapDAO.getNhaCungCap(database);
        nhaCungCapAdapter=new NhaCungCapAdapter(getActivity(),nhaCungCapList,clickNhaCC);
        recNCC.setAdapter(nhaCungCapAdapter);
    }

    void loadProduct(){
        sanPhamList=new ArrayList<>();
        recProductlist.setLayoutManager(new GridLayoutManager(getActivity(),2));
        SanPhamDAO sanPhamDAO= new SanPhamImp();
        sanPhamList=sanPhamDAO.getSanPham(database);
        allProductAdapter=new AllProductAdapter(getActivity(),sanPhamList,this);
        recProductlist.setAdapter(allProductAdapter);
        progressBar.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
    }

    RecyclerViewClickItem clickNhaCC= new RecyclerViewClickItem() {
        @Override
        public void onItemClick(int position) {
            Bundle bundle=new Bundle();
            bundle.putString("id_ncc",nhaCungCapList.get(position).getIDNCC());
            controller.navigate(R.id.nhaCungCapFragment,bundle);
        }
    };


    @Override
    public void onItemClick(int position) {
        SanPham sanPham=sanPhamList.get(position);
        Intent intent= new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("detail",sanPham);
        startActivity(intent);

    }
}
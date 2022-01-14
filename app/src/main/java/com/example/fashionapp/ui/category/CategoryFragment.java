package com.example.fashionapp.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashionapp.Common;
import com.example.fashionapp.R;
import com.example.fashionapp.models.CategoryHome;
import com.example.fashionapp.ui.RecyclerViewClickItem;
import com.example.fashionapp.ui.category.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements RecyclerViewClickItem {

    List<CategoryHome> list;
    CategoryAdapter categoryAdapter;
    RecyclerView recCategory;
    NavController controller;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category, container, false);

        controller= Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        recCategory=root.findViewById(R.id.rec_category_product);
        list=new ArrayList<>();
        recCategory.setLayoutManager(new GridLayoutManager(getActivity(),2));
        categoryAdapter=new CategoryAdapter(getActivity(),list,this);
        recCategory.setAdapter(categoryAdapter);

        list.add(new CategoryHome("Áo", Common.url_ao_category));
        list.add(new CategoryHome("Quần", Common.url_quan_category));
        list.add(new CategoryHome("Ba lô", Common.url_balo_category));
        list.add(new CategoryHome("Giày", Common.url_giay_category));
        list.add(new CategoryHome("Phụ kiện", Common.url_phukien_category));

        categoryAdapter.notifyDataSetChanged();


        return root;
    }


    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                controller.navigate(R.id.aoFragment);
                break;
            case 1:
                controller.navigate(R.id.quanFragment);
                break;
            case 2:
                controller.navigate(R.id.baloFragment);
                break;
            case 3:
                controller.navigate(R.id.giayFragment);
                break;
            case 4:
                controller.navigate(R.id.phuKienFragment);
                break;
        }
    }
}
package com.example.fashionapp.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.NhaCungCapDAO;
import com.example.fashionapp.database.repository.NhaCungCapImp;
import com.example.fashionapp.models.NhaCungCap;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<NhaCungCap>> mListNCCLiveData;
    private List<NhaCungCap> mListNCC;

    public HomeViewModel() {
        mListNCCLiveData=new MutableLiveData<>();
    }

    public void getNCC(Database database){
//        Database database=new Database();
        mListNCC=new ArrayList<>();
        NhaCungCapDAO nhaCungCapDAO=new NhaCungCapImp();
        mListNCC=nhaCungCapDAO.getNhaCungCap(database);
        mListNCCLiveData.setValue(mListNCC);

    }

    public MutableLiveData<List<NhaCungCap>> getmListNCCLiveData() {
        return mListNCCLiveData;
    }
}
package com.example.fashionapp.ui.the;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.fashionapp.R;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.TheDAO;
import com.example.fashionapp.database.repository.TheImp;
import com.example.fashionapp.models.KhachHang;
import com.example.fashionapp.models.The;
import com.example.fashionapp.ui.RecyclerViewClickItem;
import com.example.fashionapp.ui.TheCommunicator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TheActivity extends AppCompatActivity implements TheCommunicator, RecyclerViewClickItem {

    Toolbar mToolbar;
    RecyclerView rec_the;
    FloatingActionButton add_btn;
    List<The> theList;
    TheAdapter adapter;
    KhachHang khachHang;
    Database database;
    The preThe;
    TheDAO theDAO;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the);

        database = new Database(this);
        khachHang = getKH();
        theDAO = new TheImp();
        value = "";
        mToolbar = (Toolbar) findViewById(R.id.toolbar_the);
        rec_the = findViewById(R.id.rec_the);
        add_btn = findViewById(R.id.the_btn_add);
        rec_the.setLayoutManager(new LinearLayoutManager(this));
        theList = new ArrayList<>();
        theList = getThe(khachHang.getIDKH());
        adapter = new TheAdapter(this, theList, this, xoaThe);
        rec_the.setAdapter(adapter);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        value = intent.getStringExtra("select");

        addEvents();


    }

    private List<The> getThe(int IDKH) {
        TheDAO theDAO = new TheImp();
        return theDAO.getTheByIDKH(database, IDKH);

    }

    private void addEvents() {
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                The the = null;
                loadFragment(the);
            }
        });
    }

    private void loadFragment(The the) {
        Bundle bundle = new Bundle();
        bundle.putInt("IDKH", khachHang.getIDKH());
        bundle.putSerializable("the", the);
        DialogFragment dialog = new ThemTheFragmentDialog();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    private KhachHang getKH() {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("current_kh", "");
        if (json != "") {
            KhachHang khachHang = gson.fromJson(json, KhachHang.class);
            return khachHang;
        }
        return null;
    }

    @Override
    public void themThe(String action, The the) {
        if (action.equals("del")) {
            theList.remove(preThe);
            theDAO.capNhatThe(database, preThe, the);
            adapter.notifyDataSetChanged();

        }
        if (action.equals("add")) {
            theDAO.themThe(database, the);
        }
        theList.add(the);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        preThe = theList.get(position);
        if (value != null && value.equals("order")) {
            Intent intent=new Intent();
            intent.putExtra("order_the",preThe);
            setResult(RESULT_OK,intent);
            finish();
        } else {
            loadFragment(preThe);
        }
    }

    private XoaThe xoaThe = new XoaThe() {
        @Override
        public void xoaThe(int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(TheActivity.this);

            builder.setMessage("Bạn có chắc chắn muốn xóa thông tin thẻ hay không?");
            builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    The the = theList.get(position);
                    theList.remove(position);
                    theDAO.xoaThe(database, the);
                    adapter.notifyDataSetChanged();
                }
            });

            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    };


}
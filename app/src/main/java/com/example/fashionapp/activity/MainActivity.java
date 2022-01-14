package com.example.fashionapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.fashionapp.R;
import com.example.fashionapp.database.Database;
import com.example.fashionapp.database.dao.GioHangDAO;
import com.example.fashionapp.database.repository.GioHangImp;
import com.example.fashionapp.models.KhachHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private NavController controller;
    private BottomNavigationView navigationView;
    private Toolbar toolbar;
    public static Database database;
    SearchView searchView;

    MenuItem menuItem;
    TextView txt_count;
    int item_count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigation_view);
        controller = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView, controller);

        toolbar=findViewById(R.id.toolbar_main);
        NavigationUI.setupWithNavController(toolbar,controller);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!searchView.isIconified()){
                    searchView.setIconified(true);
                    return;
                }
                controller.popBackStack();
            }
        });

        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter("action_cart"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                return true;
            }
        };

        menu.findItem(R.id.search).setOnActionExpandListener(onActionExpandListener);
        searchView  = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("Tìm kiếm sản phẩm");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Bundle bundle=new Bundle();
                bundle.putString("key",s);
                if(controller.getCurrentDestination().getId()==R.id.searchFragment){
                    controller.popBackStack();
                }
                controller.navigate(R.id.searchFragment,bundle);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                Bundle bundle=new Bundle();
//                bundle.putString("key",s);
//                if(controller.getCurrentDestination().getId()==R.id.searchFragment){
//                    controller.popBackStack();
//                }
//                controller.navigate(R.id.searchFragment,bundle);
                return false;
            }
        });

        menuItem=menu.findItem(R.id.cart);
        item_count=demGioHang();
        if(item_count==0){
            menuItem.setActionView(null);
        }
        else {
            menuItem.setActionView(R.layout.noti_badge);
            View view=menuItem.getActionView();
            txt_count=view.findViewById(R.id.txt_count_cart);
            txt_count.setText(item_count+"");
            menuItem.getActionView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.this.onOptionsItemSelected(menuItem);
                }
            });

        }

        return true;
    }

    private int demGioHang(){
        Database database=new Database(this);
        GioHangDAO gioHangDAO=new GioHangImp();
        return gioHangDAO.getGioHang(database,getKH().getIDKH()).size();
    }
    private KhachHang getKH(){
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("current_kh", "");
        if(json!="") {
            KhachHang khachHang = gson.fromJson(json, KhachHang.class);
            return khachHang;
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.cart:
                controller.navigate(R.id.myCartFragment);
                break;

        }
        return super.onOptionsItemSelected(item)||NavigationUI.onNavDestinationSelected(item,controller);
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getStringExtra("key");
            if(action.equals("add")) item_count+=1;
            if(action.equals("del")) item_count-=1;
            if(action.equals("buy")) item_count=0;
            invalidateOptionsMenu();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
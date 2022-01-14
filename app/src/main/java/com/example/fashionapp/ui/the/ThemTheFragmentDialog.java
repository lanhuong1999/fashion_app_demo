package com.example.fashionapp.ui.the;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fashionapp.R;
import com.example.fashionapp.models.The;
import com.example.fashionapp.ui.TheCommunicator;

import java.util.ArrayList;
import java.util.Arrays;

public class ThemTheFragmentDialog extends DialogFragment  {

    EditText edt_stk;
    Spinner tennh;
    Button btn_huy, btn_xn;
    int IDKH;
    TheCommunicator communicator;
    int pos=0;
    ArrayList<String> arr_tennh;
    The mThe;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.fragment_them_the_dialog, null);
        dialogBuilder.setView(dialogView);
        communicator= (TheCommunicator) getContext();
        tennh=dialogView.findViewById(R.id.add_the_spinner_ten);
        edt_stk=dialogView.findViewById(R.id.add_the_edt_stk);
        btn_huy=dialogView.findViewById(R.id.add_the_btn_huy);
        btn_xn=dialogView.findViewById(R.id.add_the_btn_xn);
        setCancelable(false);

        String[] strings=getResources().getStringArray(R.array.ten_ngan_hang);
        arr_tennh = new ArrayList<>(Arrays.asList(strings));
        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,arr_tennh);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tennh.setAdapter(arrayAdapter);

        Bundle bundle=getArguments();
        if(bundle!=null){
            IDKH=bundle.getInt("IDKH");
            mThe= (The) bundle.getSerializable("the");
            if(mThe!=null) setView(mThe);
        }

        addEvents();

        return dialogBuilder.create();
    }

    private void addEvents() {
        tennh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pos=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btn_xn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mThe==null) {
                    The the = getThe();
                    if (the != null) {
                        communicator.themThe("add", the);
                        dismiss();
                    }
                }
                else{
                    The the = getThe();
                    if (the != null) {
                        communicator.themThe("del", the);
                        dismiss();
                    }
                }
            }
        });


    }

    private The getThe(){
        String ten=arr_tennh.get(pos);
        String stk=edt_stk.getText().toString();
        if(TextUtils.isEmpty(ten)){
            Toast.makeText(getActivity(),"Vui lòng nhập thông tin tên ngân hàng",Toast.LENGTH_SHORT).show();
            return null;
        }
        if(TextUtils.isEmpty(stk)){
            Toast.makeText(getActivity(),"Vui lòng nhập thông tin tên số tài khoản",Toast.LENGTH_SHORT).show();
            return null;

        }
        return new The(IDKH,stk,ten);
    }

    private void setView(The the){
        edt_stk.setText(the.getSoTK());
        int index=arr_tennh.indexOf(the.getTenNH());
        tennh.setSelection(index);
    }

}
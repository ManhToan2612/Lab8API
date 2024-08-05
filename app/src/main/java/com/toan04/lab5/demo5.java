package com.toan04.lab5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.toan04.lab5.delete.InterfaceDelete;
import com.toan04.lab5.select.InterfaceSelect;
import com.toan04.lab5.select.SvrResponseSelect;
import com.toan04.lab5.update.InterfaceUpdateSP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class demo5 extends AppCompatActivity {

    EditText txt1, txt2, txt3;
    TextView tvkq;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo5);
        txt1=findViewById(R.id.demo5txt1);
        txt2=findViewById(R.id.demo5txt2);
        txt3=findViewById(R.id.demo5txt3);
        tvkq=findViewById(R.id.demo5txtkq);
        btn= findViewById(R.id.demo5btn);
        btn.setOnClickListener(v->{
            insertData(txt1, txt2, txt3, tvkq);
            //selectDaTa();
            //deleteData(txt1);
//            updateDta(txt1, txt2, txt3, tvkq);
        });
    }

    private void insertData(EditText txt1, EditText txt2, EditText txt3, TextView tvkq){
        // b1- tao doi tuong chua du lieu
        sanpham sp=new sanpham(txt1.getText().toString(), txt2.getText().toString(), txt3.getText().toString());

        //b2- tao doi tuong retrofit
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://192.168.0.104/lab5/").addConverterFactory(GsonConverterFactory.create()).build();

        //b3- goi ham insert
        InterfaceInsertSP insertSP = retrofit.create(InterfaceInsertSP.class);

        //chuan bi ham
        Call<SvrResponseSP> call= insertSP.insertSP(sp.getMaMP(), sp.getTenSP(), sp.getMoTa());

        // thuc thi ham
        call.enqueue(new Callback<SvrResponseSP>() {
            // thanh cong
            @Override
            public void onResponse(Call<SvrResponseSP> call, Response<SvrResponseSP> response) {
                SvrResponseSP res= response.body();
                tvkq.setText(res.getMessage());
            }

            //that bai
            @Override
            public void onFailure(Call<SvrResponseSP> call, Throwable throwable) {
                tvkq.setText(throwable.getMessage());
            }
        });
    }

    private void deleteData(EditText txt1){
        //b2- tao doi tuong retrofit
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://192.168.0.104/lab5/").addConverterFactory(GsonConverterFactory.create()).build();

        //b3- goi ham delete
        InterfaceDelete deleteSP = retrofit.create(InterfaceDelete.class);

        //chuan bi ham
        Call<SvrResponseSP> call= deleteSP.deleteSP(txt1.getText().toString());

        // thuc thi ham
        call.enqueue(new Callback<SvrResponseSP>() {
            // thanh cong
            @Override
            public void onResponse(Call<SvrResponseSP> call, Response<SvrResponseSP> response) {
                SvrResponseSP res= response.body();
                tvkq.setText(res.getMessage());
            }

            //that bai
            @Override
            public void onFailure(Call<SvrResponseSP> call, Throwable throwable) {
                tvkq.setText(throwable.getMessage());
            }
        });
    }

    private void updateDta(EditText txt1, EditText txt2, EditText txt3, TextView tvkq){
        // b1- tao doi tuong chua du lieu
        sanpham sp=new sanpham(txt1.getText().toString(), txt2.getText().toString(), txt3.getText().toString());

        //b2- tao doi tuong retrofit
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://192.168.0.104/lab5/").addConverterFactory(GsonConverterFactory.create()).build();

        //b3- goi ham update
        InterfaceUpdateSP updateSP = retrofit.create(InterfaceUpdateSP.class);

        //chuan bi ham
        Call<SvrResponseSP> call= updateSP.updateSP(sp.getMaMP(), sp.getTenSP(), sp.getMoTa());

        // thuc thi ham
        call.enqueue(new Callback<SvrResponseSP>() {
            // thanh cong
            @Override
            public void onResponse(Call<SvrResponseSP> call, Response<SvrResponseSP> response) {
                SvrResponseSP res= response.body();
                tvkq.setText(res.getMessage());
            }

            //that bai
            @Override
            public void onFailure(Call<SvrResponseSP> call, Throwable throwable) {
                tvkq.setText(throwable.getMessage());
            }
        });
    }
    String strKQ="";
    List<sanpham> list;
    private void selectDaTa(){
        strKQ="";

        // 1- Tao doi tuong retrofit
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://192.168.0.104/lab5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 2- Goi ham select trong interface
        // 2.1- Tao doi tuong
        InterfaceSelect interfaceSelect= retrofit.create(InterfaceSelect.class);

        //2.2- Chuan bij ham
        Call<SvrResponseSelect> call= interfaceSelect.selectSanPham();

        //2.3- Thuc thi request
        call.enqueue(new Callback<SvrResponseSelect>() {
            @Override
            public void onResponse(Call<SvrResponseSelect> call, Response<SvrResponseSelect> response) {
                SvrResponseSelect res= response.body(); // lat ket qua tu server tra ve

                // chuyen doi ket qua sang dang list
                list= new ArrayList<>(Arrays.asList(res.getProducts()));

                // doc list
                for (sanpham p: list){
                    strKQ+= "MaMP: " + p.getMaMP()+ "; TenSP: " + p.getTenSP()+ "; MoTa" + p.getMoTa()+ "\n";
                }
                tvkq.setText(strKQ);
            }

            @Override
            public void onFailure(Call<SvrResponseSelect> call, Throwable throwable) {
                tvkq.setText(throwable.getMessage());
            }
        });
    }
}
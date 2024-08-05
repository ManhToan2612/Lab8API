package com.toan04.lab5.demo7;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.toan04.lab5.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class demo7 extends AppCompatActivity {
    EditText txt1, txt2, txt3, txt4;
    Button btnSelect, btndelete, btninsert, btnupdate;
    TextView tvkq;
    Context context= this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo7);
        txt1= findViewById(R.id.demo7txt1);
        txt2= findViewById(R.id.demo7txt2);
        txt3= findViewById(R.id.demo7txt3);
        txt4= findViewById(R.id.demo7txt4);
        btnSelect= findViewById(R.id.demo7txtselect);
        btndelete= findViewById(R.id.demo7delete);
        btninsert= findViewById(R.id.btnupdate);
        btnupdate= findViewById(R.id.btninsert);
        tvkq= findViewById(R.id.demo7tvkq);
        btnSelect.setOnClickListener(v->{
            selectVolley();
        });
        btndelete.setOnClickListener(v->{
           deleteVolley();
        });
        btninsert.setOnClickListener(v->{
            insertVolley();
        });
        btnupdate.setOnClickListener(v->{
            updateVolley();
        });
    }

    private void insertVolley() {
        //b1- chuan bi du lieu
        //b2- tao reque
        RequestQueue queue= Volley.newRequestQueue(context);
        //b3- url
        String url="https://hungnq28.000webhostapp.com/su2024/insert.php";
        //b4- xac dinh loai request
        // StringRequest(method, thanh cong, that bai({thamso}
        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tvkq.setText(s.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tvkq.setText(volleyError.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> mydata= new HashMap<>();
                mydata.put("name", txt2.getText().toString());
                mydata.put("price", txt3.getText().toString());
                mydata.put("description", txt4.getText().toString());
                return mydata;
            }
        };
    }

    private void updateVolley() {
        //b1- chuan bi du lieu
        //b2- tao reque
        RequestQueue queue= Volley.newRequestQueue(context);
        //b3- url
        String url="https://hungnq28.000webhostapp.com/su2024/update.php";
        //b4- xac dinh loai request
        // StringRequest(method, thanh cong, that bai({thamso}
        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tvkq.setText(s.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tvkq.setText(volleyError.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> mydata= new HashMap<>();
                mydata.put("pid", txt1.getText().toString());
                mydata.put("name", txt2.getText().toString());
                mydata.put("price", txt3.getText().toString());
                mydata.put("description", txt4.getText().toString());
                return mydata;
            }
        };
    }

    private void deleteVolley() {
        //1- chuan bi du lieu
        //2- tao queue
        RequestQueue queue= Volley.newRequestQueue(context);
        //3- Xac url
        String url="https://hungnq28.000webhostapp.com/su2024/delete.php";
        //4- Xac dinh loai request
        // StringRequest(method, thanh cong, that bai({thamso}
;
        StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tvkq.setText(s.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tvkq.setText(volleyError.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata= new HashMap<>();
                mydata.put("pid", txt1.getText().toString());
                return mydata;
            }
        };
    }

    String strkq="";
    void selectVolley(){
        strkq="";
        //1- tao requeset
        RequestQueue requestQueue= Volley.newRequestQueue(context);

        //2- url:
        String url="https://hungnq28.000webhostapp.com/su2024/select.php";

        //3- goi request:
        // lay ve object
        JsonObjectRequest request= new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) { // thanh cong
                try{
                    JSONArray jsonArray= jsonObject.getJSONArray("products");
                    for (int i = 0; i < jsonArray.length(); i++){
                        // lay ve array: "products":[
                        JSONObject p = jsonArray.getJSONObject(i);// lay ve doi tuong i
                        String MaSP= p.getString("MaSP");
                        String TenSP= p.getString("TenSP");
                        String MoTa= p.getString("MoTa");
                        strkq += "MaSP: " + MaSP + "; TenSP: " + TenSP + "; MoTa" + MoTa + "\n";
                    }
                    tvkq.setText(strkq);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) { // that bai
                tvkq.setText(volleyError.getMessage());
            }
        });
        //4- thuc thi requset
        requestQueue.add(request);
    }
}
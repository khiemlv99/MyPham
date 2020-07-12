package com.example.quanlybanmyphamonline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlybanmyphamonline.Adapter.VerticalRecyclerViewAdapter;
import com.example.quanlybanmyphamonline.Class.HorizontalModel;
import com.example.quanlybanmyphamonline.Class.Server;
import com.example.quanlybanmyphamonline.Class.VerticalModel;
import com.example.quanlybanmyphamonline.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txtGiaSP,txtTenSP,txtMoTaSP;
    Button btnThemGH;
    ArrayList<VerticalModel> arrayListVertical;
    ArrayList<HorizontalModel> horizontalModelArrayList;
    VerticalModel verticalModel;
    RecyclerView verticalRecyclerView;
    VerticalRecyclerViewAdapter adapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4BCEDF")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AnhXa();
        DoDuLieu();
        Recyclerview();


    }

    public void AnhXa()
    {
       imageView = findViewById(R.id.imageviewctsp);
       btnThemGH=findViewById(R.id.btnThemGH);
       txtGiaSP=findViewById(R.id.txtGiaCTSP);
       txtTenSP=findViewById(R.id.txtTenCTSP);
       txtMoTaSP=findViewById(R.id.txtMoTaCTSP);
       verticalRecyclerView=findViewById(R.id.recyclerViewDetail);
    }

    public void DoDuLieu()
    {
        intent =getIntent();
        txtTenSP.setText(intent.getStringExtra("tensp"));
        txtMoTaSP.setText(intent.getStringExtra("motasp"));
        txtGiaSP.setText(intent.getStringExtra("giasp"));
        Picasso.get().load(intent.getStringExtra("hinh")).placeholder(R.drawable.bill32).error(R.drawable.cart32).into(imageView);
    }

    private void setData() {
        verticalModel = new VerticalModel();
        verticalModel.setTilte("Sản phẩm liên quan");

        horizontalModelArrayList = new ArrayList<>();
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdansanphamcungloai, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int masanpham = jsonObject.getInt("masp");
                        String tensp = jsonObject.getString("tensp");

                        String mota = jsonObject.getString("mota");
                        int gia = jsonObject.getInt("gia");
                        int maloaisanpham = jsonObject.getInt("MaLoaiSanPham");
                        String hinh = jsonObject.getString("hinh");


                        HorizontalModel horizontalModel = new HorizontalModel();
                        horizontalModel.setGia("Giá : " + gia);
                        horizontalModel.setMota(mota);
                        horizontalModel.setTen(tensp);
                        horizontalModel.setHinh(hinh);
                        horizontalModel.setMaloaisp(maloaisanpham);
                        horizontalModel.setMasp(masanpham);

                        horizontalModelArrayList.add(horizontalModel);
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
           }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>();
                String ma= String.valueOf(intent.getIntExtra("maloaisp",0));
                param.put("LoaiSanPham",String.valueOf(ma));
                return  param;
            }
        };

        requestQueue.add(stringRequest);


        verticalModel.setArrayList(horizontalModelArrayList);
        arrayListVertical.add(verticalModel);
        adapter.notifyDataSetChanged();
    }

    public void Recyclerview() {
        arrayListVertical = new ArrayList<>();
        verticalRecyclerView.setHasFixedSize(true);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new VerticalRecyclerViewAdapter(this, arrayListVertical);
        verticalRecyclerView.setAdapter(adapter);
        setData();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DetailActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        return;
    }

}
package com.example.quanlybanmyphamonline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.quanlybanmyphamonline.Adapter.LoaiSPAdapter;
import com.example.quanlybanmyphamonline.Fragment.CaNhanFragment;
import com.example.quanlybanmyphamonline.Fragment.DanhMucFragment;
import com.example.quanlybanmyphamonline.Fragment.ThongBaoFragment;
import com.example.quanlybanmyphamonline.Fragment.TimKiemFragment;
import com.example.quanlybanmyphamonline.Fragment.TrangChuFragment;
import com.example.quanlybanmyphamonline.R;
import com.example.quanlybanmyphamonline.Class.SendData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements SendData {

    BottomNavigationView bottomNavigationView;
    EditText searchview;
    ImageView imageView;
    LinearLayout layout;
    LoaiSPAdapter loaiSPAdapter;
    boolean status =false;
    TrangChuFragment trangChuFragment;
    MenuItem menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4BCEDF")));

        bottomNavigationView = findViewById(R.id.nav_main);
        searchview=findViewById(R.id.search_view);
        imageView=findViewById(R.id.btnGioHang);
        layout=findViewById(R.id.search_container);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        bottomNavigationView.setSelectedItemId(R.id.navigation_trangchu);
        searchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimKiemFragment fragment1= new TimKiemFragment();
                layout.setVisibility(View.GONE);
                loadFragment(fragment1);
            }
        });

    }


    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId())
            {
                case R.id.navigation_trangchu:
                    getSupportActionBar().setTitle("Trang chủ");
                    TrangChuFragment fragment = new TrangChuFragment();
                    layout.setVisibility(View.VISIBLE);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_TimKiem:
                    TimKiemFragment fragment1= new TimKiemFragment();
                    layout.setVisibility(View.GONE);
                    loadFragment(fragment1);
                    return true;
                case R.id.navigation_Danhmuc:
                    getSupportActionBar().setTitle("Danh mục");
                    DanhMucFragment fragment2 = new DanhMucFragment();
                    layout.setVisibility(View.VISIBLE);
                    loadFragment(fragment2);
                    return true;
                case R.id.navigation_ThongBao:
                    getSupportActionBar().setTitle("Thông báo");
                    ThongBaoFragment fragment3 = new ThongBaoFragment();
                    layout.setVisibility(View.VISIBLE);
                    loadFragment(fragment3);
                    return true;
                case R.id.navigation_CaNhan:

                    getSupportActionBar().setTitle("Cá nhân");
                    CaNhanFragment fragment4 = new CaNhanFragment();
                    layout.setVisibility(View.GONE);
                    loadFragment(fragment4);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.cart_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public void sendData(int name) {
        DanhMucFragment demo1Fragment =new DanhMucFragment();
        if (demo1Fragment != null || demo1Fragment.isInLayout()) { // kiem tra fragment can truyen data den co thuc su ton tai va dang hien
            {
                this.setIdloai(name);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Fragment is not exist", Toast.LENGTH_LONG).show();
        }
    }

    public int getIdloai() {
        return idloai;
    }

    public void setIdloai(int idloai) {
        this.idloai = idloai;
    }

    public  static int idloai=0;
}

package com.example.quanlybanmyphamonline.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quanlybanmyphamonline.activity.DangNhapActivity;
import com.example.quanlybanmyphamonline.R;
import com.example.quanlybanmyphamonline.activity.MainActivity;

import static android.content.Context.MODE_PRIVATE;


public class CaNhanFragment extends Fragment {

     Button btnDangNhapDangKy,btnDangXuat;
    String name;
    SharedPreferences sharedPreferences;
    public CaNhanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ca_nhan, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
        btnDangNhapDangKy = view.findViewById(R.id.buttonDangNhapDangKy);
        btnDangXuat=view.findViewById(R.id.btnDangXuat);
        sharedPreferences = getActivity().getSharedPreferences("ThongTin",MODE_PRIVATE);
        name=sharedPreferences.getString("name","false");
        String hoa= name.toUpperCase();
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences= getContext().getSharedPreferences("ThongTin", MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
                loadFragment(new CaNhanFragment());
            }
        });

        if(!name.equals("false"))
        {
            btnDangNhapDangKy.setText("Chào mừng "+hoa);

        }
        else
        {
            btnDangNhapDangKy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), DangNhapActivity.class);
                    startActivity(intent);
                }
            });
        }


    }
    public void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

package com.example.quanlybanmyphamonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlybanmyphamonline.Class.HorizontalModel;
import com.example.quanlybanmyphamonline.R;
import com.example.quanlybanmyphamonline.activity.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerviewTrangChuAdapter extends RecyclerView.Adapter<RecyclerviewTrangChuAdapter.RecylerViewHolder>  {

    public Context context;
    public ArrayList<HorizontalModel> arrayList;

    public RecyclerviewTrangChuAdapter(Context context, ArrayList<HorizontalModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecylerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerviewtrangchu,parent,false);
        return new RecyclerviewTrangChuAdapter.RecylerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewHolder holder, final int position) {
        final HorizontalModel horizontalModel=arrayList.get(position);
        holder.txtTen.setText(horizontalModel.getTen());
        holder.txtGia.setText(horizontalModel.getGia());

        Picasso.get().load(horizontalModel.getHinh()).placeholder(R.drawable.bill32).error(R.drawable.cart32).into(holder.imageView);

        holder.txtTen.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));

        holder.txtGia.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));
        holder.imageView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("tensp",arrayList.get(position).getTen());
                intent.putExtra("giasp",arrayList.get(position).getGia());
                intent.putExtra("motasp",arrayList.get(position).getMota());
                intent.putExtra("hinh",arrayList.get(position).getHinh());
                intent.putExtra("maloaisp",arrayList.get(position).getMaloaisp());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public  class  RecylerViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTen,txtGia;
        ImageView imageView;
        public RecylerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen =(TextView)itemView.findViewById(R.id.tensanpham);
            txtGia =(TextView)itemView.findViewById(R.id.giasanpham);
            imageView=(ImageView)itemView.findViewById(R.id.imageviewsanpham);
        }
    }
}


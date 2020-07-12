package com.example.quanlybanmyphamonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlybanmyphamonline.Class.HorizontalModel;
import com.example.quanlybanmyphamonline.R;
import com.example.quanlybanmyphamonline.activity.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HorizontalRecylerViewAdapter extends RecyclerView.Adapter<HorizontalRecylerViewAdapter.HorizontalViewHolder> {

    public Context context;
    public ArrayList<HorizontalModel> arrayList;



    public  HorizontalRecylerViewAdapter(Context context , ArrayList<HorizontalModel>arrayList)
    {
        this.arrayList=arrayList;
        this.context=context;

    }
    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal,parent,false);
        return new HorizontalViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final HorizontalViewHolder holder, final int position) {
            final HorizontalModel horizontalModel=arrayList.get(position);
            holder.txtTen.setText(horizontalModel.getTen());
            holder.txtGia.setText(horizontalModel.getGia());
            holder.txtMoTa.setText(horizontalModel.getMota());
            Picasso.get().load(horizontalModel.getHinh()).placeholder(R.drawable.bill32).error(R.drawable.cart32).into(holder.imageView);

            holder.txtTen.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));
             holder.txtMoTa.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));
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

    public  class  HorizontalViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTen,txtGia,txtMoTa;
        ImageView imageView;

        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTen =(TextView)itemView.findViewById(R.id.txtTextTilteHorizontal);
            txtGia =(TextView)itemView.findViewById(R.id.txtGiaBan);
            txtMoTa =(TextView)itemView.findViewById(R.id.txtMoTa);
            imageView=(ImageView)itemView.findViewById(R.id.Image);

        }
    }
}

package com.example.quanlybanmyphamonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlybanmyphamonline.Class.HorizontalModel;
import com.example.quanlybanmyphamonline.Class.VerticalModel;
import com.example.quanlybanmyphamonline.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalViewHolder> {

    public  Context context;
    public ArrayList<VerticalModel> arrayList;

    public  VerticalRecyclerViewAdapter(Context context , ArrayList<VerticalModel> arrayList)
    {
        this.arrayList=arrayList;
        this.context=context;

    }

    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical,parent,false);
        return new VerticalViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VerticalViewHolder holder, int position) {
          final VerticalModel verticalModel = arrayList.get(position);
          String title =verticalModel.getTilte();
          ArrayList<HorizontalModel> singleitem= verticalModel.getArrayList();

          holder.textView.setText(title);
        HorizontalRecylerViewAdapter horizontalRecylerViewAdapter=new HorizontalRecylerViewAdapter(context,singleitem);

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(horizontalRecylerViewAdapter);
        holder.textView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));
        holder.button.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));
        holder.container.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));
        holder.recyclerView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,verticalModel.getTilte(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  class VerticalViewHolder extends RecyclerView.ViewHolder
    {
        RecyclerView recyclerView;
        TextView textView;
        Button button;
        RelativeLayout container;
        public VerticalViewHolder(@NonNull View itemView)
        {
            super(itemView);
            recyclerView =(RecyclerView)itemView.findViewById(R.id.recyclerViewVertical);
             textView=(TextView)itemView.findViewById(R.id.txtTitle);
             button = (Button)itemView.findViewById(R.id.btnMore);
             container=itemView.findViewById(R.id.Container1);
        }
    }

}

package com.app.appcafe.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.appcafe.Activity.ListDrinksActivity;
import com.app.appcafe.Domain.Category;
import com.app.appcafe.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {
    ArrayList<Category> items;
    Context context;
    public CategoryAdapter(ArrayList<Category> items){
        this.items = items;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new viewholder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.catNameTxt.setText(items.get(position).getName());
        @SuppressLint("DiscouragedApi") int drawableResourceId = context.getResources().getIdentifier(items.get(position).getImagePath(),
                "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.imageCat);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListDrinksActivity.class);
                intent.putExtra("CategoryId",items.get(position).getId());
                intent .putExtra("CategoryName",items.get(position).getName());
                context.startActivity(intent);
            }
        });
    };

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView catNameTxt;
        ImageView imageCat;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            catNameTxt = itemView.findViewById(R.id.catNameTxt);
            imageCat = itemView.findViewById(R.id.imageCat);

        }
    }
}

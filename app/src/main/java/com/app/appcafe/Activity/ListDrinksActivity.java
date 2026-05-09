package com.app.appcafe.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.app.appcafe.Adapter.DrinkListAdapter;
import com.app.appcafe.Domain.Drinks;
import com.app.appcafe.R;
import com.app.appcafe.databinding.ActivityListDrinksBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListDrinksActivity extends BaseActivity {
    ActivityListDrinksBinding binding;
    private Adapter adapterListDrink;
    private int categoryId;
    private String searchText;
    private boolean isSearch;

    public ListDrinksActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListDrinksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtra();
        initList();
    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Drinks");
        binding.progressBarListDrink.setVisibility(View.VISIBLE);
        ArrayList<Drinks> list = new ArrayList<>();
        Query query;
        if(isSearch){
            query = myRef.orderByChild("Title").startAt(searchText).endAt(searchText+'\uf8ff');
        }else{
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Drinks.class));
                    }
                    if(list.size()>0){
                        binding.drinkListView.setLayoutManager(new GridLayoutManager(ListDrinksActivity.this,2));
                        adapterListDrink = new DrinkListAdapter(list);
                        binding.drinkListView.setAdapter(adapterListDrink);
                    }
                    binding.progressBarListDrink.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId",0);
        String categoryName = getIntent().getStringExtra("CategoryName");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch",false);

        binding.titleTxt.setText(categoryName);
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
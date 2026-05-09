package com.app.appcafe.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.app.appcafe.Adapter.BestDrinksAdapter;
import com.app.appcafe.Adapter.CategoryAdapter;
import com.app.appcafe.Domain.Category;
import com.app.appcafe.Domain.Drinks;
import com.app.appcafe.Domain.Location;
import com.app.appcafe.Domain.Price;
import com.app.appcafe.Domain.TimeOrder;
import com.app.appcafe.R;
import com.app.appcafe.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        initLocation();
        initTimeOrder();
        initPrice();
        initBestDrinks();
        initCategory();
        setVariable();
    }

    private void setVariable() {
        drawerLayout = binding.drawerLayout;
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        ImageView imgAboutUs = findViewById(R.id.imgAboutUs);
        imgAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });
        binding.LogoutBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = binding.searchEdt.getText().toString();
                if(!text.isEmpty()){
                    Intent intent = new Intent(MainActivity.this, ListDrinksActivity.class);
                    intent.putExtra("text",text);
                    intent.putExtra("isSearch",true);
                    startActivity(intent);

                }
            }
        });
        binding.cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
        binding.settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettingsScreen();

            }

            private void openSettingsScreen() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_lichSu) {
                Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_thongTin) {
                // Chuyển sang InforActivity
                try {
                    Intent intent = new Intent(MainActivity.this, InforActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("MainActivity", "Lỗi khi chuyển sang InforActivity", e);
                }
            } // Xử lý các item khác ở đây (Voucher, ...)

            drawerLayout.closeDrawer(GravityCompat.START); // Đóng drawer sau khi xử lý sự kiện
            return true;
        });

    }

    private void initBestDrinks() {
        DatabaseReference myRef = database.getReference("Drinks");
        binding.progressBarBestDrink.setVisibility(View.VISIBLE);
        ArrayList<Drinks> list = new ArrayList<>();
        Query query = myRef.orderByChild("BestDrink").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Drinks.class));
                    }
                    if(list.size()>0){
                        binding.recyclerViewBestDrink.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        RecyclerView.Adapter<BestDrinksAdapter.viewholder> adapter = new BestDrinksAdapter(list);
                        binding.recyclerViewBestDrink.setAdapter(adapter);
                    }
                    binding.progressBarBestDrink.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initCategory() {
        DatabaseReference myRef = database.getReference("Category");
        binding.progressBarCate.setVisibility(View.VISIBLE);
        ArrayList<Category> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Category.class));
                    }
                    if(list.size()>0){
                        binding.recyclerViewCate.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
                        RecyclerView.Adapter<CategoryAdapter.viewholder> adapter = new CategoryAdapter(list);
                        binding.recyclerViewCate.setAdapter(adapter);
                    }
                    binding.progressBarCate.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void initLocation() {
        DatabaseReference myRef = database.getReference("Location");
        ArrayList<Location> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Location.class));
                    }
                    ArrayAdapter<Location> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.locationSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initTimeOrder() {
        DatabaseReference myRef = database.getReference("TimeOrder");
        ArrayList<TimeOrder> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue: snapshot.getChildren()){
                        TimeOrder i = issue.getValue(TimeOrder.class);
                        list.add(i);
                    }
                    ArrayAdapter<TimeOrder> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.timeSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initPrice() {
        DatabaseReference myRef = database.getReference("Price");
        ArrayList<Price> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Price.class));
                    }
                    ArrayAdapter<Price> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.dollarSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
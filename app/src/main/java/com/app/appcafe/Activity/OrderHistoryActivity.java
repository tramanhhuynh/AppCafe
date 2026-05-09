package com.app.appcafe.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.app.appcafe.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        DrawerLayout drawerLayout;
        ArrayList<OrderHistoryItem> orderHistoryList = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_order_history);

                drawerLayout = findViewById(R.id.drawer_layout);
                NavigationView navigationView = findViewById(R.id.navigationViewOrderHistory);
                navigationView.setNavigationItemSelectedListener(this);

                layDuLieuLichSuDonHang();

                Menu menu = navigationView.getMenu();
                SubMenu subMenu = menu.addSubMenu("Lịch sử đơn hàng");

                for (OrderHistoryItem item : orderHistoryList) {
                        subMenu.add(Menu.NONE, Menu.NONE, Menu.NONE, item.getOrderId())
                                .setOnMenuItemClickListener(menuItem -> {
                                        Intent intent = new Intent(OrderHistoryActivity.this, OrderDetailActivity.class);
                                        intent.putExtra("orderId", item.getOrderId());
                                        startActivity(intent);
                                        return true;
                                });
                }
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String orderId = item.getTitle().toString();

                Intent intent = new Intent(OrderHistoryActivity.this, OrderDetailActivity.class);
                intent.putExtra("orderId", orderId);
                startActivity(intent);

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }

        private void layDuLieuLichSuDonHang() {
                orderHistoryList.add(new OrderHistoryItem("#12345", "25/10/2024"));
                // ... thêm các mục lịch sử đơn hàng khác
        }
}
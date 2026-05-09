package com.app.appcafe.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.app.appcafe.R;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    TextView orderIdTextView, orderDateTextView, totalAmountTextView;
    RecyclerView orderItemsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        orderIdTextView = findViewById(R.id.orderIdTextView);
        orderDateTextView = findViewById(R.id.orderDateTextView);
        totalAmountTextView = findViewById(R.id.totalAmountTextView);
        orderItemsRecyclerView = findViewById(R.id.orderItemsRecyclerView);
        orderItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String orderId = getIntent().getStringExtra("orderId");
        hienThiChiTietDonHang(orderId);
    }

    private void hienThiChiTietDonHang(String orderId) {
        // 1. Lấy chi tiết đơn hàng từ database hoặc API dựa trên orderId
        //    Ví dụ:
        //    Order order = getOrderFromDatabase(orderId);

        // 2. Hiển thị chi tiết đơn hàng lên giao diện
        //    Ví dụ:
        //    orderIdTextView.setText("Order ID: " + order.getOrderId());
        //    orderDateTextView.setText("Order Date: " + order.getOrderDate());
        //    totalAmountTextView.setText("Total Amount: $" + order.getTotalAmount());

        // 3. Tạo OrderItemAdapter và hiển thị danh sách sản phẩm
        //    Ví dụ:
        //    ArrayList<OrderItem> orderItems = order.getOrderItems();
        //    OrderItemAdapter adapter = new OrderItemAdapter(orderItems);
        //    orderItemsRecyclerView.setAdapter(adapter);
    }
}
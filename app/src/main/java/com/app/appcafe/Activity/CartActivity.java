package com.app.appcafe.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.appcafe.Adapter.CartAdapter;
import com.app.appcafe.Domain.Order;
import com.app.appcafe.Helper.ChangeNumberItemsListener;
import com.app.appcafe.Helper.ManagmentCart;
import com.app.appcafe.R;
import com.app.appcafe.databinding.ActivityCartBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartActivity extends BaseActivity implements ChangeNumberItemsListener {
    private ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    private double tax;

    @Override
    public void change() {
        calculateCart();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);
        
        setVariable();
        calculateCart();
        initList();
    }

    private void initList() {
        if(managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollViewCart.setVisibility(View.GONE);
        }else{
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollViewCart.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.cardView.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managmentCart.getListCart(),this,this);
                binding.cardView.setAdapter(adapter);

    }

    private void calculateCart() {
        double percentTax = 0.02;
        double delivery = 10;

        tax = (double) Math.round(managmentCart.getTotalFee() * percentTax * 100.0) /100;
        double total = (double) (Math.round((managmentCart.getTotalFee() + tax + delivery)) * 100) /100;
        double itemTotal = (double) Math.round(managmentCart.getTotalFee() * 100) /100;

        binding.totalFeeTxt.setText(itemTotal+"Đ");
        binding.taxTxt.setText(tax+"Đ");
        binding.totalTxt.setText(total+"Đ");
        binding.deliveryTxt.setText(delivery+"Đ");
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,PaymentActivity.class));
                managmentCart.clearCart();
            }
        });
    }
}
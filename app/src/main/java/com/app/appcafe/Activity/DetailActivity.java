package com.app.appcafe.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.app.appcafe.Domain.Drinks;
import com.app.appcafe.Helper.ManagmentCart;
import com.app.appcafe.R;
import com.app.appcafe.databinding.ActivityDetailBinding;
import com.bumptech.glide.Glide;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    private Drinks object;
    private int num =1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        managmentCart = new ManagmentCart(this);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Glide.with(DetailActivity.this)
                .load(object.getImagePath())
                .into(binding.imgDetail);
        binding.priceTxt.setText(object.getPrice()+""+"Đ");
        binding.titleTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        binding.rateTxt.setText(object.getStar() + " Rating");
        binding.totalTxt.setText((num * object.getPrice() + ""+"Đ"));
        binding.ratingBar.setRating((float) object.getStar());
        binding.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num+1;
                binding.numTxt.setText(num + "");
                binding.totalTxt.setText((""+num * object.getPrice()) +""+"Đ");
            }
        });
        binding.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(num>1){
                   num = num-1;
                   binding.numTxt.setText(num + "");
                   binding.totalTxt.setText((""+num * object.getPrice()) +""+"Đ");
               }
            }
        });
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(num);
                managmentCart.insertDrinks(object);
            }
        });
    }

    private void getIntentExtra() {
        object = (Drinks) getIntent().getSerializableExtra("object");
    }
}
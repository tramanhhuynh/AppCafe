package com.app.appcafe.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.app.appcafe.R;

public class InforActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);

        ImageView backBtn = findViewById(R.id.backBtn);
        ImageView bntNextProfile = findViewById(R.id.bntNextProfile);

        if (backBtn != null) {
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            Log.e("InforActivity", "backBtn not found");
        }

        if (bntNextProfile != null) {
            bntNextProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(InforActivity.this, InforEditActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            Log.e("InforActivity", "bntNextProfile not found");
        }
    }
}
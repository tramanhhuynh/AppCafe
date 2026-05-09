package com.app.appcafe.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.app.appcafe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class BaseActivity extends AppCompatActivity {
    FirebaseAuth Auth;
    FirebaseDatabase database;
    public String TAG ="lyshctah";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=FirebaseDatabase.getInstance();
        Auth = FirebaseAuth.getInstance();

        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
    }
}


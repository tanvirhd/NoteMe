package com.rokomari.noteme.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.rokomari.noteme.databinding.ActivitySplashScreenBinding;

public class ActivitySplashScreen extends AppCompatActivity {
    private static final String TAG = "ActivitySplashScreen";
    private ActivitySplashScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ActivitySplashScreen.this, ActivityHomeScreen.class));
                finish();
            }
        }, 2000);
    }
}
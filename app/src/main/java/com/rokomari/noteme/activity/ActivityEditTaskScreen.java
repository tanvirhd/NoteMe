package com.rokomari.noteme.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rokomari.noteme.databinding.ActivityEditTaskScreenBinding;

public class ActivityEditTaskScreen extends AppCompatActivity {
    private static final String TAG = "ActivityEditTaskScreen";
    private ActivityEditTaskScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditTaskScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
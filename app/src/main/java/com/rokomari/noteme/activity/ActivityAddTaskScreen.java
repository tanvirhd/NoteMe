package com.rokomari.noteme.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rokomari.noteme.databinding.ActivityAddTaskScreenBinding;

public class ActivityAddTaskScreen extends AppCompatActivity {
    private static final String TAG = "ActivityAddTaskScreen";
    private ActivityAddTaskScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddTaskScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
package com.rokomari.noteme.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.rokomari.noteme.databinding.ActivityTaskDetailsScreenBinding;
import com.rokomari.noteme.model.ModelTask;

import java.util.HashMap;
import java.util.Map;

public class ActivityTaskDetailsScreen extends AppCompatActivity {
    private static final String TAG = "ActivityTaskDetailsScreen";
    private ActivityTaskDetailsScreenBinding binding;
    private ModelTask taskDetails;
    private Map<Integer,String> taskProgressMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTaskDetailsScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    void init(){
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("");

        taskProgressMap=new HashMap<Integer,String>();
        taskProgressMap.put(1,"Open");
        taskProgressMap.put(2,"In-Progress");
        taskProgressMap.put(3,"Test");
        taskProgressMap.put(4,"Done");

        taskDetails=getIntent().getParcelableExtra("parcel");
        if(taskDetails != null) updateUI(taskDetails);
    }

    void updateUI(ModelTask task){
        binding.tvTaskCreatedDate.setText(task.getCreatedDate());
        binding.tvTaskDeadline.setText(task.getDeadline());

        binding.tvTaskTitle.setText(task.getTaskName());
        binding.tvTaskDes.setText(task.getTaskDes());
        binding.tvTaskStatus.setText(taskProgressMap.get(task.getTaskStatus()));
        Log.d(TAG, "updateUI: "+taskProgressMap.get(task.getTaskStatus()));
    }
}
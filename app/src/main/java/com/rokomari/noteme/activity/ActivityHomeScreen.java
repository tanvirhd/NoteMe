package com.rokomari.noteme.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.rokomari.noteme.adpater.AdapterTaskList;
import com.rokomari.noteme.callback.AdapterTaskListCallbacks;
import com.rokomari.noteme.databinding.ActivityHomeScreenBinding;
import com.rokomari.noteme.model.ModelTask;

import java.util.ArrayList;
import java.util.List;

public class ActivityHomeScreen extends AppCompatActivity implements AdapterTaskListCallbacks {
    private static final String TAG = "ActivityHomeScreen";
    private ActivityHomeScreenBinding binding;

    private List<ModelTask> taskList;
    private AdapterTaskList adapterTaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    void init(){
        taskList=new ArrayList<>();
        createDummyData();
        adapterTaskList=new AdapterTaskList(ActivityHomeScreen.this,taskList,ActivityHomeScreen.this);

        binding.recycTask.setAdapter(adapterTaskList);
        binding.recycTask.setLayoutManager(new LinearLayoutManager(ActivityHomeScreen.this));

    }

    void createDummyData(){
        taskList.add(new ModelTask("task 1","task 1 des","02-11-2021","02-11-2021",1));
        taskList.add(new ModelTask("task 2","task 2 des","02-11-2021","02-11-2021",2));
        taskList.add(new ModelTask("task 3","task 3 des","02-11-2021","02-11-2021",3));
        taskList.add(new ModelTask("task 4","task 4 des","02-11-2021","02-11-2021",4));
    }

    @Override
    public void onEditClicked(ModelTask task, int position) {
        Log.d(TAG, "onEditClicked: clicked");
    }

    @Override
    public void onDeleteClicked(ModelTask task, int position) {
        Log.d(TAG, "onDeleteClicked: clicked");
    }

    @Override
    public void onTaskClicked(ModelTask task) {
        Intent taskDetailsIntent=new Intent(ActivityHomeScreen.this,ActivityTaskDetailsScreen.class);
        taskDetailsIntent.putExtra("parcel",task);
        startActivity(taskDetailsIntent);
    }
}
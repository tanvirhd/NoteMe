package com.rokomari.noteme.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.rokomari.noteme.R;
import com.rokomari.noteme.adpater.AdapterTaskList;
import com.rokomari.noteme.callback.AdapterTaskListCallbacks;
import com.rokomari.noteme.databinding.ActivityHomeScreenBinding;
import com.rokomari.noteme.model.ModelTask;
import com.rokomari.noteme.viewmodel.ViewModelTask;

import java.util.ArrayList;
import java.util.List;

public class ActivityHomeScreen extends AppCompatActivity implements AdapterTaskListCallbacks {
    private static final String TAG = "ActivityHomeScreen";
    private ActivityHomeScreenBinding binding;

    private List<ModelTask> taskList;
    private List<ModelTask> taskListDisplay;
    private AdapterTaskList adapterTaskList;
    private int selectedTaskStatus;

    private ViewModelTask viewModelTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();

        binding.btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityHomeScreen.this,ActivityAddTaskScreen.class));
            }
        });

        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuOpen:
                        selectedTaskStatus=1;
                        taskListDisplay.clear();
                        taskListDisplay.addAll(filterList(selectedTaskStatus,taskList));
                        Log.d(TAG, "onNavigationItemSelected: size:"+taskListDisplay.size());
                        adapterTaskList.notifyDataSetChanged();
                        break;
                    case R.id.menuInProgress:
                        selectedTaskStatus=2;
                        taskListDisplay.clear();
                        taskListDisplay.addAll(filterList(selectedTaskStatus,taskList));
                        Log.d(TAG, "onNavigationItemSelected: size:"+taskListDisplay.size());
                        adapterTaskList.notifyDataSetChanged();
                        break;
                    case R.id.menuTest:
                        selectedTaskStatus=3;
                        taskListDisplay.clear();
                        taskListDisplay.addAll(filterList(selectedTaskStatus,taskList));
                        Log.d(TAG, "onNavigationItemSelected: size:"+taskListDisplay.size());
                        adapterTaskList.notifyDataSetChanged();
                        break;
                    case R.id.menuDone:
                        selectedTaskStatus=4;
                        taskListDisplay.clear();
                        taskListDisplay.addAll(filterList(selectedTaskStatus,taskList));
                        Log.d(TAG, "onNavigationItemSelected: size:"+taskListDisplay.size());
                        adapterTaskList.notifyDataSetChanged();
                        break;
                }
                return true;
            }
        });
    }

    void init(){
        binding.bottomNavigation.setItemIconTintList(null);
        binding.bottomNavigation.setSelectedItemId(R.id.menuOpen);
        selectedTaskStatus=1;

        taskList=new ArrayList<>();
        taskListDisplay =new ArrayList<>();
        viewModelTask=new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ViewModelTask.class);
        adapterTaskList=new AdapterTaskList(ActivityHomeScreen.this, taskListDisplay,ActivityHomeScreen.this);

        binding.recycTask.setAdapter(adapterTaskList);
        binding.recycTask.setLayoutManager(new LinearLayoutManager(ActivityHomeScreen.this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModelTask.getAllTasks().observe(this, new Observer<List<ModelTask>>() {
            @Override
            public void onChanged(List<ModelTask> modelTasks) {
                taskList.clear();
                taskListDisplay.clear();
                taskList.addAll(modelTasks);
                taskListDisplay.addAll(filterList(selectedTaskStatus,taskList));
                adapterTaskList.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onEditClicked(ModelTask task, int position) {
        Intent taskDetailsIntent=new Intent(ActivityHomeScreen.this,ActivityEditTaskScreen.class);
        taskDetailsIntent.putExtra("parcel",task);
        startActivity(taskDetailsIntent);
    }

    @Override
    public void onDeleteClicked(ModelTask task, int position) {
        viewModelTask.deleteTaskByTaskId(task.id);
        viewModelTask.getAllTasks();
    }

    @Override
    public void onTaskClicked(ModelTask task) {
        Intent taskDetailsIntent=new Intent(ActivityHomeScreen.this,ActivityTaskDetailsScreen.class);
        taskDetailsIntent.putExtra("parcel",task);
        startActivity(taskDetailsIntent);
    }

    List<ModelTask> filterList(int taskStatus,List<ModelTask> tasks){
        List<ModelTask> tempTaskList=new ArrayList<>();
        for(ModelTask task:tasks){
            if(task.getTaskStatus()==taskStatus) tempTaskList.add(task);
        }
        return tempTaskList;
    }
}
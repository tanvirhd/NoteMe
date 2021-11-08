package com.rokomari.noteme.activity;

import static com.rokomari.noteme.tools.Utils.openDialler;
import static com.rokomari.noteme.tools.Utils.openEmailApp;
import static com.rokomari.noteme.tools.Utils.openURLInBrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rokomari.noteme.databinding.ActivityTaskDetailsScreenBinding;
import com.rokomari.noteme.model.ModelTask;
import com.rokomari.noteme.tools.Utils;
import com.rokomari.noteme.viewmodel.ViewModelTask;

import java.util.HashMap;
import java.util.Map;

public class ActivityTaskDetailsScreen extends AppCompatActivity {
    private static final String TAG = "ActivityTaskDetailsScreen";
    private ActivityTaskDetailsScreenBinding binding;
    private ModelTask taskDetails;
    private Map<Integer,String> taskProgressMap;

    private ViewModelTask viewModelTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTaskDetailsScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        binding.btnEditTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent taskDetailsIntent=new Intent(ActivityTaskDetailsScreen.this,ActivityEditTaskScreen.class);
                taskDetailsIntent.putExtra("parcel",taskDetails);
                startActivity(taskDetailsIntent);
            }
        });

        binding.containerEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(taskDetails.getTaskAttachmentEmail().isEmpty()) showToast("No Email Attached!");
                else openEmailApp(taskDetails.getTaskAttachmentEmail(),ActivityTaskDetailsScreen.this,ActivityTaskDetailsScreen.this);
            }
        });

        binding.containerPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(taskDetails.getTaskAttachmentPhone().isEmpty()) showToast("No Phone Number Attached!");
                else openDialler(taskDetails.getTaskAttachmentPhone(),ActivityTaskDetailsScreen.this);
            }
        });

        binding.containerUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(taskDetails.getTaskAttachmentUrl().isEmpty()) showToast("No URL Attached!");
                else openURLInBrowser(taskDetails.getTaskAttachmentUrl(),ActivityTaskDetailsScreen.this);
            }
        });

        binding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModelTask.deleteTaskByTaskId(taskDetails.id);
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTaskById(taskDetails.id);
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

        viewModelTask=new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ViewModelTask.class);
    }

    void updateUI(ModelTask task){
        binding.tvTaskCreatedDate.setText(task.getCreatedDate());
        binding.tvTaskDeadline.setText(task.getDeadline());

        binding.tvTaskTitle.setText(task.getTaskName());
        binding.tvTaskDes.setText(task.getTaskDes());
        binding.tvTaskStatus.setText(taskProgressMap.get(task.getTaskStatus()));
    }

    void getTaskById(int id){
        viewModelTask.getTaskByTaskId(id).observe(this, new Observer<ModelTask>() {
            @Override
            public void onChanged(ModelTask task) {
                if(task != null) {
                    taskDetails=task;
                    updateUI(task);
                }
            }
        });
    }

    void  showToast(String message ){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
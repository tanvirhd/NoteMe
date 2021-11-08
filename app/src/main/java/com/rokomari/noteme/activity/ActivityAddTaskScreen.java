package com.rokomari.noteme.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rokomari.noteme.R;
import com.rokomari.noteme.databinding.ActivityAddTaskScreenBinding;
import com.rokomari.noteme.model.ModelTask;
import com.rokomari.noteme.viewmodel.ViewModelTask;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActivityAddTaskScreen extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "ActivityAddTaskScreen";
    private ActivityAddTaskScreenBinding binding;

    private ArrayAdapter<String> spinnerAdapter;
    private Dialog dialogSavePhone;
    private Dialog dialogSaveURL;
    private Dialog dialogSaveEmail;
    private Dialog dialogTaskSaved;

    private String taskName="",taskDes="",taskDeadLine,taskCreateDate,taskEmail="",taskPhone="",taskURL="";
    private int taskStatus;
    private ViewModelTask viewModelTask;

    Calendar today;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddTaskScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        binding.containerEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSaveEmail.show();
            }
        });

        binding.containerPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSavePhone.show();
            }
        });

        binding.containerUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSaveURL.show();
            }
        });

        binding.ivCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show(getSupportFragmentManager(), "DatePickerDialog");
            }
        });

        binding.spinnerTaskStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //1=open 2=in-progress 3=test 4=done
                taskStatus=i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertNewTask();
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        taskDeadLine=dayOfMonth+"."+monthOfYear+"."+year;
        binding.tvDeadline.setText(taskDeadLine);
    }

    void init(){
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("");

        today =Calendar.getInstance();
        taskDeadLine="00.00.0000";
        taskStatus=1;
        taskCreateDate=today.get(Calendar.DAY_OF_MONTH)+"."+today.get(Calendar.MONTH)+"."+today.get(Calendar.YEAR);
        viewModelTask=new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ViewModelTask.class);

        initTaskStatusSpinner();
        initAllDialog();

    }

    void initTaskStatusSpinner(){
        spinnerAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.status_list)
        );
        binding.spinnerTaskStatus.setAdapter(spinnerAdapter);
    }

    void initAllDialog(){
         dialogSavePhone= createDialog(R.drawable.icon_phone,"Enter Phone","Save Phone","phone");
         dialogSaveEmail= createDialog(R.drawable.icon_email,"Enter Email","Save Email","email");
         dialogSaveURL= createDialog(R.drawable.icon_url,"Enter URL (http://example.com)","Save URL","url");
         dialogTaskSaved= createTaskSavedDialog();

         binding.tvDeadline.setText(taskDeadLine);
         datePickerDialog= DatePickerDialog.newInstance(
                 ActivityAddTaskScreen.this,
                 today.get(Calendar.YEAR),
                 today.get(Calendar.MONTH),
                 today.get(Calendar.DAY_OF_MONTH)
         );
         datePickerDialog.setMinDate(today);
     }

    Dialog createDialog(int icon, String hintText, String buttonText, String tag){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView topIcon=dialog.findViewById(R.id.ivDialogTopIcon);
        topIcon.setImageResource(icon);
        EditText inputBoxDialog=dialog.findViewById(R.id.etDialogInputField);
        inputBoxDialog.setHint(hintText);
        TextView submitButtonDialog=dialog.findViewById(R.id.dialogSubmit);
        submitButtonDialog.setText(buttonText);

        submitButtonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (tag){
                    case "phone":
                        if(inputBoxDialog.getText().toString().length()==11){
                            taskPhone=inputBoxDialog.getText().toString();
                            Log.d(TAG, "onClick: "+inputBoxDialog.getText().toString());
                        }else {
                            showToast("Invalid Phone Number");
                        }
                        break;
                    case "email":
                        if(inputBoxDialog.getText().toString().contains("@")){
                            taskEmail=inputBoxDialog.getText().toString();
                            Log.d(TAG, "onClick: "+inputBoxDialog.getText().toString());
                        }else {
                            showToast("Invalid Email Address");
                        }
                        break;
                    case "url":
                        if(URLUtil.isValidUrl(inputBoxDialog.getText().toString())){
                            taskURL=inputBoxDialog.getText().toString();
                            Log.d(TAG, "onClick: "+inputBoxDialog.getText().toString());
                        }else {
                            showToast("Invalid  URL");
                        }
                        break;
                }
                dialog.dismiss();
            }
        });

        return dialog;
    }

    Dialog createTaskSavedDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog_saved);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView submitButtonDialog=dialog.findViewById(R.id.dialogSubmit);

        submitButtonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onBackPressed();
            }
        });

        return dialog;
    }

    void  showToast(String message){
        Toast.makeText(ActivityAddTaskScreen.this, message, Toast.LENGTH_SHORT).show();
    }

    void insertNewTask(){
        taskName=binding.etTaskName.getText().toString();
        taskDes=binding.etTaskDes.getText().toString();

        if(taskName.isEmpty() || taskDes.isEmpty() || taskDeadLine.equals("00.00.0000") || taskStatus==0){
            showToast("Empty Field Found!!");
        }else{
            viewModelTask.insertNewTask(
                    new ModelTask(taskName, taskDes,
                            taskCreateDate, taskDeadLine,
                            taskStatus, taskEmail, taskPhone, taskURL));
            dialogTaskSaved.show();
        }
    }
}
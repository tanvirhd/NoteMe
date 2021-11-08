package com.rokomari.noteme.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rokomari.noteme.model.ModelTask;
import com.rokomari.noteme.repository.RepositoryTask;

import java.util.List;

public class ViewModelTask extends AndroidViewModel {

    private RepositoryTask repository;
    public ViewModelTask(@NonNull Application application) {
        super(application);
        repository=new RepositoryTask(application);
    }

    public void insertNewTask(ModelTask task) {
       repository.insertNewTask(task);
    }

    public void deleteTaskByTaskId(int taskId){
        repository.deleteTaskByTaskId(taskId);
    }

    public LiveData<List<ModelTask>> getAllTasks() {
        return  repository.getAllTasks();
    }

    public LiveData<List<ModelTask>> getTaskByStatus(int status){
        return repository.getTaskByStatus(status);
    }

    public LiveData<ModelTask> getTaskByTaskId(int taskId){
        return repository.getTaskByTaskId(taskId);
    }

    public void updateTask(ModelTask task){
        repository.updateTask(task);
    }
}

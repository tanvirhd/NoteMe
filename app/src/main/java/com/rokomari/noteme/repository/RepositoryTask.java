package com.rokomari.noteme.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rokomari.noteme.model.ModelTask;
import com.rokomari.noteme.room.TaskDao;
import com.rokomari.noteme.room.TaskDatabase;

import java.util.List;

public class RepositoryTask {
   private TaskDao taskDao;

    public RepositoryTask(Application application) {
        TaskDatabase db = TaskDatabase.getDb(application);
        taskDao = db.getTaskDao();
    }

    public void insertNewTask(ModelTask task) {
        TaskDatabase.databaseWriteExecutor.execute(()->taskDao.insertNewTask(task));
    }

    public void deleteTaskByTaskId(int taskId){
        TaskDatabase.databaseWriteExecutor.execute(()->taskDao.deleteTaskByID(taskId));
    }

    public LiveData<List<ModelTask>> getAllTasks() {
        return  taskDao.getAllTask();
    }

    public LiveData<List<ModelTask>> getTaskByStatus(int status){
        return taskDao.getAllTaskByStatus(status);
    }

    public LiveData<ModelTask> getTaskByTaskId(int taskId){
        return taskDao.getTaskByTaskId(taskId);
    }

    public void updateTask(ModelTask task){
        TaskDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.updateTask(
                        task.id,
                        task.getTaskName(),
                        task.getTaskDes(),
                        task.getCreatedDate(),
                        task.getDeadline(),
                        task.getTaskStatus(),
                        task.getTaskAttachmentEmail(),
                        task.getTaskAttachmentPhone(),
                        task.getTaskAttachmentUrl()
                );
            }
        });
    }


}

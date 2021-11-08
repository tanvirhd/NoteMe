package com.rokomari.noteme.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.rokomari.noteme.model.ModelTask;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertNewTask(ModelTask task);

    @Query("Select * from task_table where id=(:taskId)")
    LiveData<ModelTask> getTaskByTaskId(int taskId);

    @Query("SELECT * FROM task_table ")
    LiveData<List<ModelTask>> getAllTask();

    @Query("select * from task_table where taskStatus=(:status)")
    LiveData<List<ModelTask>> getAllTaskByStatus(int status);

    @Query("delete from task_table where id=:taskId")
    void deleteTaskByID(int taskId);

    @Query("update task_table set taskName=:taskName, taskDes=:taskDes, createdDate=:createdDate, deadline=:deadline, " +
            "taskStatus=:taskStatus, taskAttachmentEmail=:taskAttachmentEmail, taskAttachmentPhone=:taskAttachmentPhone, " +
            "taskAttachmentUrl=:taskAttachmentUrl where id=:id")
    void updateTask(int id,String taskName, String taskDes,
                    String createdDate, String deadline,
                    int taskStatus, String taskAttachmentEmail,
                    String taskAttachmentPhone, String taskAttachmentUrl);
}

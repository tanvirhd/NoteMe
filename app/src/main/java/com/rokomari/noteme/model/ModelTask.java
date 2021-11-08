package com.rokomari.noteme.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* status option
   1.open
   2.in-progress
   3.test
   4.done */

@Entity (tableName = "task_table")
public class ModelTask implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public  int id;

    @ColumnInfo (name = "taskName")
    private String taskName;

    @ColumnInfo (name = "taskDes")
    private String taskDes;

    @ColumnInfo (name = "createdDate")
    private String createdDate;

    @ColumnInfo (name = "deadline")
    private String deadline;

    @ColumnInfo (name = "taskStatus")
    private int taskStatus;

    @ColumnInfo (name = "taskAttachmentEmail")
    private String taskAttachmentEmail;

    @ColumnInfo (name = "taskAttachmentPhone")
    private String taskAttachmentPhone;

    @ColumnInfo (name = "taskAttachmentUrl")
    private String taskAttachmentUrl;

    public ModelTask(String taskName, String taskDes, String createdDate, String deadline, int taskStatus, String taskAttachmentEmail, String taskAttachmentPhone, String taskAttachmentUrl) {
        this.taskName = taskName;
        this.taskDes = taskDes;
        this.createdDate = createdDate;
        this.deadline = deadline;
        this.taskStatus = taskStatus;
        this.taskAttachmentEmail = taskAttachmentEmail;
        this.taskAttachmentPhone = taskAttachmentPhone;
        this.taskAttachmentUrl = taskAttachmentUrl;
    }

    public ModelTask(String taskName, String taskDes, String createdDate, String deadline, int taskStatus) {
        this.taskName = taskName;
        this.taskDes = taskDes;
        this.createdDate = createdDate;
        this.deadline = deadline;
        this.taskStatus = taskStatus;

        this.taskAttachmentEmail="";
        this.taskAttachmentPhone="";
        this.taskAttachmentUrl="";
    }

    public ModelTask(int id, String taskName, String taskDes, String createdDate, String deadline, int taskStatus, String taskAttachmentEmail, String taskAttachmentPhone, String taskAttachmentUrl) {
        this.id = id;
        this.taskName = taskName;
        this.taskDes = taskDes;
        this.createdDate = createdDate;
        this.deadline = deadline;
        this.taskStatus = taskStatus;
        this.taskAttachmentEmail = taskAttachmentEmail;
        this.taskAttachmentPhone = taskAttachmentPhone;
        this.taskAttachmentUrl = taskAttachmentUrl;
    }

    public ModelTask() {
    }

    protected ModelTask(Parcel in) {
        id = in.readInt();
        taskName = in.readString();
        taskDes = in.readString();
        createdDate = in.readString();
        deadline = in.readString();
        taskStatus = in.readInt();
        taskAttachmentEmail = in.readString();
        taskAttachmentPhone = in.readString();
        taskAttachmentUrl = in.readString();
    }

    public static final Creator<ModelTask> CREATOR = new Creator<ModelTask>() {
        @Override
        public ModelTask createFromParcel(Parcel in) {
            return new ModelTask(in);
        }

        @Override
        public ModelTask[] newArray(int size) {
            return new ModelTask[size];
        }
    };

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDes() {
        return taskDes;
    }

    public void setTaskDes(String taskDes) {
        this.taskDes = taskDes;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskAttachmentEmail() {
        return taskAttachmentEmail;
    }

    public void setTaskAttachmentEmail(String taskAttachmentEmail) {
        this.taskAttachmentEmail = taskAttachmentEmail;
    }

    public String getTaskAttachmentPhone() {
        return taskAttachmentPhone;
    }

    public void setTaskAttachmentPhone(String taskAttachmentPhone) {
        this.taskAttachmentPhone = taskAttachmentPhone;
    }

    public String getTaskAttachmentUrl() {
        return taskAttachmentUrl;
    }

    public void setTaskAttachmentUrl(String taskAttachmentUrl) {
        this.taskAttachmentUrl = taskAttachmentUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(taskName);
        parcel.writeString(taskDes);
        parcel.writeString(createdDate);
        parcel.writeString(deadline);
        parcel.writeInt(taskStatus);
        parcel.writeString(taskAttachmentEmail);
        parcel.writeString(taskAttachmentPhone);
        parcel.writeString(taskAttachmentUrl);
    }
}

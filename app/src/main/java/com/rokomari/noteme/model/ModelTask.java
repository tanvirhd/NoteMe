package com.rokomari.noteme.model;

import android.os.Parcel;
import android.os.Parcelable;

/* status option
   1.open
   2.in-progress
   3.test
   4.done */
public class ModelTask implements Parcelable {
    private String taskName;
    private String taskDes;
    private String createdDate;
    private String deadline;
    private int taskStatus;
    private String taskAttachmentEmail;
    private String taskAttachmentPhone;
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

    public ModelTask() {
    }

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

    protected ModelTask(Parcel in) {
        taskName = in.readString();
        taskDes = in.readString();
        createdDate = in.readString();
        deadline = in.readString();
        taskStatus = in.readInt();
        taskAttachmentEmail = in.readString();
        taskAttachmentPhone = in.readString();
        taskAttachmentUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(taskName);
        dest.writeString(taskDes);
        dest.writeString(createdDate);
        dest.writeString(deadline);
        dest.writeInt(taskStatus);
        dest.writeString(taskAttachmentEmail);
        dest.writeString(taskAttachmentPhone);
        dest.writeString(taskAttachmentUrl);
    }

    @Override
    public int describeContents() {
        return 0;
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
}

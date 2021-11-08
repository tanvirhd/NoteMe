package com.rokomari.noteme.adpater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rokomari.noteme.callback.AdapterTaskListCallbacks;
import com.rokomari.noteme.databinding.LayoutTaskBinding;
import com.rokomari.noteme.model.ModelTask;

import java.util.List;

public class AdapterTaskList extends RecyclerView.Adapter<AdapterTaskList.AdapterTaskListViewHolder>{

    Context context;
    List<ModelTask> taskList;
    AdapterTaskListCallbacks callbacks;

    public AdapterTaskList(Context context, List<ModelTask> taskList, AdapterTaskListCallbacks callbacks) {
        this.context = context;
        this.taskList = taskList;
        this.callbacks = callbacks;
    }

    @NonNull
    @Override
    public AdapterTaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutTaskBinding layoutTaskBinding=LayoutTaskBinding.inflate(LayoutInflater.from(context),parent,false);
        return new AdapterTaskListViewHolder(layoutTaskBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTaskListViewHolder holder, int position) {
        ModelTask task=taskList.get(position);
        int index=position;

        holder.layoutTaskBinding.tvTaskName.setText(task.getTaskName());
        holder.layoutTaskBinding.tvCreatedDate.setText(task.getCreatedDate());
        holder.layoutTaskBinding.tvDeadline.setText(task.getDeadline());

        holder.layoutTaskBinding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbacks.onEditClicked(task,index);
            }
        });

        holder.layoutTaskBinding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbacks.onDeleteClicked(task,index);
            }
        });

        holder.layoutTaskBinding.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbacks.onTaskClicked(task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class  AdapterTaskListViewHolder extends RecyclerView.ViewHolder{
        private LayoutTaskBinding layoutTaskBinding;
        public AdapterTaskListViewHolder(@NonNull LayoutTaskBinding layoutTaskBinding) {
            super(layoutTaskBinding.getRoot());
            this.layoutTaskBinding=layoutTaskBinding;
        }
    }
}

package com.rokomari.noteme.callback;

import com.rokomari.noteme.model.ModelTask;

public interface AdapterTaskListCallbacks {
    void onEditClicked(ModelTask task,int position);
    void onDeleteClicked(ModelTask task,int position);
    void onTaskClicked(ModelTask task);
}

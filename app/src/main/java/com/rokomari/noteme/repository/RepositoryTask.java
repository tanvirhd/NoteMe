package com.rokomari.noteme.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    public LiveData<List<ModelTask>> getAllTasks() {
        return  taskDao.getAllTask();
    }

    public LiveData<List<ModelTask>> getTaskByStatus(int status){
        return taskDao.getAllTaskByStatus(status);
    }

    public LiveData<ModelTask> getTaskByTaskId(int taskId){
        return taskDao.getNoteByTaskId(taskId);
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

        TaskDatabase.databaseWriteExecutor.isShutdown();

    }


}

















    /*private TaskDao taskDao;
    private LiveData<List<ModelTask>> allTask;

    public RepositoryTask(Application application) {
        TaskDatabase database = TaskDatabase.getDb(application);
        taskDao = database.getTaskDao();
        allTask = taskDao.getAllTask();
    }

    public void insertNewTask(ModelTask task) {
        new InsertTaskAsyncTask(taskDao).execute(task);
    }

    *//*public void update(ModelTask task) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }*//*


    private static class InsertTaskAsyncTask extends AsyncTask<ModelTask, Void, Void> {

        private TaskDao taskDao;
        private InsertTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(ModelTask... tasks) {
            taskDao.insertNewTask(tasks[0]);
            return null;
        }
    }

    private static class UpdateTaskAsyncTask extends AsyncTask<ModelTask, Void, Void> {
        private TaskDao taskDao;
        private UpdateTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(ModelTask... tasks) {
            taskDao.updateTask(
                    tasks[0].id,
                    tasks[0].getTaskName(),
                    tasks[0].getTaskDes(),
                    tasks[0].getCreatedDate(),
                    tasks[0].getDeadline(),
                    tasks[0].getTaskStatus(),
                    tasks[0].getTaskAttachmentEmail(),
                    tasks[0].getTaskAttachmentPhone(),
                    tasks[0].getTaskAttachmentUrl()
            );
            return null;
        }
    }
*/

    /*private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;
        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.Delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;
        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.DeleteAllNotes();
            return null;
        }
    }*/
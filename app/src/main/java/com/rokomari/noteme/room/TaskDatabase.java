package com.rokomari.noteme.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rokomari.noteme.model.ModelTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {ModelTask.class} , version = 1)
public abstract class TaskDatabase  extends RoomDatabase {

    public abstract TaskDao getTaskDao();

    public static TaskDatabase dbInstance;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized TaskDatabase getDb(Context context){
        if(dbInstance==null){
            dbInstance= Room.databaseBuilder(context.getApplicationContext(),TaskDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }
}

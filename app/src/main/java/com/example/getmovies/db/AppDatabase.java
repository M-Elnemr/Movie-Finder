package com.example.getmovies.db;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.getmovies.model.MovieShortDetails;

@Database(entities = {MovieShortDetails.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getINSTANCE(Application application){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){

                    INSTANCE = Room.databaseBuilder(application.getApplicationContext()
                            , AppDatabase.class,
                            "app_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}

package com.helmo.simonsays.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;

import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.helmo.simonsays.database.dao.ScoreDao;
import com.helmo.simonsays.model.Score;

@Database(entities = {Score.class},version=1,exportSchema = false)
@TypeConverters({ScoreTypeConverters.class})
public abstract class ScoreDatabase extends RoomDatabase {
    private static final String DATABASE_NAME ="score_database";
    private static ScoreDatabase instance;

    public abstract ScoreDao scoreDao();

    public static void initDatabase(Context context){
        if(instance==null)
            instance = Room.databaseBuilder(context.getApplicationContext(), ScoreDatabase.class,DATABASE_NAME).build();
    }


    public static ScoreDatabase getInstance(){
        if(instance==null)
            throw new IllegalStateException("Score database must be initialized");
        return instance;
    }
    public static void disconnectDatabase(){
        instance.close();
        instance=null;
    }
}

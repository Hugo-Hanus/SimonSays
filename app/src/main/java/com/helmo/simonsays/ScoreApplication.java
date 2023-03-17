package com.helmo.simonsays;

import android.app.Application;
import android.util.Log;

import com.helmo.simonsays.database.ScoreDatabase;

public class ScoreApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("Init Application","initialisation de l'application + dataBase");
        ScoreDatabase.initDatabase(getBaseContext());
    }
    @Override
    public void onTerminate(){
        super.onTerminate();
        Log.d("Terminate Application","fin de l'application + dataBase");
        ScoreDatabase.disconnectDatabase();
    }
}

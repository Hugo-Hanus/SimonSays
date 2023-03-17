package com.helmo.simonsays.database.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.helmo.simonsays.database.ScoreDatabase;
import com.helmo.simonsays.database.dao.ScoreDao;
import com.helmo.simonsays.model.Score;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScoreRepository {
    public static ScoreRepository instance;
    private final ScoreDao scoreDao= ScoreDatabase.getInstance().scoreDao();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private ScoreRepository(){}

    public LiveData<List<Score>> getClassement(){

        return scoreDao.getClassement();
    }
    public LiveData<Score> getBestScore(){
        return scoreDao.getBestScore();
    }

    public void insertScore(final Score score){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                scoreDao.insert(score);
                Log.d("Repo","sauvegarde du score");
            }
        });
    }

    public static ScoreRepository getInstance(){
        if(instance==null)
            instance=new ScoreRepository();
        return instance;
    }

}

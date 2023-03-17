package com.helmo.simonsays.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.helmo.simonsays.model.Score;

import java.util.List;

@Dao
public interface ScoreDao {
    @Query("Select * FROM Score  ORDER BY point DESC")
    LiveData<List<Score>> getClassement();
    @Query("Select * from Score ORDER BY point DESC LIMIT 1")
    LiveData<Score> getBestScore();
    @Insert
    void insert(Score score);
}

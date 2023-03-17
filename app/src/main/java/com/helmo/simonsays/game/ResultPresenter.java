package com.helmo.simonsays.game;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.helmo.simonsays.database.repository.ScoreRepository;
import com.helmo.simonsays.model.Score;

public class ResultPresenter implements ResultFragmentVP.Presenter{

        private ResultFragmentVP.View view;

        private final Score score=new Score();
    public ResultPresenter(ResultFragmentVP.View view){
        this.view=view;
    }


    public void saveScore(String difficulty,int point,String pseudo){
        score.setDifficulty(difficulty);
        score.setPoint(point);
        score.setPseudo(pseudo);
        ScoreRepository.getInstance().insertScore(score);
    }

}

package com.helmo.simonsays.classement;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.helmo.simonsays.database.repository.ScoreRepository;
import com.helmo.simonsays.model.Score;

import java.util.List;

public class ClassementPresenter implements ClassementFragmentVP.Presenter {

    private List<Score> scores;
    private final ClassementFragmentVP.View view;

    public ClassementPresenter(ClassementFragmentVP.View view){
        this.view=view;
    }


    public void loadScores(){
        ScoreRepository.getInstance().getClassement().observeForever(new Observer<List<Score>>() {
            @Override
            public void onChanged(List<Score> classement) {
                ClassementPresenter.this.scores = classement;
                if(scores.size()==0){
                view.noScoreRegister();
                }
                view.loadView();
            }
        });
    }

    public int getItemCount() {
        if(scores==null){
            return 0;
        }
        return scores.size();
    }

    public void showClassementOn(ClassementFragmentVP.IScoreItemScreen holder, int position) {
        Score score=scores.get(position);
        holder.showClassement(score.getPseudo(),score.getDifficulty(),score.getPoint());

    }
}


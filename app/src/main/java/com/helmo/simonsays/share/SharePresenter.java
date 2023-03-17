package com.helmo.simonsays.share;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.helmo.simonsays.classement.ClassementFragmentVP;
import com.helmo.simonsays.database.repository.ScoreRepository;
import com.helmo.simonsays.model.Score;

public class SharePresenter implements ShareFragmentVP.Presenter{
    private ShareFragmentVP.View view;
    private Score score=new Score();
    public SharePresenter(ShareFragmentVP.View view){this.view=view;}

    @Override
    public void loadBestScore(){
        ScoreRepository.getInstance().getBestScore().observeForever(new Observer<Score>() {
            @Override
            public void onChanged(Score score) {
                SharePresenter.this.score=score;
                if(score!=null){  view.setBestScore(score.getPseudo(),score.getDifficulty(),score.getPoint());}
            }
        });
    }

    @Override
    public void shareScore(CharSequence pseudo, CharSequence difficulty, CharSequence point) {
        String formatted=String.join(" | ",pseudo,difficulty,point);
        if(!formatted.equals(" |  | ")){
            view.sendBestScore(formatted);
        }else{
            view.noBestScoreRegisted();
        }
    }
}

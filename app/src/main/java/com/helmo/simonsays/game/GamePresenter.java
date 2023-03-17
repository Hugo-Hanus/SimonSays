package com.helmo.simonsays.game;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.helmo.simonsays.game.mecanics.Game;
import com.helmo.simonsays.model.Score;

import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Semaphore;

public class GamePresenter implements GameFragmentVP.Presenter {
    private final GameFragmentVP.View view;
    private Game game;
    RespondCountDownTimer respondTimer;
    PatternCountDownTimer patternTimer;


    public GamePresenter(GameFragmentVP.View view) {
        this.view = view;
        respondTimer = new RespondCountDownTimer(3100, 1000);
    }


    @Override
    public void pressMove(String move) {
        game.moveStep();
        game.playerMove(move);
        if (game.getDifficulty().equals("DIFFICILE")) {
            respondTimer.start();
        }
        if (game.verifyMoveNumber(game.getStep())) {
            if (game.sequenceFinish()) {
                view.setTimer("3");
                game.scored();
                view.setScore(game.getScore());
                readPattern();
            }
        } else {
            respondTimer.cancel();
            patternTimer.cancel();
            view.endGame(game.getScore(), game.getDifficulty());
        }


    }


    @Override
    public void startGame(String difficulty) {
        game = new Game(difficulty);
        game.startGame();
        readPattern();
    }

    @Override
    public void stopGame() {
        respondTimer.cancel();
        patternTimer.cancel();
    }

    private void readPattern() {
        long delay = 1000;
        List<String> readPattern = game.getPattern();
        int length = game.getLengthPattern();
        respondTimer.cancel();
        patternTimer = new PatternCountDownTimer((long) ((length+0.5) * delay), delay, readPattern);
        new CountDownTimer(750, 750) {
            @Override
            public void onTick(long l) {
            }
            @Override
            public void onFinish() {
                patternTimer.start();
                view.buttonPlayable(false);
            }
        }.start();
    }


    public class RespondCountDownTimer extends CountDownTimer {
        public RespondCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            view.setTimer(Long.toString(millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            view.endGame(game.getScore(), game.getDifficulty());
        }
    }

    public class PatternCountDownTimer extends CountDownTimer {
        List<String> patternList;
        int i = 0;

        public PatternCountDownTimer(long millisInFuture, long countDownInterval, List<String> patternList) {
            super(millisInFuture, countDownInterval);
            this.patternList = patternList;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (i < patternList.size()) {
                view.playButton(patternList.get(i));
                i++;
            }
        }

        @Override
        public void onFinish() {
            view.buttonPlayable(true);
            if (game.getDifficulty().equals("DIFFICILE")) {
                respondTimer.start();
            }
        }
    }


}

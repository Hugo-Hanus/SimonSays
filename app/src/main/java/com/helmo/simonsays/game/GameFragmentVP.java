package com.helmo.simonsays.game;

public interface GameFragmentVP {

    interface View{
        void setTimer(String timer);
        void playButton(String next);
        void endGame(int score, String difficulty);
        void setScore(int score);

        void buttonPlayable(boolean action);
    }

    interface Presenter{
        void pressMove(String move);
        void startGame(String difficulty);

        void stopGame();
    }
}

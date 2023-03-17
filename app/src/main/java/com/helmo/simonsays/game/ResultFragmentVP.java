package com.helmo.simonsays.game;

public interface ResultFragmentVP {

    interface View{

    }
    interface Presenter{
        void saveScore(String difficulty,int point,String pseudo);
    }
}

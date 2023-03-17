package com.helmo.simonsays.classement;

public interface ClassementFragmentVP {


    interface View{
        void loadView();

        void noScoreRegister();
    }
    interface Presenter{
    }
    interface IScoreItemScreen{
        void showClassement(String pseudo,String difficulty,int point);
    }
}

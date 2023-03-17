package com.helmo.simonsays.share;

public interface ShareFragmentVP {

    interface Presenter{
        void loadBestScore();

        void shareScore(CharSequence pseudo, CharSequence difficulty, CharSequence point);
    }
    interface View{
        void setBestScore(String pseudo, String difficulty, int point);

        void sendBestScore(String formatedString);

        void noBestScoreRegisted();
    }
}

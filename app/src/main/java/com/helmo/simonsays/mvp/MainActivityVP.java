package com.helmo.simonsays.mvp;

public interface MainActivityVP {
    interface View{
        void setFragment(BaseFragment fragment);
    }
    interface Presenter{
        void launch();
    }
}

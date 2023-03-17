package com.helmo.simonsays.mvp;

public interface FragmentNavigation {


    interface View{
        void attachPresenter(Presenter Presenter);
    }

    interface Presenter{
        void addFragment(BaseFragment fragment);
    }

}

package com.helmo.simonsays.presenter;

import com.helmo.simonsays.home.HomeFragment;
import com.helmo.simonsays.mvp.BaseFragment;
import com.helmo.simonsays.mvp.FragmentNavigation;
import com.helmo.simonsays.mvp.MainActivityVP;

public class MainActivityPresenter implements MainActivityVP.Presenter, FragmentNavigation.Presenter {

    private MainActivityVP.View  view;
    public MainActivityPresenter(MainActivityVP.View view){
        this.view=view;
    }
    @Override
    public void addFragment(BaseFragment fragment) {
    view.setFragment(fragment);
    }

    @Override
    public void launch() {
    view.setFragment(new HomeFragment());
    }
}

package com.helmo.simonsays.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment implements FragmentNavigation.View {
    protected View rootView;
    protected FragmentNavigation.Presenter navigationPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        rootView = inflater.inflate(getLayout(), container, false);
        return rootView;
    }

    protected abstract int getLayout();

    @Override
    public void attachPresenter(FragmentNavigation.Presenter presenter) {
        navigationPresenter=presenter;
    }
}

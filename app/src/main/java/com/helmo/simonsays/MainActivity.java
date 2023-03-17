package com.helmo.simonsays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.helmo.simonsays.mvp.BaseFragment;
import com.helmo.simonsays.mvp.MainActivityVP;
import com.helmo.simonsays.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityVP.View {

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusAndActionBar();
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
        presenter.launch();
    }


    private void hideStatusAndActionBar() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //-- Soit disant DEPRECATED mais fonctionne en API 30
        // Mais le code fournit par google ne fonctionne pas ni en 24 ni en 30 https://developer.android.com/training/system-ui/status#41
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void setFragment(BaseFragment fragment) {
        fragment.attachPresenter(presenter);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }
}
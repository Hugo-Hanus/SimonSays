package com.helmo.simonsays.game;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.helmo.simonsays.R;
import com.helmo.simonsays.classement.ClassementFragment;
import com.helmo.simonsays.mvp.BaseFragment;

public class DifficultyFragment extends BaseFragment {
    public static final String TAG ="DifficultyFragment";
    private Button normalButton;
    private Button hardButton;

    @Override
    public void onViewCreated(View view,@Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        Log.d(TAG,"onViewCreated");
        normalButton = view.findViewById(R.id.normalButton);
        hardButton= view.findViewById(R.id.hardButton);

        normalButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                navigationPresenter.addFragment(new GameFragment("NORMAL"));
            }
        });
        hardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                navigationPresenter.addFragment(new GameFragment("DIFFICILE"));
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.difficulty_choice_view_fragment;
    }
}

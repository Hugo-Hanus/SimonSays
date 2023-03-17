package com.helmo.simonsays.home;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.helmo.simonsays.R;
import com.helmo.simonsays.classement.ClassementFragment;
import com.helmo.simonsays.game.DifficultyFragment;
import com.helmo.simonsays.game.GameFragment;
import com.helmo.simonsays.mvp.BaseFragment;
import com.helmo.simonsays.share.ShareFragment;


public class HomeFragment extends BaseFragment {
    public static final String TAG="HomeFragment";
    private ImageButton playGameButton;
    private ImageButton viewTopButton;
    private ImageButton shareTopButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navigationPresenter.addFragment(new HomeFragment());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }
    @Override
    public void onViewCreated(View view,@Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        Log.d(TAG,"onViewCreated");
        playGameButton = view.findViewById(R.id.playButton);
        viewTopButton=view.findViewById(R.id.scoreButton);
        shareTopButton=view.findViewById(R.id.shareButton);

        playGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationPresenter.addFragment(new DifficultyFragment());
            }
        });
        viewTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationPresenter.addFragment(new ClassementFragment());
            }
        });
        shareTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationPresenter.addFragment(new ShareFragment());
            }
        });

    }


    @Override
    protected int getLayout() {
        return R.layout.menu_view_fragment;
    }
}

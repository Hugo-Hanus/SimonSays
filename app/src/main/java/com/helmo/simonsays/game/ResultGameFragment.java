package com.helmo.simonsays.game;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;

import com.helmo.simonsays.R;
import com.helmo.simonsays.home.HomeFragment;
import com.helmo.simonsays.mvp.BaseFragment;

public class ResultGameFragment extends BaseFragment implements ResultFragmentVP.View {
        private static final String TAG ="ResultGameFragment";
        private ResultFragmentVP.Presenter resultPresenter;
        private String difficulty;
        private int score;
        private TextView scoreTextView;
        private TextView difficultyTextView;
        private EditText pseudoInput;
        private ImageButton saveButton;

    public ResultGameFragment(int score,String difficulty){
        this.difficulty=difficulty;
        this.score=score;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        resultPresenter = new ResultPresenter(this);
        scoreTextView=view.findViewById(R.id.score_end);
        difficultyTextView=view.findViewById(R.id.difficulty_choose);
        scoreTextView.setText(Integer.toString(score));
        difficultyTextView.setText(difficulty);
        saveButton=view.findViewById(R.id.saveButton);

        pseudoInput=view.findViewById(R.id.pseudo_input);



        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(pseudoInput.getText().length()!=0){
                    Log.i(TAG,"Sauvegarde du score");
                     resultPresenter.saveScore(difficultyTextView.getText().toString(),Integer.parseInt(scoreTextView.getText().toString()),pseudoInput.getText().toString());
                        navigationPresenter.addFragment(new HomeFragment());
                }else{
                    Log.i(TAG,"Pseudo vide + Toast");
                    CharSequence text="Pseudo vide";
                    Context context= getActivity().getApplication();
                    Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
                }
            }
        });

        }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navigationPresenter.addFragment(new HomeFragment());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }



        @Override
    protected int getLayout() {
        return R.layout.result_game_view_fragment;
    }
}

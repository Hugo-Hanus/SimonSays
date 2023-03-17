package com.helmo.simonsays.share;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.helmo.simonsays.R;
import com.helmo.simonsays.mvp.BaseFragment;

public class ShareFragment extends BaseFragment implements ShareFragmentVP.View {
    protected static final String TAG ="ShareFragment";
    private ShareFragmentVP.Presenter sharePresenter;
    private TextView pseudoScore;
    private TextView difficultyScore;
    private TextView pointScore;
    private Button shareButton;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){
        View view = inflater.inflate(R.layout.share_view_fragment,container,false);
        Log.d(TAG,"onCreateView called");
        sharePresenter=new SharePresenter(this);
        pseudoScore=view.findViewById(R.id.pseudoScore);
        difficultyScore=view.findViewById(R.id.difficultyScore);
        pointScore=view.findViewById(R.id.pointScore);
        shareButton=view.findViewById(R.id.shareBestButton);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePresenter.shareScore(pseudoScore.getText(),difficultyScore.getText(),pointScore.getText());
            }
        });
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated called");
        sharePresenter=new SharePresenter(this);
        sharePresenter.loadBestScore();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
    }

    @Override
    protected int getLayout() {
        return R.layout.share_view_fragment;
    }

    @Override
    public void setBestScore(String pseudo, String difficulty, int point) {
        pseudoScore.setText(pseudo);
        difficultyScore.setText(difficulty);
        pointScore.setText(Integer.toString(point));
    }

    @Override
    public void sendBestScore(String formatedString){
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        String shareBody = formatedString;
        myIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
        myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(myIntent, "Partager via"));
    }

    @Override
    public void noBestScoreRegisted() {
        Log.i(TAG,"Pas de BestScore + Toast");
        CharSequence text="Vous n'avez pas encore de Meilleur score";
        Context context= getActivity().getApplication();
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}

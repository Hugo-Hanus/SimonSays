package com.helmo.simonsays.game;

import static android.content.Context.AUDIO_SERVICE;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;

import com.helmo.simonsays.R;
import com.helmo.simonsays.home.HomeFragment;
import com.helmo.simonsays.mvp.BaseFragment;

public class GameFragment extends BaseFragment implements SensorEventListener,GameFragmentVP.View {
    protected static final String TAG ="GameFragment";
    private GameFragmentVP.Presenter gamePresenter;
    private Button upButton;
    private Button rightButton;
    private final String difficulty;
    private Button leftButton;
    private Button downButton;
    private TextView coolDown;
    private TextView scoreText;


    private SensorManager sensorManager;
    private ToneGenerator toneGenerator ;

    public GameFragment(String difficulty){
        this.difficulty=difficulty;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG,"onViewCreated |"+this.difficulty);
        gamePresenter=new GamePresenter(this);
        //Button
        upButton=view.findViewById(R.id.UpBlueButton);
        rightButton=view.findViewById(R.id.RightGreenButton);
        leftButton=view.findViewById(R.id.LeftYellowButton);
        downButton=view.findViewById(R.id.DownRedButton);
        coolDown=view.findViewById(R.id.coolDown);
        scoreText=view.findViewById(R.id.scoreIncrement);

        //DifficultyChoose
        if(difficulty.equals("NORMAL")){
            coolDown.setVisibility(View.GONE);
        }else{
            coolDown.setVisibility(View.VISIBLE);
        }

        //Audio
        final AudioManager am = (AudioManager) requireActivity().getSystemService(AUDIO_SERVICE);
        final int volume_level= am.getStreamVolume(AudioManager.STREAM_MUSIC);
        //volume_Level =0min-15max 100/15=6.66
        toneGenerator=new ToneGenerator(AudioManager.STREAM_MUSIC, volume_level*6);
        //Sensor
        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor gyro = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE).get(0);
        sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_UI);



        upButton.setOnClickListener(view1 -> {
            playButton("UP");
            gamePresenter.pressMove("UP");
        });
        downButton.setOnClickListener(view12 -> {
            playButton("DOWN");
            gamePresenter.pressMove("DOWN");

        });
        rightButton.setOnClickListener(view13 -> {
            playButton("RIGHT");
            gamePresenter.pressMove("RIGHT");
        });
        leftButton.setOnClickListener(view14 -> {
            playButton("LEFT");
            gamePresenter.pressMove("LEFT");
        });
    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.values[0]<-5f){
            playButton("UP");
            Log.d("Sensor","UP"+sensorEvent.values[0]);
            gamePresenter.pressMove("UP");
        }else if (sensorEvent.values[0]>5f){
            Log.d("Sensor","DOWN"+sensorEvent.values[0]);
            playButton("DOWN");
            gamePresenter.pressMove("DOWN");
        }else if(sensorEvent.values[1]>5f){
            Log.d("Sensor","RIGHT"+sensorEvent.values[1]);
            playButton("RIGHT");
            gamePresenter.pressMove("RIGHT");
        }else if (sensorEvent.values[1]<-5f){
            Log.d("Sensor","LEFT"+sensorEvent.values[1]);
            playButton("LEFT");
            gamePresenter.pressMove("LEFT");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                gamePresenter.stopGame();
                onDestroy();
                navigationPresenter.addFragment(new HomeFragment());
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
        gamePresenter.startGame(difficulty);
    }


    @Override
    public void onDestroy() {
        sensorManager.unregisterListener(this);
        gamePresenter.stopGame();
        super.onDestroy();
        Log.d(TAG,"onDestroy");


    }

    @Override
    protected int getLayout() {
        return R.layout.game_view_fragment;
    }



    /*-----------ANIMATION--------------------*/
    private void clickAnimationButton(Button button,ToneGenerator generator){

        int id=button.getId();
        int[] variation = buttonVariation(id);
        highlightButton(button,variation[1]);
        generator.startTone(variation[0],300);
    }


    private void highlightButton(Button button,int color){
        ObjectAnimator animator = ObjectAnimator.ofArgb(button, "backgroundColor",color,Color.WHITE);
        animator.setDuration(200);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }
    private int[] buttonVariation(int id){
        switch(id){
            case 2131230737:return new int[]{ToneGenerator.TONE_DTMF_1,Color.rgb(72, 86, 150)};
            case 2131230729:return new int[]{ToneGenerator.TONE_DTMF_5,Color.rgb(80, 114, 60)};
            case 2131230726:return new int[]{ToneGenerator.TONE_DTMF_9,Color.rgb(233, 215, 88)};
            case 2131230724:return new int[]{ToneGenerator.TONE_DTMF_D,Color.rgb(240, 45, 58)};
            default:return new int[]{-1,-1};
        }
    }
    @Override
    public void playButton(String next) {
        switch (next){
            case "UP":
                clickAnimationButton(upButton,toneGenerator);
                break;
            case "DOWN":
                clickAnimationButton(downButton,toneGenerator);
                break;
            case "RIGHT":
                clickAnimationButton(rightButton,toneGenerator);
                break;
            case "LEFT":
                clickAnimationButton(leftButton,toneGenerator);
                break;
            default:
                break;
        }
    }



    // --------------SETTER VIEW----------------
    @Override
    public void setTimer(String timer) {
        coolDown.setText(timer);
    }
    @Override
    public void setScore(int score) {
        scoreText.setText(Integer.toString(score));
    }

    @Override
    public void buttonPlayable(boolean action) {
        upButton.setEnabled(action);
        downButton.setEnabled(action);
        leftButton.setEnabled(action);
        rightButton.setEnabled(action);
    }

    @Override
    public void endGame(int score, String difficulty) {
        onDestroy();
        navigationPresenter.addFragment(new ResultGameFragment(score,difficulty));
    }



    // FOR sensor
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //nothing
    }
}

package com.helmo.simonsays.classement;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.helmo.simonsays.R;
import com.helmo.simonsays.database.repository.ScoreRepository;
import com.helmo.simonsays.mvp.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class ClassementFragment extends BaseFragment implements ClassementFragmentVP.View {
    protected static final String TAG ="ClassementFragment";
    private RecyclerView recyclerView;
    private ClassementPresenter classementPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstance){
        View view = inflater.inflate(R.layout.score_view_fragment,container,false);
        Log.d(TAG,"onCreateView called");
        //Set adapter
        recyclerView = view.findViewById(R.id.classement);
        if(recyclerView != null){
            recyclerView.setAdapter(new ScoreAdapter(null));
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated called");
        classementPresenter=new ClassementPresenter(this);
        classementPresenter.loadScores();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
    }
    @Override
    public void loadView(){
        recyclerView.setAdapter(new ScoreAdapter(classementPresenter));
    }
    @Override
    public void noScoreRegister() {
        Log.i(TAG,"Pas de score + Toast");
        CharSequence text="Vous n'avez pas encore de score";
        Context context= getActivity().getApplication();
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected int getLayout() {
        return R.layout.score_view_fragment;
    }
}

package com.helmo.simonsays.classement;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helmo.simonsays.R;
import com.helmo.simonsays.model.Score;

import java.util.List;
import java.util.UUID;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {
    private final ClassementPresenter presenter;
    public ScoreAdapter(ClassementPresenter presenter){
        this.presenter=presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.score_item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreAdapter.ViewHolder holder, int position) {
        presenter.showClassementOn(holder,position);

    }

    @Override
    public int getItemCount() {

        if(presenter==null){
            return 0;
        }
        return presenter.getItemCount();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements ClassementFragmentVP.IScoreItemScreen {
        private TextView difficultyTextView,pseudoTextView;
        private UUID id;
        public ViewHolder(View view){
            super(view);
             difficultyTextView=view.findViewById(R.id.difficulty_score_item);
             pseudoTextView=view.findViewById(R.id.pseudo_item);
        }
        @Override
        public String toString() {
            return super.toString() + " '" + id + "'";
        }
        @Override
        public void showClassement(String pseudo, String difficulty, int point) {
            this.id=id;
            String secondRow=String.join(" | ",difficulty,Integer.toString(point));
            pseudoTextView.setText(pseudo);
            difficultyTextView.setText(secondRow);
        }
    }
}

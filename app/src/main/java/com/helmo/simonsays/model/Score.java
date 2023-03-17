package com.helmo.simonsays.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Score {
    @PrimaryKey
    @NonNull
    private UUID id;
    private String pseudo;
    private String difficulty;
    private int point;

    public Score(){
        id=UUID.randomUUID();
        pseudo="";
        difficulty="";
        point =0;
    }

    public UUID getId(){
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo){
        this.pseudo=pseudo;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public int getPoint() {
        return this.point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
}

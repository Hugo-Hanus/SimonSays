package com.helmo.simonsays.game.mecanics;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int score = 0;
    private List<String> pattern;
    private String perform;
    private String difficulty;
    private int step = -1;

    public Game(String difficulty) {
        this.score = 0;
        this.difficulty = difficulty;
        this.pattern = new ArrayList<String>();
        this.perform = "";
    }

    public List<String> getPattern() {
        return pattern;
    }
    public void setPattern(List<String> list) {this.pattern=list;}

    public int getLengthPattern() {
        return pattern.size();
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void addNewCaseGenerated() {
        pattern.add(generateNextCase());
    }

    public void scored() {
        score += 1;
        addNewCaseGenerated();
        resetStep();
    }

    private String generateNextCase() {
        int random = (int) ((Math.random() * 4) + 1);
        switch (random) {
            case 1:
                return "UP";
            case 2:
                return "RIGHT";
            case 3:
                return "LEFT";
            case 4:
                return "DOWN";
            default:
                return "DEFAULT";
        }
    }


    public void playerMove(String move) {
        perform = move;
    }

    public boolean verifyMoveNumber(int number) {
        return pattern.get(number).equals(perform);
    }

    public void startGame() {
        pattern.add(generateNextCase());
    }


    public int getScore() {
        return score;
    }

    public void moveStep() {
        this.step += 1;
    }
    public void setStep(int i){this.step=i;}

    public int getStep() {
        return step;
    }

    public boolean sequenceFinish() {
        return step == (pattern.size() - 1);
    }

    public void resetStep() {
        this.step = -1;
    }
}



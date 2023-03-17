package com.helmo.simonsays;
import org.junit.Test;

import static org.junit.Assert.*;

import com.helmo.simonsays.game.mecanics.Game;

import java.util.ArrayList;
import java.util.List;

public class GameTest {
    List<String> patternlist;
    String normal,difficile;
    Game gameNormal,gameDifficile;
    public void init(){
        List<String> temp= new ArrayList<String>();
        temp.add("UP");
        temp.add("UP");
        temp.add("DOWN");
        temp.add("LEFT");
        temp.add("RIGHT");
        patternlist=temp;
        normal="NORMAL";
        difficile="DIFFICILE";
        gameNormal=new Game(normal);
        gameDifficile=new Game(difficile);
        gameNormal.setPattern(patternlist);
        gameNormal.playerMove("UP");
        gameNormal.setStep(3);
        gameDifficile.setPattern(patternlist);
    }

    @Test
    public void getterGame() {
        init();
        List<String> test= new ArrayList<String>();
        test.add("UP");
        test.add("UP");
        test.add("DOWN");
        test.add("LEFT");
        test.add("RIGHT");

        // Pattern
        assertEquals(test,gameNormal.getPattern());

        //Length Pattern
        assertEquals(5,gameNormal.getLengthPattern());


        //Difficulty
        assertEquals(normal,gameNormal.getDifficulty());
        assertEquals(difficile,gameDifficile.getDifficulty());

        //Step
        assertEquals(3,gameNormal.getStep());

        // Score
        assertEquals(0,gameNormal.getScore());
        gameNormal.scored();
        assertEquals(1,gameNormal.getScore());

    }

    @Test
    public void verifyingAction(){
        init();

        //Move correct
        assertTrue(gameNormal.verifyMoveNumber(1));

        //sequenceFinish
        assertFalse(gameNormal.sequenceFinish());
        gameNormal.moveStep();
        assertTrue(gameNormal.sequenceFinish());

   }


    @Test
    public void newGameStart(){
        Game game = new Game("NORMAL");
        game.startGame();
        assertEquals(1,game.getLengthPattern());
        assertEquals(-1,game.getStep());
        assertEquals(0,game.getScore());
    }

    @Test
    public void randomGeneratedCase(){
        Game game = new Game("NORMAL");
        game.startGame();
        assertNotEquals("DEFAULT",game.getPattern().get(0));
        for(int i =0;i<50;i++){
            game.addNewCaseGenerated();
        }
        for(int i =0;i<51;i++){
            assertNotEquals("DEFAULT",game.getPattern().get(i));
        }
        for(int i =0;i<51;i++){
            String move=game.getPattern().get(i);
            game.playerMove(move);
            game.setStep(i);
            assertTrue(game.verifyMoveNumber(i));
        }
    }
}

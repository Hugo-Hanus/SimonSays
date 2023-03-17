package com.helmo.simonsays;

import com.helmo.simonsays.model.Score;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.UUID;
import java.util.regex.Pattern;


public class ScoreTest {
    @Test
    public void setterAndGetterScore() {
        Score score = new Score();

        Pattern UUID_REGEX =
                Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

        assertEquals("",score.getDifficulty());
        assertEquals("",score.getPseudo());
        assertEquals(0,score.getPoint());
        assertTrue(UUID_REGEX.matcher(score.getId().toString()).matches());


        UUID uuid=UUID.randomUUID();
        //Setter
        score.setPseudo("Pseudo");
        score.setPoint(4);
        score.setId(uuid);
        score.setDifficulty("DIFFICILE");
        //getter
        assertEquals("DIFFICILE",score.getDifficulty());
        assertEquals(uuid,score.getId());
        assertTrue(UUID_REGEX.matcher(uuid.toString()).matches());
        assertEquals("Pseudo",score.getPseudo());
        assertEquals(4,score.getPoint());

    }

}

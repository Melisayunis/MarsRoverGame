package com.example.MarsRover.unit.ServiceTest.EnumsTest;

import com.example.MarsRover.Service.Enums.LevelDifficult;
import org.junit.Test;
import static org.junit.Assert.*;

public class LevelDifficultTest {

    @Test
    public void levelDifficultValueTest() {
        LevelDifficult[] values = LevelDifficult.values();

        assertEquals(3, values.length);
        assertEquals(LevelDifficult.EASY, values[0]);
        assertEquals(LevelDifficult.MEDIUM, values[1]);
        assertEquals(LevelDifficult.HARD, values[2]);
    }

    @Test
    public void levelDifficultAttributesTest() {
        assertEquals("EASY", LevelDifficult.EASY.toString());
        assertEquals("easy", LevelDifficult.EASY.difficult);
        assertEquals(4, LevelDifficult.EASY.amountObjects);

        assertEquals("MEDIUM", LevelDifficult.MEDIUM.toString());
        assertEquals("medium", LevelDifficult.MEDIUM.difficult);
        assertEquals(7, LevelDifficult.MEDIUM.amountObjects);

        assertEquals("HARD", LevelDifficult.HARD.toString());
        assertEquals("hard", LevelDifficult.HARD.difficult);
        assertEquals(10, LevelDifficult.HARD.amountObjects);
    }
}

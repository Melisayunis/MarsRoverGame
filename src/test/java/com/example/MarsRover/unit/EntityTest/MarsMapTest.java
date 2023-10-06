package com.example.MarsRover.unit.EntityTest;

import com.example.MarsRover.Entity.MarsMap;
import com.example.MarsRover.Entity.MartianObject;
import com.example.MarsRover.Entity.Rover;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MarsMapTest {

    public MarsMap marsMap;

    @Before
    public void stepUp() {
        marsMap = new MarsMap("easy", 8, 8);
    }

    @After
    public void tearDown() {
        marsMap = null;
    }

    @Test
    public void constructorDefaultTest() {
        MarsMap marsMapDefault = new MarsMap();

        assertNotNull(marsMapDefault);

        assertNull(marsMapDefault.getId());
        assertNull(marsMapDefault.getLevelDifficult());
        assertNull(marsMapDefault.getHeightX());
        assertNull(marsMapDefault.getWideY());
        assertNull(marsMapDefault.getIsWon());
        assertNull(marsMapDefault.getFinishLine());
        assertNull(marsMapDefault.getRover());
        assertNull(marsMapDefault.getMartianObjects());
    }

    @Test
    public void getterTest() {
        assertEquals(8, (int) marsMap.getHeightX());
        assertEquals(8, (int) marsMap.getWideY());
        assertEquals("easy", marsMap.getLevelDifficult());

        assertNull(marsMap.getId());
        assertNull(marsMap.getIsWon());
        assertNull(marsMap.getFinishLine());
        assertNull(marsMap.getRover());
        assertNull(marsMap.getMartianObjects());
    }

    @Test
    public void setterTest() {
        marsMap.setHeightX(10);
        marsMap.setWideY(10);
        marsMap.setLevelDifficult("hard");
        marsMap.setIsWon(true);
        marsMap.setFinishLine(new Integer[]{2, 8});
        marsMap.setRover(new Rover());
        marsMap.setMartianObjects(new ArrayList<MartianObject>());

        assertEquals(10, (int) marsMap.getHeightX());
        assertEquals(10, (int) marsMap.getWideY());
        assertEquals("hard", marsMap.getLevelDifficult());
        assertEquals(2, (int) marsMap.getFinishLine()[0]);
        assertEquals(8, (int) marsMap.getFinishLine()[1]);

        assertNotNull(marsMap.getFinishLine());
        assertTrue(marsMap.getIsWon());
        assertNotNull(marsMap.getRover());
        assertNotNull(marsMap.getMartianObjects());
    }


}

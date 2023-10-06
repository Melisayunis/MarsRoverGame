package com.example.MarsRover.unit.EntityTest;

import com.example.MarsRover.Entity.Rover;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoverTest {
    
    private Rover rover;

    @Before
    public void setUp(){
        rover = new Rover(0, 0, "N");
    }

    @Test
    public void constructorDefaultTest() {
        Rover roverDefault = new Rover();

        assertNotNull(roverDefault);

        assertNull(roverDefault.getId());
        assertNull(roverDefault.getPositionX());
        assertNull(roverDefault.getPositionY());
        assertNull(roverDefault.getDirection());
        assertNull(roverDefault.getHasCrashed());
    }
    
    @Test
    public void getterTest() {
        assertEquals(0, (int) rover.getPositionX());
        assertEquals(0, (int) rover.getPositionY());
        assertEquals("N", rover.getDirection());
        assertFalse(rover.getHasCrashed());
    }

    @Test
    public void setterTest() {
        rover.setPositionX(1);
        rover.setPositionY(4);
        rover.setDirection("W");
        rover.setHasCrashed(true);

        assertEquals(1, (int) rover.getPositionX());
        assertEquals(4, (int) rover.getPositionY());
        assertEquals("W", rover.getDirection());
        assertTrue(rover.getHasCrashed());
    }

    @After
    public void tearDown() {
        rover = null;
    }

}



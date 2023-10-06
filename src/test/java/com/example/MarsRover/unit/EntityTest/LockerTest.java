package com.example.MarsRover.unit.EntityTest;

import com.example.MarsRover.Entity.Locker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LockerTest {

    public Locker locker;

    @Before
    public void stepUp() {
        locker = new Locker(0, 0);
    }

    @Test
    public void constructorDefaultTest() {
        Locker lockerDefault = new Locker();

        assertNotNull(lockerDefault);
        assertNull(lockerDefault.getId());
        assertNull(lockerDefault.getPositionX());
        assertNull(lockerDefault.getPositionY());
        assertNull(lockerDefault.getOccupied());
    }

    @Test
    public void getterTest() {
        assertEquals(0, (int) locker.getPositionX());
        assertEquals(0, (int) locker.getPositionY());
        assertFalse(locker.getOccupied());
    }

    @Test
    public void setterTest() {
        locker.setPositionX(4);
        locker.setPositionY(2);
        locker.setOccupied(true);

        assertEquals(4, (int) locker.getPositionX());
        assertEquals(2, (int) locker.getPositionY());
        assertTrue(locker.getOccupied());
    }

    @After
    public void tearDown() {
        locker = null;
    }

}

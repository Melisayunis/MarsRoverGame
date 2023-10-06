package com.example.MarsRover.unit.ServiceTest.EnumsTest;

import com.example.MarsRover.Service.Enums.Direction;
import org.junit.Test;
import static org.junit.Assert.*;

public class DirectionTest {

    @Test
    public void directionValuesTest() {
        Direction[] values = Direction.values();

        assertEquals(4, values.length);
        assertEquals(Direction.N, values[0]);
        assertEquals(Direction.S, values[1]);
        assertEquals(Direction.E, values[2]);
        assertEquals(Direction.W, values[3]);
    }

    @Test
    public void directionToStringTest() {
        assertEquals("N", Direction.N.toString());
        assertEquals("S", Direction.S.toString());
        assertEquals("E", Direction.E.toString());
        assertEquals("W", Direction.W.toString());
    }
}

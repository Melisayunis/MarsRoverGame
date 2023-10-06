package com.example.MarsRover.unit.ServiceTest;

import com.example.MarsRover.Entity.Rover;
import com.example.MarsRover.Repository.RoverRepository;
import com.example.MarsRover.Service.ILockerService;
import com.example.MarsRover.Service.RoverService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RoverMovesLookingNorthTest {

    @InjectMocks
    private RoverService roverService;

    @Mock
    private RoverRepository roverRepository;

    @Mock
    private ILockerService lockerService;

    private Rover createdRover;

    @Before
    public void setUp() {
        doNothing().when(lockerService).putOccupiedPosition(anyInt(), anyInt());

        roverService.createRover(8, 8);

        ArgumentCaptor<Rover> roverCaptor = ArgumentCaptor.forClass(Rover.class);
        verify(roverRepository).save(roverCaptor.capture());
        createdRover = roverCaptor.getValue();

        createdRover.setDirection("N");
    }

    // MOVES FORWARD

    @Test
    public void moveRoverForwardStart11Test() {
        // move Forward and look North -> move Up

        // Set Rover position: X, Y and Direction
        createdRover.setPositionX(1);
        createdRover.setPositionY(1);

        // Move the rover Forward
        roverService.moveRover("F");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(8, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverForwardStart14Test() {
        createdRover.setPositionX(1);
        createdRover.setPositionY(4);

        roverService.moveRover("F");
        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(8, (int) createdRover.getPositionX());
        assertEquals(4, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverForwardStart18Test() {
        createdRover.setPositionX(1);
        createdRover.setPositionY(8);

        roverService.moveRover("F");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(8, (int) createdRover.getPositionX());
        assertEquals(8, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverForwardStart41Test() {
        createdRover.setPositionX(4);
        createdRover.setPositionY(1);

        roverService.moveRover("F");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(3, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverForwardStart44Test() {
        createdRover.setPositionX(4);
        createdRover.setPositionY(4);

        roverService.moveRover("F");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(3, (int) createdRover.getPositionX());
        assertEquals(4, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverForwardStart48Test() {
        createdRover.setPositionX(4);
        createdRover.setPositionY(8);

        roverService.moveRover("F");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(3, (int) createdRover.getPositionX());
        assertEquals(8, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverForwardStart81Test() {
        createdRover.setPositionX(8);
        createdRover.setPositionY(1);

        roverService.moveRover("F");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(7, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverForwardStart84Test() {
        createdRover.setPositionX(8);
        createdRover.setPositionY(4);

        roverService.moveRover("F");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(7, (int) createdRover.getPositionX());
        assertEquals(4, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverForwardStart88Test() {
        createdRover.setPositionX(8);
        createdRover.setPositionY(8);

        roverService.moveRover("F");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(7, (int) createdRover.getPositionX());
        assertEquals(8, (int) createdRover.getPositionY());
    }

    // MOVES BACKWARD

    @Test
    public void moveRoverBackwardStart11Test() {
        createdRover.setPositionX(1);
        createdRover.setPositionY(1);

        roverService.moveRover("B");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(2, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverBackwardStart14Test() {
        createdRover.setPositionX(1);
        createdRover.setPositionY(4);

        roverService.moveRover("B");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(2, (int) createdRover.getPositionX());
        assertEquals(4, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverBackwardStart18Test() {
        createdRover.setPositionX(1);
        createdRover.setPositionY(8);

        roverService.moveRover("B");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(2, (int) createdRover.getPositionX());
        assertEquals(8, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverBackwardStart41Test() {
        createdRover.setPositionX(4);
        createdRover.setPositionY(1);

        roverService.moveRover("B");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(5, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverBackwardStart44Test() {
        createdRover.setPositionX(4);
        createdRover.setPositionY(4);

        roverService.moveRover("B");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(5, (int) createdRover.getPositionX());
        assertEquals(4, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverBackwardStart48Test() {
        createdRover.setPositionX(4);
        createdRover.setPositionY(8);

        roverService.moveRover("B");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(5, (int) createdRover.getPositionX());
        assertEquals(8, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverBackwardStart81Test() {
        createdRover.setPositionX(8);
        createdRover.setPositionY(1);

        roverService.moveRover("B");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(1, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverBackwardStart84Test() {
        createdRover.setPositionX(8);
        createdRover.setPositionY(4);

        roverService.moveRover("B");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(1, (int) createdRover.getPositionX());
        assertEquals(4, (int) createdRover.getPositionY());
    }

    @Test
    public void moveRoverBackwardStart88Test() {
        createdRover.setPositionX(8);
        createdRover.setPositionY(8);

        roverService.moveRover("B");

        assertEquals("N", createdRover.getDirection());
        assertFalse(createdRover.getHasCrashed());
        assertEquals(1, (int) createdRover.getPositionX());
        assertEquals(8, (int) createdRover.getPositionY());
    }

}

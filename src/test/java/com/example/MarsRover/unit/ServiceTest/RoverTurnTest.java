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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoverTurnTest {

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
        createdRover.setPositionX(1);
        createdRover.setPositionY(1);
    }

    /*
    TURN LEFT
       case ("N") -> roverInstance.setDirection("W");
       case ("S") -> roverInstance.setDirection("E");
       case ("E") -> roverInstance.setDirection("N");
       case ("W") -> roverInstance.setDirection("S");
    */

    @Test
    public void turnRoverLeftNorthTest() {
        createdRover.setDirection("N");
        assertEquals("N", createdRover.getDirection());

        roverService.turnRover("L");

        // Verifico que al girar, las posiciones no se cambien, ni el valor hasCrashed
        assertEquals(1, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
        assertFalse(createdRover.getHasCrashed());

        // Verifico que si el rover mira al N y gira a la izquierda, ahora mire al W
        assertEquals("W", createdRover.getDirection());
    }

    @Test
    public void turnRoverLeftSouthTest() {
        createdRover.setDirection("S");
        assertEquals("S", createdRover.getDirection());

        roverService.turnRover("L");

        assertEquals(1, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
        assertFalse(createdRover.getHasCrashed());
        assertEquals("E", createdRover.getDirection());
    }

    @Test
    public void turnRoverLeftEastTest() {
        createdRover.setDirection("E");
        assertEquals("E", createdRover.getDirection());

        roverService.turnRover("L");

        assertEquals(1, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
        assertFalse(createdRover.getHasCrashed());
        assertEquals("N", createdRover.getDirection());
    }

    @Test
    public void turnRoverLeftWestTest() {
        createdRover.setDirection("W");
        assertEquals("W", createdRover.getDirection());

        roverService.turnRover("L");

        assertEquals(1, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
        assertFalse(createdRover.getHasCrashed());
        assertEquals("S", createdRover.getDirection());
    }

    /*
    TURN RIGHT
        case ("N") -> roverInstance.setDirection("E");
        case ("S") -> roverInstance.setDirection("W");
        case ("E") -> roverInstance.setDirection("S");
        case ("W") -> roverInstance.setDirection("N");
     */

    @Test
    public void turnRoverRightNorthTest() {
        createdRover.setDirection("N");
        assertEquals("N", createdRover.getDirection());

        roverService.turnRover("R");

        // Verifico que al girar, las posiciones no se cambien, ni el valor hasCrashed
        assertEquals(1, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
        assertFalse(createdRover.getHasCrashed());

        // Verifico que si el rover mira al N y gira a la izquierda, ahora mire al E
        assertEquals("E", createdRover.getDirection());
    }

    @Test
    public void turnRoverRightSouthTest() {
        createdRover.setDirection("S");
        assertEquals("S", createdRover.getDirection());

        roverService.turnRover("R");

        assertEquals(1, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
        assertFalse(createdRover.getHasCrashed());
        assertEquals("W", createdRover.getDirection());
    }

    @Test
    public void turnRoverRightEastTest() {
        createdRover.setDirection("E");
        assertEquals("E", createdRover.getDirection());

        roverService.turnRover("R");

        assertEquals(1, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
        assertFalse(createdRover.getHasCrashed());
        assertEquals("S", createdRover.getDirection());
    }

    @Test
    public void turnRoverRightWestTest() {
        createdRover.setDirection("W");
        assertEquals("W", createdRover.getDirection());

        roverService.turnRover("R");

        assertEquals(1, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
        assertFalse(createdRover.getHasCrashed());
        assertEquals("N", createdRover.getDirection());
    }

    // Valores no validos para el metodo turnRover()

    @Test(expected = IllegalArgumentException.class)
    public void turnRoverInvalidValueTest() {
        // "testeo parametrizado" o "testeo con casos de prueba múltiples"

        roverService.turnRover("");
        roverService.turnRover(" ");
        roverService.turnRover("L ");
        roverService.turnRover(" R");

        roverService.turnRover("l");
        roverService.turnRover("r");
        roverService.turnRover("u");
        roverService.turnRover("aL");
        roverService.turnRover("Rf");

        roverService.turnRover("U");
        roverService.turnRover("ML");
        roverService.turnRover("YR");
        roverService.turnRover("LR");

        roverService.turnRover(".L");
        roverService.turnRover("R+");
        roverService.turnRover(".");
        roverService.turnRover("/");
        roverService.turnRover("-");
        roverService.turnRover("\n");

        roverService.turnRover("1L");
        roverService.turnRover("R5");
        roverService.turnRover("0");
        roverService.turnRover("321");
    }

}
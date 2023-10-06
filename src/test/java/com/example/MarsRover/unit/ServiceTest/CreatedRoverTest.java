package com.example.MarsRover.unit.ServiceTest;

import static org.mockito.Mockito.*;
import com.example.MarsRover.Entity.Rover;
import com.example.MarsRover.Repository.RoverRepository;
import com.example.MarsRover.Service.ILockerService;
import com.example.MarsRover.Service.RoverService;

import org.junit.After;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CreatedRoverTest {

    @InjectMocks
    private RoverService roverService;

    @Mock
    private RoverRepository roverRepository;

    @Mock
    private ILockerService lockerService;

    @Before
    public void setUp() {
        // Configuracion para indicarle a Mockito que no debe hacer nada cuando se
        // llame a putOccupiedPosition en la creacion del Rover.
        doNothing().when(lockerService).putOccupiedPosition(anyInt(), anyInt());
    }

    @Test
    public void createRoverTest() {
        roverService.createRover(8, 8);

        ArgumentCaptor<Rover> roverCaptor = ArgumentCaptor.forClass(Rover.class);
        verify(roverRepository).save(roverCaptor.capture());
        Rover createdRover = roverCaptor.getValue();

        assertNotNull(createdRover);
        assertNull(createdRover.getId());
        assertNotNull(createdRover.getPositionX());
        assertNotNull(createdRover.getPositionY());
        assertNotNull(createdRover.getDirection());
        assertNotNull(createdRover.getHasCrashed());
        assertFalse(createdRover.getHasCrashed());

        assertTrue("El atributo debería ser un entero", createdRover.getPositionX() instanceof Integer);
        assertTrue("El atributo debería ser un entero", createdRover.getPositionY() instanceof Integer);
        assertTrue("El atributo debería ser un boolean", createdRover.getHasCrashed() instanceof Boolean);
        assertTrue("El atributo debería ser un string", createdRover.getDirection() instanceof String);
    }

    @Test
    public void checkSetterTest() {
        roverService.createRover(8, 8);

        ArgumentCaptor<Rover> roverCaptor = ArgumentCaptor.forClass(Rover.class);
        verify(roverRepository).save(roverCaptor.capture());
        Rover createdRover = roverCaptor.getValue();

        // Como las posiciones se crear en una posicion random con createRover, las seteo para verificarlas
        createdRover.setPositionX(1);
        createdRover.setPositionY(1);
        createdRover.setHasCrashed(true);
        createdRover.setDirection("N");

        assertEquals(1, (int) createdRover.getPositionX());
        assertEquals(1, (int) createdRover.getPositionY());
        assertEquals("N", createdRover.getDirection());
        assertTrue(createdRover.getHasCrashed());
    }

}

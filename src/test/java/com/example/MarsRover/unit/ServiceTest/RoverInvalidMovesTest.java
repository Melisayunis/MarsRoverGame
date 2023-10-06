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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RoverInvalidMovesTest {

    @InjectMocks
    private RoverService roverService;

    @Mock
    private RoverRepository roverRepository;

    @Mock
    private ILockerService lockerService;

    @Before
    public void setUp() {
        doNothing().when(lockerService).putOccupiedPosition(anyInt(), anyInt());

        roverService.createRover(8, 8);

        ArgumentCaptor<Rover> roverCaptor = ArgumentCaptor.forClass(Rover.class);
        verify(roverRepository).save(roverCaptor.capture());
    }

    @Test(expected = IllegalArgumentException.class)
    public void movesRoverInvalidValueTest() {
        roverService.moveRover("");
        roverService.moveRover(" ");
        roverService.moveRover("F ");
        roverService.moveRover(" B");

        roverService.moveRover("f");
        roverService.moveRover("b");
        roverService.moveRover("u");
        roverService.moveRover("aF");
        roverService.moveRover("Bd");

        roverService.moveRover("U");
        roverService.moveRover("MF");
        roverService.moveRover("YB");
        roverService.moveRover("FB");

        roverService.moveRover(".F");
        roverService.moveRover("B+");
        roverService.moveRover(".");
        roverService.moveRover("/");
        roverService.moveRover("-");
        roverService.moveRover("\n");

        roverService.moveRover("1F");
        roverService.moveRover("B5");
        roverService.moveRover("0");
        roverService.moveRover("321");
    }

}

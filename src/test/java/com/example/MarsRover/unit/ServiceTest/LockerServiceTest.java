package com.example.MarsRover.unit.ServiceTest;

import com.example.MarsRover.Entity.Locker;
import com.example.MarsRover.Repository.LockerRepository;
import com.example.MarsRover.Service.LockerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class LockerServiceTest {

    @InjectMocks
    private LockerService lockerService;

    @Mock
    private LockerRepository lockerRepository;

    private List<Locker> lockers;

    @Before
    public void setUp() {
        lockers = new ArrayList<>();
        doAnswer(invocation -> {
            lockers.addAll(invocation.getArgument(0));
            return null;
        }).when(lockerRepository).saveAll(anyList());

        lockerService.createLockers(6, 6);
    }

    @Test
    public void checkCreatedLockersTest() {
        assertEquals(36, lockers.size());

        for (Locker locker: lockers) {
            assertNotNull(locker);
            assertFalse(locker.getOccupied());
            assertNotNull(locker.getPositionX());
            assertNotNull(locker.getPositionY());

            assertTrue("La posición X no es válida", locker.getPositionX() >= 1 && locker.getPositionX() <= 6);
            assertTrue("La posición Y no es válida", locker.getPositionY() >= 1 && locker.getPositionY() <= 6);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void createLockersWithInvalidValuesTest() {
        lockerService.createLockers(0, 0);
        lockerService.createLockers(0, 6);
        lockerService.createLockers(6, 0);
        lockerService.createLockers(-5, 5);
        lockerService.createLockers(6, -6);
    }

    @Test
    public void existLockersTest() {
        Locker firstLocker = lockers.get(0);
        Locker lastElementFirstLine = lockers.get(5);
        Locker lastLocker = lockers.get(35);
        Locker meddiumLocker = lockers.get(20);

        assertEquals(1, (int) firstLocker.getPositionX());
        assertEquals(1, (int) firstLocker.getPositionY());
        assertFalse(firstLocker.getOccupied());

        assertEquals(1, (int) lastElementFirstLine.getPositionX());
        assertEquals(6, (int) lastElementFirstLine.getPositionY());
        assertFalse(lastElementFirstLine.getOccupied());

        assertEquals(6, (int) lastLocker.getPositionX());
        assertEquals(6, (int) lastLocker.getPositionY());
        assertFalse(lastLocker.getOccupied());

        assertEquals(4, (int) meddiumLocker.getPositionX());
        assertEquals(3, (int) meddiumLocker.getPositionY());
        assertFalse(meddiumLocker.getOccupied());
    }

    @Test
    public void putOccupiedAndFreePositionTest() {
        for (Locker locker: lockers) {
            assertFalse(locker.getOccupied());
        }

        lockerService.putOccupiedPosition(1, 1);
        assertTrue(lockers.get(0).getOccupied());
        assertTrue(lockerService.isObjectHere(1, 1));
        lockerService.setFreePosition(1, 1);
        assertFalse(lockers.get(0).getOccupied());
        assertFalse(lockerService.isObjectHere(1, 1));

        lockerService.putOccupiedPosition(6, 6);
        assertTrue(lockers.get(35).getOccupied());
        assertTrue(lockerService.isObjectHere(6, 6));
        lockerService.setFreePosition(6, 6);
        assertFalse(lockers.get(35).getOccupied());
        assertFalse(lockerService.isObjectHere(6, 6));

        lockerService.putOccupiedPosition(1, 6);
        assertTrue(lockers.get(5).getOccupied());
        assertTrue(lockerService.isObjectHere(1, 6));
        lockerService.setFreePosition(1, 6);
        assertFalse(lockers.get(5).getOccupied());
        assertFalse(lockerService.isObjectHere(1, 6));

        lockerService.putOccupiedPosition(6, 1);
        assertTrue(lockers.get(30).getOccupied());
        assertTrue(lockerService.isObjectHere(6, 1));
        lockerService.setFreePosition(6, 1);
        assertFalse(lockers.get(30).getOccupied());
        assertFalse(lockerService.isObjectHere(6, 1));

        lockerService.putOccupiedPosition(3, 5);
        assertTrue(lockers.get(16).getOccupied());
        assertTrue(lockerService.isObjectHere(3, 5));
        lockerService.setFreePosition(3, 5);
        assertFalse(lockers.get(16).getOccupied());
        assertFalse(lockerService.isObjectHere(3, 5));

        lockerService.putOccupiedPosition(4, 2);
        assertTrue(lockers.get(19).getOccupied());
        assertTrue(lockerService.isObjectHere(4, 2));
        lockerService.setFreePosition(4, 2);
        assertFalse(lockers.get(19).getOccupied());
        assertFalse(lockerService.isObjectHere(4, 2));

        lockerService.putOccupiedPosition(2, 3);
        assertTrue(lockers.get(8).getOccupied());
        assertTrue(lockerService.isObjectHere(2, 3));
        lockerService.setFreePosition(2, 3);
        assertFalse(lockers.get(8).getOccupied());
        assertFalse(lockerService.isObjectHere(2, 3));

        lockerService.putOccupiedPosition(5, 4);
        assertTrue(lockers.get(27).getOccupied());
        assertTrue(lockerService.isObjectHere(5, 4));
        lockerService.setFreePosition(5, 4);
        assertFalse(lockers.get(27).getOccupied());
        assertFalse(lockerService.isObjectHere(5, 4));

    }

    @Test(expected = IllegalArgumentException.class)
    public void putOccupiedAndFreeInvalidPositionTest() {
        lockerService.putOccupiedPosition(0, 0);
        lockerService.putOccupiedPosition(0, 6);
        lockerService.putOccupiedPosition(1, 0);
        lockerService.putOccupiedPosition(-8, -1);
        lockerService.putOccupiedPosition(-3, 5);
        lockerService.putOccupiedPosition(5, -4);

        lockerService.setFreePosition(0 , 0);
        lockerService.setFreePosition(0, 6);
        lockerService.setFreePosition(1, 0);
        lockerService.setFreePosition(-8, -1);
        lockerService.setFreePosition(-3, 5);
        lockerService.setFreePosition(5, -4);
    }



}

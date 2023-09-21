package com.example.MarsRover.Service;

import com.example.MarsRover.Entity.Locker;
import com.example.MarsRover.Repository.LockerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LockerService implements ILockerService {

    protected List<Locker> lockers;

    @Autowired
    private LockerRepository lockerRepository;

    @Override
    public void createLockers(int heightX, int wideY) {
        lockers = new ArrayList<>();
        Locker locker;

        for (int i = 1; i <= heightX; i++) {
            for (int j = 1; j <= wideY; j++) {
                locker = new Locker(i, j);
                locker.setOccupied(false);
                lockers.add(locker);
            }
        }
        lockerRepository.saveAll(lockers);
    }

    public boolean isObjectHere(int positionX, int positionY) {
        // return true si hay un objeto en esa posicion, false en caso contrario

        for (Locker locker : lockers) {
            if (locker.getPositionX().equals(positionX) && locker.getPositionY().equals(positionY)) {
                if (locker.getOccupied().equals(true)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void putOccupiedPosition(int positionX, int positionY) {

        for (Locker locker : lockers) {
            if (locker.getPositionX() == positionX) {
                if (locker.getPositionY() == positionY) {
                    locker.setOccupied(true);
                }
            }
        }
    }

    public void setFreePosition(int positionX, int positionY) {

        for (Locker locker : lockers) {
            if (locker.getPositionX().equals(positionX) && locker.getPositionY().equals(positionY)) {
                locker.setOccupied(false);
            }
        }
    }

    @Override
    public void deleteAllLockers() {
        for (Locker locker : lockers) {
            lockerRepository.delete(locker);
        }
        lockers = null;
    }

}
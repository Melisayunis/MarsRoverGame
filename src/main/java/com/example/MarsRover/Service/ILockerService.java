package com.example.MarsRover.Service;

public interface ILockerService {

    void createLockers(int heightX, int wideY);

    void deleteAllLockers();

    boolean isObjectHere(int positionX, int positionY);

    void putOccupiedPosition(int positionX, int positionY);

    void setFreePosition(int positionX, int positionY);

}

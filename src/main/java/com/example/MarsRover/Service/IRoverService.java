package com.example.MarsRover.Service;

import com.example.MarsRover.Entity.Rover;

public interface IRoverService {

    void createRover(int heightX, int wideY);

    void deleteRover();

    Rover readRover(Rover rover);

    void updateRover(Rover rover);

    void moveRover(String command);

    void turnRover(String command);

}

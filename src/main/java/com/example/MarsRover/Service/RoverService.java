package com.example.MarsRover.Service;

import com.example.MarsRover.Entity.Rover;
import com.example.MarsRover.Repository.RoverRepository;
import com.example.MarsRover.Service.Enums.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RoverService implements IRoverService {

    private final Rover roverInstance = Rover.getInstance();

    private Integer heightXMap;
    private Integer wideYMap;

    @Autowired
    private RoverRepository roverRepository;

    @Autowired
    private ILockerService iLockerService;

    @Override
    public void createRover(int heightX, int wideY) {
        heightXMap = heightX;
        wideYMap = wideY;

        int positionX = randomNumber(1, heightX);
        int positionY = randomNumber(1, wideY);

        roverInstance.setPositionX(positionX);
        roverInstance.setPositionY(positionY);
        roverInstance.setDirection(randomDirectionRover());
        roverInstance.setHasCrashed(false);

        iLockerService.putOccupiedPosition(positionX, positionY);

        roverRepository.save(roverInstance);
    }

    @Override
    public void deleteRover() {
        heightXMap = 0;
        wideYMap = 0;
        //roverRepository.delete(roverInstance);
    }

    @Override
    public void moveRover(String moves) {
        if (moves.equals("F")) {
            moveForward();
        } else if (moves.equals("B")) {
            moveBackward();
        }

        if (!isValidMove(moves)) {
            throw new IllegalArgumentException("Valor de movimiento no válido: " + moves);
        }
    }

    private void moveForward() {
        int movingToX = roverInstance.getPositionX();
        int movingToY = roverInstance.getPositionY();
        String direction = roverInstance.getDirection();

        // me fijo hacia donde apunta
        if(direction.equals(Direction.N.name())) {
            // Change X - 1
            moveNorthSouth(movingToX, 1, heightXMap, movingToX - 1);

        } else if (direction.equals(Direction.S.name())) {
            // Change X + 1
            moveNorthSouth(movingToX, heightXMap, 1, movingToX + 1);

        } else if (direction.equals(Direction.E.name())) {
            // Change Y + 1
            moveEastWest(movingToY, wideYMap, 1, movingToY + 1);

        } else if (direction.equals(Direction.W.name())) {
            // Change Y - 1
            moveEastWest(movingToY, 1, wideYMap, movingToY - 1);
        }
    }

    private void moveBackward() {
        int movingToX = roverInstance.getPositionX();
        int movingToY = roverInstance.getPositionY();
        String direction = roverInstance.getDirection();

        // me fijo hacia donde apunta
        if(direction.equals(Direction.N.name())) {
            // Change X + 1
            moveNorthSouth(movingToX, heightXMap, 1, movingToX + 1);

        } else if (direction.equals(Direction.S.name())) {
            // Change X - 1
            moveNorthSouth(movingToX, 1, heightXMap, movingToX - 1);

        } else if (direction.equals(Direction.E.name())) {
            // Change Y - 1
            moveEastWest(movingToY, 1, wideYMap, movingToY - 1);

        } else if (direction.equals(Direction.W.name())) {
            // Change Y + 1
            moveEastWest(movingToY, wideYMap, 1, movingToY + 1);
        }
    }

    private void moveEastWest(int movingToY, int initialY, int finalY, int movingYHere) {
        int positionX = roverInstance.getPositionX();
        boolean isRoverMoved = false;

        if (movingToY == initialY) {
            isRoverMoved = isRoverMoving(positionX, finalY);
        } else {
            isRoverMoved = isRoverMoving(positionX, movingYHere);
        }

        roverCrash(positionX, movingToY, isRoverMoved);
    }

    private void moveNorthSouth(int movingToX, int initialX, int finalX, int movingXHere) {
        int positionY = roverInstance.getPositionY();
        boolean isRoverMoved = false;

        if (movingToX == initialX) {
            isRoverMoved = isRoverMoving(finalX, positionY);
        } else {
            isRoverMoved = isRoverMoving(movingXHere, positionY);
        }

        roverCrash(movingToX, positionY, isRoverMoved);
    }

    private boolean isRoverMoving(int positionX, int positionY) {
        if (!iLockerService.isObjectHere(positionX, positionY)) {
            moveRoverHere(positionX, positionY);
            return true;
        }
        return false;
    }

    private void moveRoverHere(int positionX, int positionY) {
        roverInstance.setPositionX(positionX);
        roverInstance.setPositionY(positionY);
        roverRepository.save(roverInstance);

        iLockerService.putOccupiedPosition(positionX, positionY);
    }

    private void roverCrash(int positionX, int positionY, boolean isRoverMoved) {
        if (isRoverMoved) {
            iLockerService.setFreePosition(positionX, positionY);
            roverInstance.setHasCrashed(false);
        } else {
            roverInstance.setHasCrashed(true);
        }
    }


    @Override
    public void turnRover(String turn) {
        if (turn.equals("L")) {
            turnLeft();
        } else if (turn.equals("R")) {
            turnRight();
        }

        if (!isValidTurn(turn)) {
            throw new IllegalArgumentException("Valor de giro no válido: " + turn);
        }
    }

    private void turnLeft() {
        switch (roverInstance.getDirection()) {
            case ("N") -> roverInstance.setDirection("W");
            case ("S") -> roverInstance.setDirection("E");
            case ("E") -> roverInstance.setDirection("N");
            case ("W") -> roverInstance.setDirection("S");
            default -> System.out.println(" No deberia entrar aca");
        }
    }

    private void turnRight() {
        switch (roverInstance.getDirection()) {
            case ("N") -> roverInstance.setDirection("E");
            case ("S") -> roverInstance.setDirection("W");
            case ("E") -> roverInstance.setDirection("S");
            case ("W") -> roverInstance.setDirection("N");
            default -> System.out.println(" No deberia entrar aca");
        }
    }

    private boolean isValidTurn(String turn) {
        return (turn.equals("L") || turn.equals("R")) ;
    }

    private boolean isValidMove(String moves) {
        return (moves.equals("F") || moves.equals("B")) ;
    }

    @Override
    public Rover readRover() {

        return roverRepository.findAll().get(0);
    }

    @Override
    public void updateRover(Rover rover) {
        roverInstance.setDirection(rover.getDirection());
        roverInstance.setPositionX(rover.getPositionX());
        roverInstance.setPositionY(rover.getPositionY());

        roverRepository.save(roverInstance);
    }

    private int randomNumber(int lowerBound, int upperBound) {

        return (int) (Math.random() * (upperBound - lowerBound + 1) + lowerBound);
    }

    private String randomDirectionRover(){
        Random random = new Random();
        Direction[] valueDirections = Direction.values();

        int valueIndex = random.nextInt(valueDirections.length);
        return valueDirections[valueIndex].name();
    }


}

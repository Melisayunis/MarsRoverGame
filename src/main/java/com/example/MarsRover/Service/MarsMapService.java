package com.example.MarsRover.Service;

import com.example.MarsRover.Entity.MarsMap;
import com.example.MarsRover.Entity.MartianObject;
import com.example.MarsRover.Entity.Rover;
import com.example.MarsRover.Repository.MarsMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarsMapService implements IMarsMapService {

    private final MarsMap marsMapInstance = MarsMap.getInstance();

    private final Rover roverInstance = Rover.getInstance();

    @Autowired
    private MarsMapRepository marsMapRepository;

    @Autowired
    private IRoverService roverService;

    @Autowired
    private IMartianObjectService martianObjectService;

    @Autowired
    private ILockerService iLockerService;

    public MarsMap createMarsMap(MarsMap marsMap) {
        int heightX = marsMap.getHeightX();
        int wideY = marsMap.getWideY();
        String levelDifficult = marsMap.getLevelDifficult();

        marsMapInstance.setLevelDifficult(levelDifficult);
        marsMapInstance.setHeightX(heightX);
        marsMapInstance.setWideY(wideY);
        marsMapInstance.setIsWon(false);

        iLockerService.createLockers(heightX, wideY);

        roverService.createRover(heightX, wideY);
        marsMapInstance.setRover(roverInstance);

        List<MartianObject> martianObjects = martianObjectService.createMartianObjects(levelDifficult);
        marsMapInstance.setMartianObjects(martianObjects);

        marsMapInstance.setFinishLine(createFinialLine());

        marsMapRepository.save(marsMapInstance);
        marsMapInstance.setId(marsMapRepository.findAll().get(0).getId());

        return marsMapInstance;
    }

    @Override
    public void deleteMarsMap() {
        iLockerService.deleteAllLockers();

        marsMapRepository.delete(marsMapInstance);
        martianObjectService.deleteAllMartianObjet();

        roverService.deleteRover();
    }

    @Override
    public List<MarsMap> readMarsMaps() {
        return marsMapRepository.findAll();
    }

    @Override
    public MarsMap updateMarsMapMoveRover(String command){
        roverService.moveRover(command);

        if (hasRoverArrivedTheEnd()) {
            marsMapInstance.setIsWon(true);
        }

        return marsMapInstance;
    }

    private boolean hasRoverArrivedTheEnd() {
        boolean samePositionX = (roverInstance.getPositionX() == marsMapInstance.getFinishLine()[0]);
        boolean samePositionY = (roverInstance.getPositionY() == marsMapInstance.getFinishLine()[1]);

        return (samePositionX && samePositionY);
    }

    @Override
    public MarsMap updateMarsMapTurnRover(String command){
        roverService.turnRover(command);

        return marsMapInstance;
    }

    private Integer[] createFinialLine(){
        Integer[] finishLine = new Integer[2];
        int positionX, positionY;
        boolean isCreated = false;

        do {
            positionX = randomNumber(1, marsMapInstance.getHeightX());
            positionY = randomNumber(1, marsMapInstance.getWideY());

            isCreated = isCreatedFinishLine(positionX, positionY, finishLine, isCreated);

        } while (!isCreated);

        return  finishLine;
    }

    private boolean isCreatedFinishLine(int positionX, int positionY, Integer[] finishLine, boolean isCreated) {
        if (!iLockerService.isObjectHere(positionX, positionY)) {
            finishLine[0] = positionX;
            finishLine[1] = positionY;

            isCreated = true;
        }
        return isCreated;
    }

    private int randomNumber(int lowerBound, int upperBound) {
        return (int) (Math.random() * (upperBound - lowerBound + 1) + lowerBound);
    }

}

package com.example.MarsRover.Service;

import com.example.MarsRover.Entity.MarsMap;
import com.example.MarsRover.Entity.MartianObject;
import com.example.MarsRover.Repository.MartianObjectRepository;
import com.example.MarsRover.Service.Enums.LevelDifficult;
import com.example.MarsRover.Service.Enums.MartianObjectNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MartianObjectService implements IMartianObjectService {

    protected List<MartianObject> martianObjects;

    private final MarsMap marsMapInstance = MarsMap.getInstance();

    @Autowired
    private MartianObjectRepository martianObjectRepository;

    @Autowired
    private ILockerService iLockerService;

    @Override
    public List<MartianObject> createMartianObjects(String levelDifficult) {
        martianObjects = new ArrayList<>();
        MartianObject martianObject;

        int amountObjectCreate = howManyObjectCreate(levelDifficult);
        int numberOfObjectsCreated = 0;
        int positionX, positionY;

        do {
            positionX = randomNumber(1, marsMapInstance.getHeightX());
            positionY = randomNumber(1,  marsMapInstance.getWideY());

            if (!iLockerService.isObjectHere(positionX, positionY)) {

                martianObject = new MartianObject(randomObjectMartian(), positionX, positionY);
                martianObjects.add(martianObject);
                iLockerService.putOccupiedPosition(positionX, positionY);

                numberOfObjectsCreated += 1;
            }
        } while (numberOfObjectsCreated < amountObjectCreate);

        return martianObjectRepository.saveAll(martianObjects);
    }

    @Override
    public void deleteAllMartianObjet() {
        for (MartianObject object : martianObjects) {
            martianObjectRepository.delete(object);
        }
        martianObjects = null;
    }

    private int howManyObjectCreate(String levelDifficult) {

        if (levelDifficult.equalsIgnoreCase(LevelDifficult.EASY.difficult)) {
            return LevelDifficult.EASY.amountObjects;
        } else if (levelDifficult.equalsIgnoreCase(LevelDifficult.MEDIUM.difficult)) {
            return LevelDifficult.MEDIUM.amountObjects;
        } else if (levelDifficult.equalsIgnoreCase(LevelDifficult.HARD.difficult)) {
            return LevelDifficult.HARD.amountObjects;
        }

        if(true) {
            System.out.println( " El level ingresado esta mal - no deberia ingresar aca");
            return 0;
        }
        return 0;
    }

    private String randomObjectMartian() {
        Random random = new Random();
        MartianObjectNames[] valueMartians = MartianObjectNames.values();

        int index = random.nextInt(valueMartians.length);

        return valueMartians[index].name().toLowerCase();
    }

    private int randomNumber(int lowerBound, int upperBound) {

        return (int) (Math.floor(Math.random() * (upperBound - lowerBound + 1) + lowerBound));
    }
}

package com.example.MarsRover.Service;

import com.example.MarsRover.Entity.MartianObject;

import java.util.List;

public interface IMartianObjectService {

    List<MartianObject> createMartianObjects(String levelDifficult);

    void deleteAllMartianObjet();


}

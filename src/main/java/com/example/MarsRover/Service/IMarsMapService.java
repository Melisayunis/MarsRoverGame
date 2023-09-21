package com.example.MarsRover.Service;

import com.example.MarsRover.Entity.MarsMap;

import java.util.List;

public interface IMarsMapService {

    MarsMap createMarsMap(MarsMap marsMap);

    void deleteMarsMap();

    List<MarsMap> readMarsMaps();

    MarsMap updateMarsMapMoveRover(String command);

    MarsMap updateMarsMapTurnRover(String command);
}

package com.example.MarsRover.Controller;

import com.example.MarsRover.Entity.MarsMap;
import com.example.MarsRover.Service.MarsMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class MarsMapController {

    @Autowired
    private MarsMapService marsMapService;

    @GetMapping("/marsmap")
    public ResponseEntity<List<MarsMap>> getAllMarsMap() {
        try {
            List<MarsMap> getMarsMap = marsMapService.readMarsMaps();
            return ResponseEntity.ok(getMarsMap);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/marsmap", consumes = "application/json")
    public ResponseEntity<MarsMap> createMarsMap(@RequestBody MarsMap marsMap) {
        try {
            MarsMap createdMarsMap = marsMapService.createMarsMap(marsMap);
            return new ResponseEntity<>(createdMarsMap, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/marsmap/moving-rover", consumes = "application/json")
    public ResponseEntity<MarsMap> moveRover(@RequestBody String command) {
        try {
            MarsMap movingRoverMap = marsMapService.updateMarsMapMoveRover(command);
            return new ResponseEntity<>(movingRoverMap, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/marsmap/turn-rover", consumes = "application/json")
    public ResponseEntity<MarsMap> turnRover(@RequestBody String command) {
        try {
            MarsMap movingRoverMap = marsMapService.updateMarsMapTurnRover(command);
            return new ResponseEntity<>(movingRoverMap, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(path = "/marsmap", consumes = "application/json")
    public ResponseEntity<MarsMap> deleteMarsMap() {
        try {
            marsMapService.deleteMarsMap();
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

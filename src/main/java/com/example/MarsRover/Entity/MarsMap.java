package com.example.MarsRover.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "mars_map")
public class MarsMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mars_map")
    protected Long id;

    @Column(name = "level_difficult", nullable = false)
    protected String levelDifficult;

    @Column(name = "height_x", nullable = false)
    protected Integer heightX;

    @Column(name = "wide_y", nullable = false)
    protected Integer wideY;

    @Column(name = "is_won")
    protected Boolean isWon;

    @ElementCollection
    @Column(name = "finish_line")
    protected Integer[] finishLine;

    @OneToOne(orphanRemoval = true)
    protected Rover rover;

    @OneToMany(orphanRemoval = true)
    protected List<MartianObject> martianObjects;

    public MarsMap(String levelDifficult, Integer heightX, Integer wideY) {
        this.levelDifficult = levelDifficult;
        this.heightX = heightX;
        this.wideY = wideY;
    }

    public MarsMap() {
    }

    private static MarsMap instance;

    public static synchronized MarsMap getInstance() {
        if (instance == null) {
            instance = new MarsMap();
        }
        return instance;
    }

}

package com.example.MarsRover.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "rover")
public class Rover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rover")
    protected Long id;

    @Column(name = "position_x", nullable = false)
    protected Integer positionX;

    @Column(name = "position_y", nullable = false)
    protected Integer positionY;

    @Column(name = "direction", nullable = false)
    protected String direction;

    @Column(name = "has_crashed")
    protected Boolean hasCrashed;

    public Rover(Integer positionX, Integer positionY, String direction) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
        this.hasCrashed = false;
    }

    public Rover() {
    }

    private static Rover instance;

    public static synchronized  Rover getInstance() {
        if (instance == null) {
            instance = new Rover();
        }
        return instance;
    }


}

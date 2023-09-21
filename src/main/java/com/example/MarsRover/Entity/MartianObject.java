package com.example.MarsRover.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "martian_object")
public class MartianObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_martian_object")
    protected Long id;

    @Column(name = "name")
    protected String name;

    @Column(name = "position_x", nullable = false)
    protected Integer positionX;

    @Column(name = "position_y", nullable = false)
    protected Integer positionY;

    public MartianObject(String name, Integer positionX, Integer positionY) {
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public MartianObject(){
    }

}

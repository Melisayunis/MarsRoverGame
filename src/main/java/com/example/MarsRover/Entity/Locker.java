package com.example.MarsRover.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name =  "locker")
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locker")
    protected Long id;

    @Column(name = "position_x", nullable = false)
    protected Integer positionX;

    @Column(name = "position_y", nullable = false)
    protected Integer positionY;

    @Column(name = "occupied")
    protected Boolean occupied;

    public Locker(Integer positionX, Integer positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.occupied = false;
    }

    public Locker() {
    }

}

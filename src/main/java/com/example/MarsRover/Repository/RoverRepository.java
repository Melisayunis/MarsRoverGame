package com.example.MarsRover.Repository;

import com.example.MarsRover.Entity.Rover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoverRepository extends JpaRepository<Rover, Long> {

}

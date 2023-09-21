package com.example.MarsRover.Repository;

import com.example.MarsRover.Entity.MarsMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarsMapRepository extends JpaRepository<MarsMap, Long> {

}

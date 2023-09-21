package com.example.MarsRover.Repository;

import com.example.MarsRover.Entity.MartianObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MartianObjectRepository extends JpaRepository<MartianObject, Long> {

}

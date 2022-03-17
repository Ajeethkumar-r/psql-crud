package com.java.springboot.respository;

import com.java.springboot.Entity.Robo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoboRepo extends JpaRepository<Robo, Long> {

}

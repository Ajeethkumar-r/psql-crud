package com.java.springboot.controller;

import com.java.springboot.respository.RoboRepo;
import com.java.springboot.schF.Sch;

import java.util.List;
import java.util.Optional;

import com.java.springboot.Entity.Robo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class actionController {

    @Autowired
    private RoboRepo roboRepo;

    @Autowired
    private Sch sch;

    @GetMapping("/robo/all")
    @ResponseBody
    public ResponseEntity<List<Robo>> getAllRobo() {
        sch.runJob();
        List<Robo> robo = roboRepo.findAll();
        return new ResponseEntity<List<Robo>>(robo, HttpStatus.OK);
    }

    @GetMapping("/robo/{roboId}")
    @ResponseBody
    public ResponseEntity<Robo> getOneRobo(@PathVariable Long roboId) {
        Optional<Robo> robo = roboRepo.findById(roboId);
        return new ResponseEntity<Robo>(robo.get(), HttpStatus.OK);

    }

    @PostMapping("/robo/create")
    @ResponseBody
    public ResponseEntity<Robo> createRobo(@RequestBody Robo robo) {
        Robo newRobo = roboRepo.save(robo);
        return new ResponseEntity<Robo>(newRobo, HttpStatus.CREATED);
    }

    @PutMapping("/robo/update/{roboId}")
    @ResponseBody
    public ResponseEntity<Robo> updateRobo(@PathVariable Long roboId, @RequestBody Robo robo) {
        Robo Urobo = roboRepo.findById(roboId).orElse(roboRepo.getById(roboId));
        Urobo.setName(robo.getName());
        final Robo updatedRobo = roboRepo.save(Urobo);
        return new ResponseEntity<Robo>(updatedRobo, HttpStatus.OK);
    }

    @DeleteMapping("/robo/{roboId}")
    @ResponseBody
    public ResponseEntity<String> deleteRobo(@PathVariable Long roboId) {
        roboRepo.deleteById(roboId);
        return new ResponseEntity<String>("Robo deleted successfully", HttpStatus.OK);
    }
}

package com.commonbackend.commonbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendations")
public class Recommendations {

    @GetMapping
    public ResponseEntity<?> getAllRecommendations() {

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> addRecommendation(@RequestBody Object obj) {

        return ResponseEntity.ok().build();
    }

}

/*
 * • Get all recommendations
 * GET /recommendations
 * Returns an array of recommendation objects
 * • Add a recommendation
 * POST /recommendation
 * Provide a recommendation as request object
 */
package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.model.RatingModel;
import com.att.tdp.bisbis10.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // Add a restaurant rating
    @PostMapping
    public ResponseEntity<Void> addRating(@RequestBody RatingModel ratingModel) {
        // Save the rating
        RatingModel savedRating = ratingService.saveRating(ratingModel);

        // Check if the new rating has been saved successfully
        if (savedRating != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

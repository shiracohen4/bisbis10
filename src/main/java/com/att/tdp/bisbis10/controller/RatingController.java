package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.model.RatingModel;
import com.att.tdp.bisbis10.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing restaurant ratings.
 */

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
        ratingService.saveRating(ratingModel);
        return ResponseEntity.ok().build();
    }
}

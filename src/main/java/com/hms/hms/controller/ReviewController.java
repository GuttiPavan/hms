package com.hms.hms.controller;

import com.hms.hms.entity.AppUser;
import com.hms.hms.entity.Property;
import com.hms.hms.entity.Reviews;
import com.hms.hms.repository.PropertyRepository;
import com.hms.hms.repository.ReviewsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service")

public class ReviewController {

    private final PropertyRepository propertyRepository;
    private final ReviewsRepository reviewsRepository;

    public ReviewController(PropertyRepository propertyRepository,
                            ReviewsRepository reviewsRepository) {
        this.propertyRepository = propertyRepository;
        this.reviewsRepository = reviewsRepository;
    }

    @PostMapping ("/userDetails")
    public AppUser getUserDetails(
            @AuthenticationPrincipal AppUser user
    ) {
        return user;
    }

    @PostMapping("/write-review")
    public ResponseEntity<?> write(@RequestBody Reviews reviews,
                                         @RequestParam long propertyId,
                                         @AuthenticationPrincipal AppUser user) {
        Property property = propertyRepository.findById(propertyId).get();

        if(reviewsRepository.existsByAppUserAndProperty(user,property)){
            return new ResponseEntity<>("Review alraedy exist",HttpStatus.CONFLICT);
        }

        reviews.setProperty(property);
        reviews.setAppUser(user);

        Reviews savedReview = reviewsRepository.save(reviews);

        return new ResponseEntity<>(savedReview, HttpStatus.OK);
    }

    @GetMapping("/user-reviews")
    public ResponseEntity<List<Reviews>> getUserReviews(
            @AuthenticationPrincipal AppUser user
    ) {
        List<Reviews> reviews = reviewsRepository.findByAppUser(user);

        return new ResponseEntity<>(reviews, HttpStatus.OK);

    }
}











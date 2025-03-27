package com.example.springhw03.controller;

import com.example.springhw03.model.dto.response.ApiResponse;
import com.example.springhw03.model.entity.Venue;
import com.example.springhw03.service.VenueService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
@AllArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(){
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .success(true)
                .message("Get All Venues Information Successefully!")
                .payload(venueService.getAllVenues())
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}

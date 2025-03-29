package com.example.springhw03.controller;

import com.example.springhw03.model.dto.request.VenueRequest;
import com.example.springhw03.model.dto.response.ApiResponse;
import com.example.springhw03.model.entity.Venue;
import com.example.springhw03.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
@AllArgsConstructor
@Tag(name = "Venue Controller")
public class VenueController {

    private final VenueService venueService;

    @Operation(summary = "Get all venues")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(@RequestParam(defaultValue = "1") @Positive Integer offset, @RequestParam(defaultValue = "10") @Positive Integer limit){
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .success(true)
                .message("Get all venues information successfully!")
                .payload(venueService.getAllVenues(offset, limit))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get venue by ID")
    @GetMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id") @Positive Integer venueId){
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .success(true)
                .message("Get venue by ID successfully!")
                .payload(venueService.getVenueById(venueId))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add a new venue")
    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> addVenue(@Valid @RequestBody VenueRequest venueRequest){
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .success(true)
                .message("Add a new venue successfully!")
                .payload(venueService.addVenue(venueRequest))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update venue by ID")
    @PutMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> updateVenueById(@PathVariable("venue-id") @Positive Integer venueId, @Valid @RequestBody VenueRequest venueRequest){
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .success(true)
                .message("Update a venue successfully!")
                .payload(venueService.updateVenueById(venueId, venueRequest))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete venue by ID")
    @DeleteMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> deleteVenueById(@PathVariable("venue-id") @Positive Integer venueId){
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .success(true)
                .message("Delete a venue successfully!")
                .payload(venueService.deleteVenueById(venueId))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
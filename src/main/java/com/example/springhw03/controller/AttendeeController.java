package com.example.springhw03.controller;

import com.example.springhw03.model.dto.request.AttendeeRequest;
import com.example.springhw03.model.dto.request.VenueRequest;
import com.example.springhw03.model.dto.response.ApiResponse;
import com.example.springhw03.model.entity.Attendee;
import com.example.springhw03.model.entity.Venue;
import com.example.springhw03.service.AttendeeService;
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
@RequestMapping("/api/v1/attendees")
@AllArgsConstructor
@Tag(name = "Attendee Controller")
public class AttendeeController {

    private final AttendeeService attendeeService;

    @Operation(summary = "Get all attendees")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllVenues(@RequestParam(defaultValue = "1") @Positive Integer offset, @RequestParam(defaultValue = "10") @Positive Integer limit){
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .success(true)
                .message("Get all attendees information successfully!")
                .payload(attendeeService.getAllAttendees(offset, limit))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get attendee by ID")
    @GetMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable("attendee-id") @Positive Integer attendeeId){
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .success(true)
                .message("Get attendee by ID successfully!")
                .payload(attendeeService.getAttendeeById(attendeeId))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add a new attendee")
    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> addVenue(@Valid @RequestBody AttendeeRequest attendeeRequest){
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .success(true)
                .message("Add a new attendee successfully!")
                .payload(attendeeService.addNewAttendee(attendeeRequest))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update attendee by ID")
    @PutMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendeeById(@PathVariable("attendee-id") @Positive Integer attendeeId, @Valid @RequestBody AttendeeRequest attendeeRequest){
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .success(true)
                .message("Update an attendee successfully!")
                .payload(attendeeService.updateAttendeeById(attendeeId, attendeeRequest))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete attendee by ID")
    @DeleteMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> deleteVenueById(@PathVariable("attendee-id") @Positive Integer attendeeId){
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .success(true)
                .message("Delete an attendee successfully!")
                .payload(attendeeService.getAttendeeById(attendeeId))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

}

package com.example.springhw03.controller;

import com.example.springhw03.model.dto.request.EventRequest;
import com.example.springhw03.model.dto.response.ApiResponse;
import com.example.springhw03.model.entity.Attendee;
import com.example.springhw03.model.entity.Event;
import com.example.springhw03.service.EventService;
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
@RequestMapping("/api/v1/evetns")
@AllArgsConstructor
@Tag(name = "Event Controller")
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Get all events")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllVenues(@RequestParam(defaultValue = "1") @Positive Integer offset, @RequestParam(defaultValue = "10") @Positive Integer limit){
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .success(true)
                .message("Get all events information successfully!")
                .payload(eventService.getAllEvents(offset, limit))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get event by ID")
    @GetMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable("event-id") @Positive Integer eventId){
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .success(true)
                .message("Get event by ID successfully!")
                .payload(eventService.getEventById(eventId))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add a new event attendee")
    @PostMapping
    public ResponseEntity<ApiResponse<Event>> addNewEvent(@Valid @RequestBody EventRequest eventRequest){
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .success(true)
                .message("Add a new event successfully!")
                .payload(eventService.addNewEvent(eventRequest))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        System.out.println(eventRequest.getVenueId());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update event by ID")
    @PutMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> updateEventById(@PathVariable("event-id") @Positive Integer eventId, @Valid @RequestBody EventRequest eventRequest){
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .success(true)
                .message("Update an event successfully!")
                .payload(eventService.updateEventById(eventId, eventRequest))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete event by ID")
    @DeleteMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> deleteVenueById(@PathVariable("event-id") @Positive Integer eventId){
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .success(true)
                .message("Delete an event successfully!")
                .payload(eventService.deleteEventById(eventId))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}

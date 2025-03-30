package com.example.springhw03.model.dto.request;

import com.example.springhw03.model.entity.Attendee;
import com.example.springhw03.model.entity.Venue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    @NotBlank(message = "EventName cannot be blank!")
    private String eventName;
//    @NotBlank(message = "EventDate cannot be blank!")
//    @FutureOrPresent(message = "EventDate better be present or future!")
    private LocalDateTime eventDate;
    @NotNull(message = "Venue cannot be blank!")
    private Integer venueId;
    @NotEmpty(message = "List of Attendees cannot be empty!")
    private List<Integer> attendeeId;
}

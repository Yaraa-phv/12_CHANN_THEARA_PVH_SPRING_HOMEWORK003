package com.example.springhw03.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventAttendee {
    private int id;
    private int venueId;
    private int attendeeId;
}

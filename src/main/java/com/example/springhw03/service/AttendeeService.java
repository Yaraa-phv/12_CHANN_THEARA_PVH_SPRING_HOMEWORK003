package com.example.springhw03.service;

import com.example.springhw03.model.dto.request.AttendeeRequest;
import com.example.springhw03.model.entity.Attendee;

import java.util.List;

public interface AttendeeService {

    List<Attendee> getAllAttendees(Integer offset, Integer limit);
    Attendee getAttendeeById(Integer attendeeId);
    Attendee addNewAttendee(AttendeeRequest attendeeRequest);
    Attendee updateAttendeeById(Integer attendeeId, AttendeeRequest attendeeRequest);
    Attendee deleteAttendeeById(Integer attendeeId);
}

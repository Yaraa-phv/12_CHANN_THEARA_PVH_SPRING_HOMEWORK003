package com.example.springhw03.service;

import com.example.springhw03.model.dto.request.EventRequest;
import com.example.springhw03.model.entity.Event;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents(Integer offset, Integer limit);
    Event getEventById(Integer eventId);
    Event addNewEvent(EventRequest eventRequest);
    Event updateEventById(Integer eventId, EventRequest eventRequest);
    Event deleteEventById(Integer eventId);
}

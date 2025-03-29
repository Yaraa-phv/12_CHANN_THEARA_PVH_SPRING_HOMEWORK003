package com.example.springhw03.service.Impl;

import com.example.springhw03.exception.NotFoundException;
import com.example.springhw03.model.dto.request.EventRequest;
import com.example.springhw03.model.entity.Event;
import com.example.springhw03.repository.AttendeeRepository;
import com.example.springhw03.repository.EventRepository;
import com.example.springhw03.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    @Override
    public List<Event> getAllEvents(Integer offset, Integer limit) {
        return eventRepository.getAllEvents(offset, limit);
    }

    @Override
    public Event getEventById(Integer eventId) {
        Event event = eventRepository.getEventById(eventId);

        if (event == null){
            throw new NotFoundException("Event Id "+eventId+ " is NOT FOUND");
        }
        return event;
    }

    @Override
    public Event addNewEvent(EventRequest eventRequest) {
        Event event = eventRepository.addNewEvent(eventRequest);

        for (Integer attId : eventRequest.getAttendeeId()){
            attendeeRepository.addToEventAttendee(event.getEventId(), attId);
        }
//        System.out.println("Event Id  : " + event.getEventId());

        return eventRepository.getEventById(event.getEventId());
    }

    @Override
    public Event updateEventById(Integer eventId, EventRequest eventRequest) {
//        System.out.println("Id of Event "+eventId);
        Event event = eventRepository.updateEventById(eventId, eventRequest);
        if (event == null){
            throw new NotFoundException("Event Id "+eventId+ " is NOT FOUND");
        }
        attendeeRepository.deleteEventAttendeeByEventId(event.getEventId());
//        System.out.println("EventId " + event.getEventId());
        for (Integer attId : eventRequest.getAttendeeId()){
            attendeeRepository.addToEventAttendee(event.getEventId(), attId);
        }

        return eventRepository.getEventById(event.getEventId());
    }

    @Override
    public Event deleteEventById(Integer eventId) {

        Event event = eventRepository.deleteEventById(eventId);
        if (event == null){
            throw new NotFoundException("Event Id "+eventId+ " is NOT FOUND");
        }
        attendeeRepository.deleteEventAttendeeByEventId(event.getEventId());
        return event;
    }
}

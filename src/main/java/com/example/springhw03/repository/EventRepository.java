package com.example.springhw03.repository;

import com.example.springhw03.model.dto.request.EventRequest;
import com.example.springhw03.model.entity.Event;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {

    @Select("""
        SELECT * FROM events
        ORDER BY event_id
        OFFSET #{offset}
        LIMIT #{limit}
    """)
    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id", one = @One(select = "com.example.springhw03.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id", many = @Many(select = "com.example.springhw03.repository.AttendeeRepository.getAttendeesByEventId"))
    })
    List<Event> getAllEvents(Integer offset, Integer limit);

    @Select("""
        SELECT * FROM events WHERE event_id = #{eventId}
    """)
    @ResultMap("eventMapper")
    Event getEventById(Integer eventId);

    @Select("""
        INSERT INTO events(event_name, venue_id) VALUES (#{request.eventName}, #{request.venueId})
        RETURNING *
    """)
    @ResultMap("eventMapper")
    @Result(property = "venueId", column = "venue_id", one = @One(select = "com.example.springhw03.repository.VenueRepository.getVenueById"))
    Event addNewEvent(@Param("request") EventRequest eventRequest);

    @Select("""
        UPDATE events 
        SET event_name = #{request.eventName}
        WHERE event_id = #{eventId}
        RETURNING *
    """)
    @ResultMap("eventMapper")
    Event updateEventById(Integer eventId, @Param("request") EventRequest eventRequest);

    @Select("""
        DELETE FROM events WHERE event_id = #{eventId}
        RETURNING *
    """)
    @ResultMap("eventMapper")
    Event deleteEventById(Integer eventId);
}

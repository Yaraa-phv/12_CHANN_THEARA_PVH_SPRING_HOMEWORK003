package com.example.springhw03.repository;

import com.example.springhw03.model.dto.request.AttendeeRequest;
import com.example.springhw03.model.entity.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Select("""
        SELECT * FROM attendees
        ORDER BY attendee_id
        OFFSET #{offset}
        LIMIT #{limit}
    """)
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    List<Attendee> getAllAttendees(Integer offset, Integer limit);

    @Select("""
        SELECT * FROM attendees
        WHERE attendee_id = #{attendeeId}
    """)
    @ResultMap("attendeeMapper")
    Attendee getAttendeeById(Integer attendeeId);

    @Select("""
        INSERT INTO attendees(attendee_name, email) 
        VALUES (#{request.attendeeName}, #{request.email})
        RETURNING *
    """)
    @ResultMap("attendeeMapper")
    Attendee addNewAttendee(@Param("request")AttendeeRequest attendeeRequest);

    @Select("""
        UPDATE attendees
        SET attendee_name = #{request.attendeeName}, email = #{request.email}
        WHERE attendee_id = #{attendeeId}
        RETURNING *
    """)
    @ResultMap("attendeeMapper")
    Attendee updateAttendeeById(Integer attendeeId, @Param("request") AttendeeRequest attendeeRequest);

    @Select("""
        DELETE FROM attendees
        WHERE attendee_id = #{attendeeId}
        RETURNING *
    """)
    @ResultMap("attendeeMapper")
    Attendee deleteAttendeeById(Integer attendeeId);

    @Select("""
        SELECT * FROM attendees att
        INNER JOIN event_attendee ett ON att.attendee_id = ett.attendee_id
        WHERE event_id = #{eventId}
    """)
    @ResultMap("attendeeMapper")
    @Result(property = "eventId", column = "event_id")
    List<Attendee> getAttendeesByEventId(Integer eventId);

    @Select("""
        INSERT INTO event_attendee(event_id, attendee_id) 
        VALUES (#{eventId}, #{attendeeId})
        RETURNING *
    """)
    @ResultMap("attendeeMapper")
    @Result(property = "eventId", column = "event_id")
    void addToEventAttendee(Integer eventId, Integer attendeeId);

    @Select("""
        DELETE FROM event_attendee
        WHERE event_id = #{eventId}
    """)
    @ResultMap("attendeeMapper")
    @Result(property = "eventId", column = "event_id")
    void deleteEventAttendeeByEventId(Integer eventId);

    @Select("""
        UPDATE event_attendee
        SET attendee_id = #{attendeeId}
        WHERE event_id = #{eventId}
    """)
    @ResultMap("attendeeMapper")
    void updateEventAttendee(Integer eventId, Integer attendeeId);
}

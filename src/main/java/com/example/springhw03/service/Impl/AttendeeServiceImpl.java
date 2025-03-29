package com.example.springhw03.service.Impl;

import com.example.springhw03.exception.NotFoundException;
import com.example.springhw03.model.dto.request.AttendeeRequest;
import com.example.springhw03.model.entity.Attendee;
import com.example.springhw03.repository.AttendeeRepository;
import com.example.springhw03.service.AttendeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;

    @Override
    public List<Attendee> getAllAttendees(Integer offset, Integer limit) {
        return attendeeRepository.getAllAttendees(offset, limit);
    }

    @Override
    public Attendee getAttendeeById(Integer attendeeId) {

        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);

        if (attendee == null){
            throw new NotFoundException("Attendee Id " +attendeeId+ " is NOT FOUND");
        }

        return attendee;
    }

    @Override
    public Attendee addNewAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.addNewAttendee(attendeeRequest);
    }

    @Override
    public Attendee updateAttendeeById(Integer attendeeId, AttendeeRequest attendeeRequest) {

        Attendee attendee = attendeeRepository.updateAttendeeById(attendeeId, attendeeRequest);

        if (attendee == null){
            throw new NotFoundException("Attendee Id " +attendeeId+ " is NOT FOUND");
        }

        return attendee;
    }

    @Override
    public Attendee deleteAttendeeById(Integer attendeeId) {
        Attendee attendee = attendeeRepository.deleteAttendeeById(attendeeId);

        if (attendee == null){
            throw new NotFoundException("Attendee Id " +attendeeId+ " is NOT FOUND");
        }

        return attendee;
    }
}

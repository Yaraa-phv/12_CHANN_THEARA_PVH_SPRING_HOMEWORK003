package com.example.springhw03.service.Impl;

import com.example.springhw03.model.entity.Venue;
import com.example.springhw03.repository.VenueRepository;
import com.example.springhw03.service.VenueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues() {
        return venueRepository.getAllVenues();
    }
}

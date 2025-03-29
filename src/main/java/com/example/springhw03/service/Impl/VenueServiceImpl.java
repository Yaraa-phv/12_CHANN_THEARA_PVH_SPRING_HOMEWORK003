package com.example.springhw03.service.Impl;

import com.example.springhw03.exception.NotFoundException;
import com.example.springhw03.model.dto.request.VenueRequest;
import com.example.springhw03.model.entity.Venue;
import com.example.springhw03.repository.VenueRepository;
import com.example.springhw03.service.VenueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@AllArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues(Integer offset, Integer limit) {
        List<Venue> venues = venueRepository.getAllVenues(offset, limit);
        return venues;
    }

    @Override
    public Venue getVenueById(Integer venueId) {

        Venue venue = venueRepository.getVenueById(venueId);
        if (venue == null) {
            throw new NotFoundException("Venue ID "+ venueId +" is NOT FOUND!");
        }
        return venue;
    }

    @Override
    public Venue addVenue(VenueRequest venueRequest) {
        return venueRepository.addVenue(venueRequest);
    }

    @Override
    public Venue updateVenueById(Integer venueId, VenueRequest venueRequest) {
        Venue venue = venueRepository.updateVenueById(venueId, venueRequest);

        if (venue == null){
            throw new NotFoundException("Venue ID "+ venueId +" is NOT FOUND!");
        }
        return venue;
    }

    @Override
    public Venue deleteVenueById(Integer venueId) {
        Venue venue = venueRepository.deleteVenueById(venueId);

        if (venue == null){
            throw new NotFoundException("Venue ID "+ venueId +" is NOT FOUND!");
        }
        return venue;
    }


}

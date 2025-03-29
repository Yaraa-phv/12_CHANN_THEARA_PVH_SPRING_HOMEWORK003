package com.example.springhw03.service;

import com.example.springhw03.model.dto.request.VenueRequest;
import com.example.springhw03.model.entity.Venue;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues(Integer offset, Integer limit);
    Venue getVenueById(Integer venueId);
    Venue addVenue(VenueRequest venueRequest);
    Venue updateVenueById(Integer venueId, VenueRequest venueRequest);
    Venue deleteVenueById(Integer venueId);
}

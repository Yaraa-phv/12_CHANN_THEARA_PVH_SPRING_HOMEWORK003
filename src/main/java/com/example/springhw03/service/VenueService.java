package com.example.springhw03.service;

import com.example.springhw03.model.entity.Venue;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues();
}

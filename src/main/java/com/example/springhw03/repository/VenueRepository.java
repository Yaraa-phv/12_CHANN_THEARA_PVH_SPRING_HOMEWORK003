package com.example.springhw03.repository;

import com.example.springhw03.model.entity.Venue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Select("""
        SELECT * FROM eventmanagement_db.public.venues;
    """)
    List<Venue> getAllVenues();
}

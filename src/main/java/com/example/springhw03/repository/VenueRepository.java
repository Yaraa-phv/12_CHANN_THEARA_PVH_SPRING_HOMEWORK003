package com.example.springhw03.repository;

import com.example.springhw03.model.dto.request.VenueRequest;
import com.example.springhw03.model.entity.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Select("""
        SELECT * FROM venues ORDER BY venue_id
        OFFSET #{offset} 
        LIMIT #{limit}
    """)
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")
    })
    List<Venue> getAllVenues(Integer offset, Integer limit);


    @Select("""
        SELECT * FROM venues
        WHERE venue_id = #{venueId}
    """)
    @ResultMap("venueMapper")
    Venue getVenueById(Integer venueId);


    @Select("""
        INSERT INTO venues(venue_name, location) 
        VALUES (#{request.venueName}, #{request.location})
        RETURNING *
    """)
    @ResultMap("venueMapper")
    Venue addVenue(@Param("request") VenueRequest venueRequest);

    @Select("""
        UPDATE venues 
        SET venue_name = #{request.venueName}, location = #{request.location}
        WHERE venue_id = #{venueId}
        RETURNING*
    """)
    @ResultMap("venueMapper")
    Venue updateVenueById(Integer venueId, @Param("request") VenueRequest venueRequest);

    @Select("""
        DELETE FROM venues
        WHERE venue_id = #{venueId}
        RETURNING*
    """)
    @ResultMap("venueMapper")
    Venue deleteVenueById(Integer venueId);
}

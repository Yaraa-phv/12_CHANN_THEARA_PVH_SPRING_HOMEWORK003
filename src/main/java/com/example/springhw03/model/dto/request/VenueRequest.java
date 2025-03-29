package com.example.springhw03.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {

    @NotBlank(message = "VenueName cannot be blank!")
    private String venueName;
    @NotBlank(message = "Location cannot be blank!")
    private String location;
}

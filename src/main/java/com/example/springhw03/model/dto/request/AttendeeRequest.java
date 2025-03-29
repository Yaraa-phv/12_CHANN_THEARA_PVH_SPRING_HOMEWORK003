package com.example.springhw03.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    @NotBlank(message = "AttendeeName cannot be blank!")
    private String attendeeName;
    @NotBlank(message = "Email cannot be blank!")
    private String email;
}

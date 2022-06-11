package com.uag.smdsm.security.models;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private LocalDate timestamp;
    private String message;
    private String details;
}

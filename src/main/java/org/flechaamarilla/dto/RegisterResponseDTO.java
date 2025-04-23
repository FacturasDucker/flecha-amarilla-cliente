package org.flechaamarilla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for user registration responses
 * Uses Lombok annotations for automatic generation of getters, setters, and constructors
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String message;
}
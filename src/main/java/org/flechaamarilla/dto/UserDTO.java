package org.flechaamarilla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flechaamarilla.entity.User;

import java.time.LocalDateTime;

/**
 * DTO for transferring user data without sensitive information
 * Uses Lombok annotations for automatic generation of getters, setters, and constructors
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor to convert from User entity
    public UserDTO(User user) {
        this.id = user.id;
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
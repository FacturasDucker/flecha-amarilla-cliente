package org.flechaamarilla.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.flechaamarilla.dto.RegisterRequestDTO;
import org.flechaamarilla.dto.RegisterResponseDTO;
import org.flechaamarilla.entity.User;
import org.flechaamarilla.exception.EmailAlreadyExistsException;
import org.flechaamarilla.exception.UserNotFoundException;
import org.flechaamarilla.repository.UserRepository;
import org.flechaamarilla.security.PasswordService;

import java.time.LocalDateTime;

/**
 * Service for handling user authentication and registration
 */
@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    PasswordService passwordService;


    /**
     * Register a new user
     *
     * @param registerRequest DTO containing registration information
     * @return response with new user details
     */
    @Transactional
    public RegisterResponseDTO register(RegisterRequestDTO registerRequest) {
        // Check if user already exists
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        // Create and save new user
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordService.hashPassword(registerRequest.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.persist(user);

        // Return response with user details
        return new RegisterResponseDTO(
                user.id,
                user.getName(),
                user.getEmail(),
                "Usuario registrado exitosamente"
        );
    }


    /**
     * Get user by email
     *
     * @param email user's email address
     * @return the user entity if found
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con el email: " + email));
    }
}
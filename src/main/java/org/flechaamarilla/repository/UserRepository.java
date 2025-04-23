package org.flechaamarilla.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.flechaamarilla.entity.User;

import java.util.Optional;

/**
 * Repository for User entity operations
 * Uses Panache to simplify database operations
 */
@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    /**
     * Find a user by name (username)
     * @param username the username to search for
     * @return optional containing the user if found
     */
    public Optional<User> findByUsername(String username) {
        return find("name", username).firstResultOptional();
    }

    /**
     * Find a user by email
     * @param email the email to search for
     * @return optional containing the user if found
     */
    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    /**
     * Check if a username already exists
     * @param username the username to check
     * @return true if username exists, false otherwise
     */
    public boolean usernameExists(String username) {
        return count("name", username) > 0;
    }

    /**
     * Check if an email already exists
     * @param email the email to check
     * @return true if email exists, false otherwise
     */
    public boolean emailExists(String email) {
        return count("email", email) > 0;
    }

    /**
     * Check if a user with the given email exists
     * @param email the email to check
     * @return true if a user with the email exists, false otherwise
     */
    public boolean existsByEmail(String email) {
        return count("email", email) > 0;
    }

}
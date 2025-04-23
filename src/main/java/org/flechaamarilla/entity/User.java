package org.flechaamarilla.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a user in the system.
 * Uses Lombok for code generation and Panache for simplified database operations.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends PanacheEntity {

    // Primary key will be managed by PanacheEntity

    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Add this to the User class
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaxCertificateStatus> taxCertificateStatuses = new ArrayList<>();
    
    /**
     * List of roles could be added here for role-based access control
     * For example:
     * @ManyToMany(fetch = FetchType.EAGER)
     * private Set<Role> roles = new HashSet<>();
     */
}
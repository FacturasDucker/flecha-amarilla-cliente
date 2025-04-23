package org.flechaamarilla.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tax_certificate_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxCertificateStatus extends PanacheEntity {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "RFC is mandatory")
    @Pattern(regexp = "^[A-Z0-9]{12,13}$", message = "RFC must be 12-13 alphanumeric characters")
    private String rfc;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 150, message = "Email must not exceed 150 characters")
    private String email;

    @NotBlank(message = "Postal code is mandatory")
    @Pattern(regexp = "^[0-9]{5}$", message = "Postal code must be exactly 5 digits")
    private String postalCode;

    @NotBlank(message = "Company name is mandatory")
    @Size(max = 200, message = "Company name must not exceed 200 characters")
    private String companyName;

    @NotBlank(message = "Tax regime is mandatory")
    @Size(max = 50, message = "Tax regime must not exceed 50 characters")
    private String taxRegime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
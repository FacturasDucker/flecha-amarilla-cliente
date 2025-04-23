package org.flechaamarilla.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxCertificateStatusRequestDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "RFC is mandatory")
    private String rfc;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Postal code is mandatory")
    private String postalCode;

    @NotBlank(message = "Company name is mandatory")
    private String companyName;

    @NotBlank(message = "Tax regime is mandatory")
    private String taxRegime;
}
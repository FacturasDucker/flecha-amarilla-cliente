package org.flechaamarilla.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flechaamarilla.entity.TaxCertificateStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxCertificateStatusDTO {
    private Long id;
    private String name;
    private String rfc;
    private String email;
    private String postalCode;
    private String companyName;
    private String taxRegime;
    private Long userId;

    public TaxCertificateStatusDTO(TaxCertificateStatus entity) {
        this.id = entity.id;
        this.name = entity.getName();
        this.rfc = entity.getRfc();
        this.email = entity.getEmail();
        this.postalCode = entity.getPostalCode();
        this.companyName = entity.getCompanyName();
        this.taxRegime = entity.getTaxRegime();
        this.userId = entity.getUser().id;
    }
}
package org.flechaamarilla.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.flechaamarilla.dto.TaxCertificateStatusDTO;
import org.flechaamarilla.dto.TaxCertificateStatusRequestDTO;
import org.flechaamarilla.entity.TaxCertificateStatus;
import org.flechaamarilla.entity.User;
import org.flechaamarilla.repository.TaxCertificateStatusRepository;
import org.flechaamarilla.repository.UserRepository;

@ApplicationScoped
public class TaxCertificateStatusService {

    @Inject
    UserRepository userRepository;

    @Inject
    TaxCertificateStatusRepository taxCertificateStatusRepository;

    @Transactional
    public TaxCertificateStatusDTO createTaxCertificateStatus(TaxCertificateStatusRequestDTO request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new org.flechaamarilla.exception.UserNotFoundException("Usuario no encontrado: " + userEmail));

        TaxCertificateStatus taxCertificateStatus = new TaxCertificateStatus();
        taxCertificateStatus.setName(request.getName());
        taxCertificateStatus.setRfc(request.getRfc());
        taxCertificateStatus.setEmail(request.getEmail());
        taxCertificateStatus.setPostalCode(request.getPostalCode());
        taxCertificateStatus.setCompanyName(request.getCompanyName());
        taxCertificateStatus.setTaxRegime(request.getTaxRegime());
        taxCertificateStatus.setUser(user);

        taxCertificateStatusRepository.persist(taxCertificateStatus);

        return new TaxCertificateStatusDTO(taxCertificateStatus);
    }
}
package org.flechaamarilla.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.flechaamarilla.entity.TaxCertificateStatus;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TaxCertificateStatusRepository implements PanacheRepository<TaxCertificateStatus> {

    public List<TaxCertificateStatus> findByUserId(Long userId) {
        return list("user.id", userId);
    }

    public Optional<TaxCertificateStatus> findByRfc(String rfc) {
        return find("rfc", rfc).firstResultOptional();
    }

    public boolean existsByRfc(String rfc) {
        return count("rfc", rfc) > 0;
    }
}
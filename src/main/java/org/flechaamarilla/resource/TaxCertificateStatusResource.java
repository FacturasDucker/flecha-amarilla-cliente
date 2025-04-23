package org.flechaamarilla.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.flechaamarilla.dto.TaxCertificateStatusDTO;
import org.flechaamarilla.dto.TaxCertificateStatusRequestDTO;
import org.flechaamarilla.service.TaxCertificateStatusService;

@Path("/api/tax-certificate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Tax Certificate", description = "Tax certificate operations")
public class TaxCertificateStatusResource {

    @Inject
    TaxCertificateStatusService taxCertificateStatusService;

    @POST
    @RolesAllowed({"user", "admin"})
    @Operation(summary = "Create tax certificate status", description = "Creates a new tax certificate status for the authenticated user")
    public Response createTaxCertificateStatus(@Valid TaxCertificateStatusRequestDTO request,
                                              @Context SecurityContext securityContext) {
        String userEmail = securityContext.getUserPrincipal().getName();
        TaxCertificateStatusDTO response = taxCertificateStatusService.createTaxCertificateStatus(request, userEmail);

        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }
}
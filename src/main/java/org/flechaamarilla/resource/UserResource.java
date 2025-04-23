package org.flechaamarilla.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.flechaamarilla.dto.RegisterRequestDTO;
import org.flechaamarilla.dto.RegisterResponseDTO;
import org.flechaamarilla.dto.UserDTO;
import org.flechaamarilla.entity.User;
import org.flechaamarilla.exception.UserNotFoundException;
import org.flechaamarilla.service.UserService;

import java.util.Map;

/**
 * Resource exposing authentication endpoints
 */
@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Authentication", description = "Authentication operations")
public class UserResource {

    @Inject
    UserService userService;

    @Inject
    JsonWebToken jwt;

    /**
     * Register a new user
     *
     * @param request registration information
     * @return response with new user details
     */
    @POST
    @Path("/register")
    @PermitAll
    @Operation(summary = "Register a new user", description = "Creates a new user account with the provided details")
    public Response register(@Valid RegisterRequestDTO request) {
        RegisterResponseDTO response = userService.register(request);
        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }


    /**
     * Get user by email
     *
     * @param email user's email
     * @return response with user details
     */

    @GET
    @Path("/byEmail/{email}")
    @PermitAll
    @Operation(summary = "Get user by email", description = "Retrieves user information based on the provided email address")
    public Response getUserByEmail(@PathParam("email") String email) {
        try {
            User user = userService.getUserByEmail(email);
            UserDTO userDTO = new UserDTO(user);
            return Response.ok(userDTO).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("message", e.getMessage()))
                    .build();
        }
    }

    /**
     * Protected endpoint example - only for authenticated users
     * Returns information about the currently authenticated user
     */
    @GET
    @Path("/me")
    @RolesAllowed({"user", "admin"})
    @Operation(summary = "Get current user", description = "Returns information about the authenticated user")
    public Response getUserInfo(@Context SecurityContext securityContext) {
        String email = securityContext.getUserPrincipal().getName();

        // El servicio de autenticación se encarga de buscar al usuario y manejar excepciones
        return Response.ok(Map.of(
                "email", email,
                "auth", Map.of(
                        "isAuthenticated", true,
                        "roles", jwt.getGroups()
                )
        )).build();
    }
}
package org.acme.controller;

import com.google.common.base.Preconditions;
import io.quarkus.panache.common.Sort;
import org.acme.dto.UserDTO;
import org.acme.model.Pageable;
import org.acme.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.faulttolerance.Fallback;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Path("/users")
public class UserController {

    @Inject
    private UserService userService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUser(@PathParam("id") String id) {
        Preconditions.checkArgument(StringUtils.isNotBlank(id), "ID must be provided");
        return userService.getUser(UUID.fromString(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Fallback(fallbackMethod = "fallbackGetAllUsers")
    public List<UserDTO> getAllUsers() {
        Pageable pageable = new Pageable(0, 10, "login", Pageable.SortDirection.DESC);
        return userService.getAllUsers(pageable);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid UserDTO userDTO) throws IOException {
        UserDTO dto = userService.save(userDTO);
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    public List<UserDTO> fallbackGetAllUsers() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.randomUUID());
        userDTO.setLogin("Fallback");

        return Collections.singletonList(userDTO);
    }
}

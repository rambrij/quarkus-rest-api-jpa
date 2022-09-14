package com.javacoderhint.api;

import com.javacoderhint.model.Employee;
import com.javacoderhint.service.EmployeeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Map;

@Path("/employees")
public class EmployeeResource {
    @Inject
    EmployeeService employeeService;

    @GET
    public List<Employee> findAll(){
        return employeeService.getEmployees();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        try {
            final var employeeByIdOpt = employeeService.findEmployeeById(id);
            if (employeeByIdOpt.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(employeeByIdOpt.get()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    public Response create(Employee employee) {
        employeeService.create(employee);
        return Response.created(URI.create("/persons/" + employee.getId())).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, Employee employee) {
        try {
            return Response.ok(employeeService.update(id, employee)).build();
        } catch (Exception e) {
            if (e instanceof WebApplicationException) {
                return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", e.getMessage())).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        var isDeleted = employeeService.delete(id);
        if (!isDeleted) {
            return Response.notModified().build();
        }
        return Response.noContent().build();
    }
}

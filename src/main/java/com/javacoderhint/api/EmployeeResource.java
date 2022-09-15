package com.javacoderhint.api;

import com.javacoderhint.model.Employee;
import com.javacoderhint.repository.EmployeeRepository;
import com.javacoderhint.service.EmployeeService;
import io.smallrye.common.constraint.NotNull;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
public class EmployeeResource {

    @Inject
    EmployeeRepository employeeRepository;

    @GET
    public List<Employee> getAll() {
        return this.employeeRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        return this.employeeRepository.findByIdOptional(id)
                .map(emp -> Response.ok(emp).build())
                .orElseGet(() -> Response.status(Status.NOT_FOUND).build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Employee post(@NotNull @Valid Employee employee) {
        this.employeeRepository.persist(employee);
        return employee;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response put(@PathParam("id") long id, @NotNull @Valid Employee employee) {
        return this.employeeRepository.findByIdOptional(id)
                .map(emp -> {
                    emp.setId(id);
                    emp.setName(employee.getName());
                    emp.setRole(employee.getRole());
                    emp.setAge(employee.getAge());
                    this.employeeRepository.persist(emp);
                    return Response.ok(emp).build();
                })
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        return this.employeeRepository.findByIdOptional(id)
                .map(emp -> {
                    this.employeeRepository.delete(emp);
                    return Response.noContent().build();
                })
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }
}

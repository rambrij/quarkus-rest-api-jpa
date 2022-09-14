package com.javacoderhint.service;

import com.javacoderhint.model.Employee;
import com.javacoderhint.repository.EmployeeRepository;
import com.javacoderhint.utility.LogEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmployeeService {
    @Inject
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.listAll();
    }

    public Optional<Employee> findEmployeeById(long id) {
        return employeeRepository.findByIdOptional(id);
    }

    @Transactional
    @LogEvent
    public void create(Employee employee) {
        employeeRepository.persist(employee);
    }

    @Transactional
    public Employee update(long id, Employee employee) {
        employee.setId(id);
        return employeeRepository.update(employee).orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @Transactional
    public boolean delete(long id) {
        return employeeRepository.deleteById(id);
    }

}

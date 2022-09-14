package com.javacoderhint.repository;

import com.javacoderhint.model.Employee;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {

    //extra methods

    public Optional<Employee> update(Employee employee) {
        final var id = employee.getId();
        var savedOpt = this.findByIdOptional(id);
        if (savedOpt.isEmpty()) {
            return Optional.empty();
        }

        var saved = savedOpt.get();
        saved.setName(employee.getName());
        saved.setAge(employee.getAge());
        saved.setRole(employee.getRole());

        return Optional.of(saved);
    }

}

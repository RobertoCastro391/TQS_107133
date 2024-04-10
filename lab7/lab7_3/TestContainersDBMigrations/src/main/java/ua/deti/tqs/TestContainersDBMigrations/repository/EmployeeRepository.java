package ua.deti.tqs.TestContainersDBMigrations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.deti.tqs.TestContainersDBMigrations.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findById(long id);
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName);
    Employee findByEmail(String email);
    List<Employee> findByDepartment(String department);
    List<Employee> findByJobTitle(String jobTitle);

}
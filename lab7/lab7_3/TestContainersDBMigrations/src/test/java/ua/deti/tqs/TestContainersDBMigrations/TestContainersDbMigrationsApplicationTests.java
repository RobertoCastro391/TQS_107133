package ua.deti.tqs.TestContainersDBMigrations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.deti.tqs.TestContainersDBMigrations.entities.Employee;
import ua.deti.tqs.TestContainersDBMigrations.repository.EmployeeRepository;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@SpringBootTest
class TestContainersDbMigrationsApplicationTests {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Container
	public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
			.withDatabaseName("test")
			.withUsername("test")
			.withPassword("test");

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.username", container::getUsername);
		registry.add("spring.datasource.password", container::getPassword);
	}

	@Test
	@Order(1)
	public void testCreateEmployee() {
		Employee employee = new Employee();
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employee.setEmail("johnDoe@ua.pt");
		employee.setDepartment("IT");
		employee.setJobTitle("Developer");

		employeeRepository.save(employee);
	}

	@Test
	@Order(2)
	public void getEmployee_ByFirstName() {
		List<Employee> employees = employeeRepository.findByFirstName("John");
		assertTrue(employees.size() == 1);
	}

	@Test
	@Order(3)
	public void getEmployee_ByLastName() {
		List<Employee> employees = employeeRepository.findByLastName("Doe");
		assertTrue(employees.size() == 1);
	}

	@Test
	@Order(4)
	public void getEmployee_findByEmail() {
		Employee employee = employeeRepository.findByEmail("johnDoe@ua.pt");
		assertEquals("johnDoe@ua.pt",employee.getEmail());
	}

	@Test
	@Order(5)
	public void getEmployee_findByDepartment() {
		List<Employee> employees = employeeRepository.findByDepartment("IT");
		assertTrue(employees.size() == 1);
	}

	@Test
	@Order(6)
	public void getEmployee_findByJobTitle() {
		List<Employee> employees = employeeRepository.findByJobTitle("Developer");
		assertTrue(employees.size() == 1);
	}
}
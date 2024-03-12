# Lab3_1 Employee Manager - Answers

## Ex3.1 

### a) Identify a couple of examples that use AssertJ expressive methods chaining.

A couple of examples that use AssertJ expressive methods chaining are: 
* In class **_A_EmployeeRepositoryTest.java_**:
    * _assertThat(fromDb).isNotNull()_;
    * _assertThat(fromDb.getEmail()).isEqualTo(emp.getEmail())_;

* In class **_B_EmployeeService_UnitTest.java_**:
    * _assertThat(found.getName()).isEqualTo(name)_;

* In class **_D_EmployeeRestControllerIT.java_**:
    * _assertThat(found).extracting(Employee::getName).containsOnly("bob")_; 
    
* In class **_E_EmployeeRestControllerTemplateIT.java_**:
    * _assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK)_;
    * _assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex")_;



### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

An example that mocks the behavior of the of the repository is in class _*B_EmployeeService_UnitTest.java*_ where the *employeeRepository* is marked with the **@Mock** annotation. In the _*setUp()*_ method: 
* _Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john)_;


### c) What is the difference between standard @Mock and @MockBean?

@Mock and @MockBean both create mock object but they have different purposes:
* @Mock is part of the Mockito framework, used to create mock objects to isolate the code under test by simulating the behavior of the real objects. It is used in unit test to replace the dependecies of the class under test with controlled implementations.

* @MockBean is part of the Spring Boot testing framework, used specifically in the context of integration testing with Spring Boot applications. Used to mock Spring beans when testing Spring components such as controllers, services, or repositories. Used for generic mocking in unit tests, @MockBean is Spring-aware and is intended for mocking Spring beans within the Spring application context.

To conclude, @Mock is used for creating mock objects in unit tests with Mockito, while @MockBean is used for mocking Spring beans in integration tests within the Spring context.


### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

The file **_application-integrationtest.properties_** plays a role in configuring properties specifically for integration tests in a Spring Boot application. Its role and usage are as follows:

* Configuration for Integration Tests:
    * Integration tests often require different configurations compared to unit tests or production environments, like configurations might include database connection settings, external service endpoints, logging levels, etc.

The application-integrationtest.properties file allows developers to define such configuration properties specifically tailored for integration tests.


### e) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

The strategy **C** (**_C_EmployeeController_WithMockServiceTest.java_**) uses unit testing the controller layer with mocked dependencies. It uses **@WebMvcTest** to test the **_EmployeeRestController_** mocking the **_EmployeeService_**. Both strategies **D** and **E** (**_D_EmployeeRestControllerIT.java_**, **_E_EmployeeRestControllerTemplateIT.java_**) use the annotation **@SpringBootTest** focusing on integration testing the controller layer with real or simulated HTTP requests, involving the complete Spring application context. The main difference between these two is the approach used for making HTTP requests: one uses MockMvc directly, while the other uses TestRestTemplate.
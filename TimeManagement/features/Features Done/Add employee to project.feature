Feature: Add employee to project

Description: Employees can add  employees from projects that they are project managers on; 
    trying to add employees from projects they are not project 
    managers on will result in an error message. You cannot add an employee twice.

Scenario: Project manager adds employee to project
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And an employee is registered as project manager of the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    Then the employee is added to the project

Scenario: Employee adds employee to project
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee is not registered as project manager of the project
    When the employee adds another employee to the project
    Then the error message "Not logged in as project manager" is given

Scenario: Project manager adds employee to project that employee is already in
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And a second employee is registered with TimeManagement 
    And an employee is registered as project manager of the project
    When the project manager adds an employee to the project
    Then the employee is added to the project
    When the project manager adds an employee to the project
    Then the error message "Employee already added to project" is given
    


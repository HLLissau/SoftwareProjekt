Feature: Set employee to project manager

Description: an employee is set as project manager of a project. 
	Actor: employee

Scenario: An employee is set as project manager to an existing project 
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    When the employee registers as project manager
    Then the employee is registered as the project manager
    And the project manager is added to the project
    

Scenario: A project manager is unregistered from a project
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    When the employee registers as project manager
    And the Employee is unregistered as project manager 
    Then the Employee is no longer project manager 

Scenario: only project manager can unregisterer project manager
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    When the employee registers as project manager
    Given another employee is logged in
    When the Employee is unregistered as project manager 
    Then the error message "Not logged in as project manager" is given

Scenario: need to be project manager to set a new project manager
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    When the employee registers as project manager
    Then the employee is registered as the project manager
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    When the employee registers a new project manager
    Then the Employee is no longer project manager 
    When the employee registers a new project manager
    Then the error message "Not logged in as project manager" is given

Scenario: need to be project manager to register a project manager
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    When the employee registers as project manager
    Then the employee is registered as the project manager
    And a second employee is registered with TimeManagement
    When the project manager adds an employee to the project
    When the employee registers a new project manager
    Then the Employee is no longer project manager
    When the employee registers as project manager
    Then the error message "Not logged in as project manager" is given
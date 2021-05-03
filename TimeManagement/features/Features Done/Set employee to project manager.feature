Feature: Set employee to project manager

Description: an employee is set as project manager of a project. 
	Actor: employee

Scenario: An employee is set as project manager to an existing project 
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    When the employee is registered as project manager
    Then the employee is registered as the project manager

Scenario: A project manager is unregistered from a project
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And an employee is registered as project manager of the project
    When the Employee is unregistered as project manager 
    Then the Employee is no longer project manager 

Scenario: need to be project manager to unregister a project manager
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee is not registered as project manager of the project
    When the Employee is unregistered as project manager 
    Then the error message "Not logged in as project manager" is given
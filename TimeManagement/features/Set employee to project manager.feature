Feature : Set employee to project manager

Description: an employee is set as project manager of a project. 
	Actor: employee

Scenario: An employee is set as project manager to an existing project 
    Given a project is in TimeManagement
    And an employee is registered
    When the employee is registered as project manager
    Then the employee is registered as project manager

Scenario: A project manager is unregistered from a project
    Given a project is in TimeManagement
    And an employee is registered
    And the employee is registered as project manager of the project
    When the Employee is unregistered as project manager 
    Then the Employee is unregistered as project manager 

Scenario: need to be project manager to unregister a project manager
    Given a project is in TimeManagement
    And an employee is registered
    And the employee is not registered as project manager of the project
    When the employee tries to unregister a project manager 
    Then the error message "Only project managers can remove project managers" is given
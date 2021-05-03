Feature: Unregister employee from TimeManagement

Description: The administrator unregisters an employee from TimeManagement.
    Actor: Administrator

Scenario: delete employee from TimeManagement
    Given an employee is registered with TimeManagement
    And that the administrator is logged in
    When the administrator deletes the employee from TimeManagement
    Then the employee is not registered in TimeManagement

Scenario: Need to be administrator to remove employees from TimeManagement
    
    Given an employee is registered with TimeManagement
    And that the administrator is not logged in
    When the administrator deletes the employee from TimeManagement
    Then the employee is still registered with TimeManagement
    And the error message "Administrator login required" is given

Scenario: delete employee not in TimeManagement
    Given an employee is registered with TimeManagement
    And that the administrator is logged in
    When the administrator deletes the employee from TimeManagement
    Then the employee is not registered in TimeManagement
    When the administrator deletes the employee from TimeManagement
    And the error message "Employee not found in TimeManagement" is given

Scenario: delete employee who is working on activities
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And an employee is registered as project manager of the project
    And a activity is in the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    Then the employee is added to the project
    When the project manager adds an employee to an activity
    Then the employee is added to the activity
    Given that the administrator is logged in
    When the administrator deletes the employee from TimeManagement
    Then the employee is still registered with TimeManagement
    And the error message "Employee is working on activity" is given
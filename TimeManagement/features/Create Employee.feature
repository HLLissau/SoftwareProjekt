Feature: Register employee to TimeManagement
Description: The administrator registers a new employee to TimeManagement.
Actor: Administrator

Scenario: Add employee to TimeManagement
    Given that the administrator is logged in
    And there is a user with name "Jens Hansen", email "JHansen@awesomefirm.dk"
    When the administrator registers the employee in TimeManagement
    Then the employee is registered in TimeManagement
    And the employee is given the employee ID "123456" 

Scenario: Add employee when not the administrator
    Given that the administrator is not logged in
    And there is a user with name "Jens Hansen", email "JHansen@awesomefirm.dk"
    When the administrator registers the employee
    Then the error message "Administrator login required" is given

Scenario: employee already registered 
    Given an employee is registered with TimeManagement
    And the administrator is logged in
    When the administrator registers the employee again
    Then the error message "Employee is already registered" is given
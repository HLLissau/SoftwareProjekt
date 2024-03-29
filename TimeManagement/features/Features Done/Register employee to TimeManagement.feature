Feature: Register employee to TimeManagement
Description: The administrator registers a new employee to TimeManagement.
Actor: Administrator

Scenario: Add employee to TimeManagement
    Given that the administrator is logged in
    And there is a user with first name "Jens" , last name "Hansen" , email "JHansen@awesomefirm.dk"
    When the administrator registers the employee in TimeManagement
    Then the employee is given a unique id
    And the employee is registered in TimeManagement
 
Scenario: Administrator tries to login with wrong password
    Given that the administrator is not logged in
    When the administrator types the wrong password
    Then the administrator is not logged in

Scenario: Add employee when not the administrator
    Given that the administrator is not logged in
    And there is a user with first name "Jens" , last name "Hansen" , email "JHansen@awesomefirm.dk"
    When the administrator registers the employee in TimeManagement
    Then the error message "Administrator login required" is given

Scenario: employee already registered 
    Given that the administrator is logged in
    And there is a user with first name "Jens" , last name "Hansen" , email "JHansen@awesomefirm.dk"
    When the administrator registers the employee in TimeManagement
    Then the employee is given a unique id
    And the employee is registered in TimeManagement
    When the administrator registers the employee in TimeManagement
    Then the error message "Employee is already registered" is given
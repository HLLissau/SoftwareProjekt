
Feature: Get time spent on activities
Description: Getting time from activities. Used by managers, and system primarily to get overview.
  Actor: employee

 
  Scenario: An employee tries to get time on activity not in timeManagement
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    And the Employee check the time of the activity
    Then the error message "Activity not found" is given
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

#Scenario: delete employee who is working on activities
    #Given a project exists and a project manager is logged in
    #And a second employee is registered with TimeManagement
    #And the employee is assigned to an activity
    #And that the administrator is logged in
    #When the administrator deletes the employee from TimeManagement
    #Then the employee is still registered with TimeManagement
    #And the error message "Employee is working on activity" is given

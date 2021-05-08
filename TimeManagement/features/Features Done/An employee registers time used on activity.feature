Feature: Add / remove employee from project
Description: Employees can add / remove employees from projects that they are project managers on; 
    trying to add / remove employees from projects they are not project 
    managers on will result in an error message. You cannot add an employee twice.

Scenario: an employee register time on activity
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a activity is in the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    And the project manager adds an employee to an activity
    Then the employee is added to the activity
    When the employee begin work on the activity
    Then the begin time is set and the begun activity is in begun activity List
    Given 30 minutes have passed
    When the employee end work on the activity
    And the Employee check the time of the activity
    Then the activity consumed time is increased by 30
    Then the begin time is set and the begun activity is in begun activity List
    
Scenario: an employee register time tries to start same activity twice
		Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a activity is in the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    And the project manager adds an employee to an activity
    Then the employee is added to the activity
    When the employee begin work on the activity
    Then the begin time is set and the begun activity is in begun activity List
    When the employee begin work on the activity
    Then the error message "Employee already working on the activity" is given
    

Scenario: an employee register time on activity they are not working on
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a activity is in the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    When the employee begin work on the activity
    Then the error message "Activity not found" is given

Scenario: an employee ends work on activity they are not working on
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a activity is in the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    When the employee end work on the activity
    Then the error message "Employee not working on the activity" is given
   

Feature: remove employe from from activity
Actor: employee, project manager

 
Scenario: Project manager removes employee from activity
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a activity is in the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    And the project manager adds an employee to an activity
    Then the employee is added to the activity
    When the project manager removes the employee from the activity
    Then the employee is removed from the activity
    
    
Scenario: Project manager removes employee from activity they are not working on
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a activity is in the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    And the project manager adds an employee to an activity
    Then the employee is added to the activity
    When the project manager removes the employee from the activity
    Then the employee is removed from the activity
    When the project manager removes the employee from the activity
	  Then the error message "Employee not found in activity" is given
 

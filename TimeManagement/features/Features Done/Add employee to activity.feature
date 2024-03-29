Feature: Add employee to activity

Description: A project manager can add an employee to an activity

Scenario: Project manager adds employee to activity
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a activity is in the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    And the project manager adds an employee to an activity
    Then the employee is added to the activity

Scenario: Employee adds employee to activity
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a activity is in the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    And the second employee adds an employee to an activity
    Then the error message "Not logged in as project manager" is given
    

Scenario: Project manager adds employee to activity that employee is already in
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a activity is in the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    And the project manager adds an employee to an activity
    Then the employee is added to the activity
    When the project manager adds an employee to an activity
    Then the error message "Employee already added to the activity" is given
    
Scenario: Project manager adds employee to activity that is not in project
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    And the project manager adds an employee to an activity
    Then the error message "Activity not found in project" is given
    

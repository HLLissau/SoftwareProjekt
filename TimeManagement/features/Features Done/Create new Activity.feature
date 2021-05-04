Feature: Create new Activity

Description: a new activity is created in TimeManagement
Actor: Project manager

Scenario: Create a new activity
    Given an employee is registered with TimeManagement
    When a new activity with name "new activity" is created
    And the employee adds the activity to timeManagement
    Then a activity with the name "new activity" is in TimeManagement

Scenario: Create a new activity that is already in timeManagement
    Given an employee is registered with TimeManagement
    When a new activity with name "new activity" is created
    And the employee adds the activity to timeManagement
    Then a activity with the name "new activity" is in TimeManagement
    And the employee adds the activity to timeManagement
    And the error message "Activity is already registered" is given 


Scenario: set a new activity in project
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And an activity with the name "new activity" is in TimeManagement
    When the employee adds the activity to the project
    Then a activity with the name "new activity" is in TimeManagement

Scenario: set a new activity when not project manager
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And an employee is not registered as project manager of the project
    And an activity with the name "new activity" is in TimeManagement
    When the employee adds the activity to the project
    And the error message "Not logged in as project manager" is given 

Scenario: Set time of activity
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And an activity with the name "new activity" is in TimeManagement
    And the employee adds the activity to the project
    When the employee sets the time of the activity to 10
    Then the time of the activity is set to 10
   
Scenario: Set time of activity when not project manager
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And an activity with the name "new activity" is in TimeManagement
    When the employee adds the activity to the project
    And another employee is logged in
    When the employee sets the time of the activity to 10
    Then the time of the activity is not set to 10
    And the error message "Not logged in as project manager" is given
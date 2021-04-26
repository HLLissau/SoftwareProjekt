Feature: Create new Activity

Description: a new activity is created in TimeManagement
Actor: Project manager

Scenario: Create a new activity
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And an employee is registered as project manager of the project
    When a new activity with name "new activity" is created
    And the employee adds the activity to the project
    Then a activity with the name "new activity" is in TimeManagement

Scenario: Create a new activity when not project manager
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And an employee is not registered as project manager of the project
    When a new activity with name "new activity" is created
    And the employee adds the activity to the project
    And the error message "Not logged in as project manager" is given 

Scenario: Set time of activity
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And an employee is registered as project manager of the project
    And an activity with the name "new activity" is in TimeManagement
    And the employee adds the activity to the project
    When the employee sets the time of the activity to 10
    Then the time of the activity is set to 10
   
Scenario: Set time of activity when not project manager
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And an employee is registered as project manager of the project
    And an activity with the name "new activity" is in TimeManagement
    When another employee is logged in who is not project manager
    And the employee adds the activity to the project
    When the second employee sets the time of the activity to 10
    Then the time of the activity is not set to 10
    And the error message "Not logged in as project manager" is given
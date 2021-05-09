Feature: Add / remove activity from project

Description: Employees can add / remove an activities on projects they are project managers on; 
    if they are not project managers for the project, an error message is thrown.

Scenario: Project manager adds activity to project
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And an activity with the name "new activity" is in TimeManagement
    When the employee adds the activity to the project
    Then the activity is added to the project
    
Scenario: Project manager adds activity to project twice
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And an activity with the name "new activity" is in TimeManagement
    When the employee adds the activity to the project
    Then the activity is added to the project
    When the employee adds the activity to the project
    Then the error message "Activity already added to project" is given
     
    
Scenario: Project manager removes activity from project
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And an activity with the name "new activity" is in TimeManagement
    When the employee adds the activity to the project
    Then the activity is added to the project
    When the employee removes the activity from the project
    And the activity is not in the project
    
Scenario: Project manager removes activity that doesn't exist from project
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And an activity with the name "new activity" is in TimeManagement
    And the employee removes the activity from the project
    And the employee removes the activity from the project  
    Then the error message "Activity is not in project" is given

Scenario: Employee want to work on acivity not in TimeManagent
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    When want to get activity
    Then no activity is returned
    
Scenario: Employee adds activity to project
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And an activity with the name "new activity" is in TimeManagement
    When the employee adds the activity to the project
    Then the error message "Not logged in as project manager" is given
    And the activity is not in the project

Scenario: Employee removes activity from project
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And an activity with the name "new activity" is in TimeManagement
    When the employee adds the activity to the project
    Then the activity is added to the project
    When another employee is logged in
    And the employee removes the activity from the project
    Then the error message "Not logged in as project manager" is given
    Then the activity is added to the project


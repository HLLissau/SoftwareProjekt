Feature : Add / remove activity from project

Description: Employees can add / remove an activities on projects they are project managers on; 
    if they are not project managers for the project, an error message is thrown.

Scenario: Project manager adds activity to project
    Given a project is in TimeManagement
    And an employee is registered
    And the employee is registered as project manager of the project
    When the project manager adds an activity with name "some activity" and ID "some activity ID" 
    to the project
    Then the activity is added to the project

Scenario: Project manager removes activity from project
    Given a project is in TimeManagement
    And an employee is registered
    And the employee is registered as project manager of the project
    And an activity with name "some activity" and ID "some activity ID" in the project
    When the employee removes the activity
    Then the activity with name "some activity" and ID "some activity ID" is removed

Scenario: Project manager removes activity that doesn't exist from project
    Given a project is in TimeManagement
    And an employee is registered
    And the employee is registered as project manager of the project
    And an activity with name "some activity" and ID "some activity ID" that is not in the project
    When the employee removes the activity
    Then the error message "Cannot remove activities that does not exist in project" is shown
    And the activity with name "some activity" and ID "some activity ID" is not

Scenario: Employee adds activity to project
    Given a project is in TimeManagement
    And an employee is registered
    And the employee is not registered as project manager of the project
    When the employee adds an activity with name "some activity" and ID "some activity ID" to 
    the project
    Then the error message "Only project managers can add activities to projects" is given
    And the activity is not added to the project

Scenario: Employee removes activity from project
    Given a project is in TimeManagement
    And an employee is registered
    And the employee is not registered as project manager of the project
    And an activity with name "some activity" and ID "some activity ID" in the project
    When the employee removes the activity
    Then the activity with name "some activity" and ID "some activity ID" is removed


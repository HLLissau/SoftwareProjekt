Feature: Getting lists from TimeManagement
  Actor: Employee, project Manager

  @tag1
  Scenario: Employee needs a list of all employees
  	Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    When the employee wants to get a list of all employee in TimeManagement
    Then the list of all employees are returned.
    
  Scenario: Employee needs a list of all projects
    And an employee is registered with TimeManagement
    When the employee wants to get a list of all projecs in TimeManagement
    Then the list of all projecs are returned.

  Scenario: a project manager needs a list of all activities in project
		Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And an activity with the name "new activity" is in TimeManagement
    When the employee adds the activity to the project
    Then the activity is added to the project
    When the project manager wants to get a list of all activities in project
    Then the list of all activities are returned
    
  Scenario: a project manager needs a list of all employees working on activity
		Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a activity is in the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    And the project manager adds an employee to an activity
    Then the employee is added to the activity
    When the project manager wants to get a list of all employees working on activity
    Then the list of all employees working on activity are returned
    
   Scenario: a project manager needs a list of all activities an employee is working on
		Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a activity is in the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    And the project manager adds an employee to an activity
    Then the employee is added to the activity
    When the project manager wants to get a list of all activities an employee is working on
    Then the list of all all activities an employee is working on is returned
    
    Scenario: a project manager needs a list of all projects an employee is working on
		Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And the employee registers as project manager
    And a activity is in the project
    And a second employee is registered with TimeManagement 
    When the project manager adds an employee to the project
    And the project manager adds an employee to an activity
    Then the employee is added to the activity
    When the project manager wants to get a list of all projects an employee is working on
    Then the list of all all projects an employee is working on is returned
    
    
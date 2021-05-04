Feature: Edit project

Description: The project manager edits a project
Actors: A project manager

Scenario: set project description
    Given an employee is registered with TimeManagement
    And a project is in TimeManagement
    And an employee is registered as project manager of the project
    When the employee edits the description to "new description"
    Then the project description is "new description"
    
Scenario: employee cant set project description 
    Given an employee is registered with TimeManagement
    And a project is in TimeManagement
    When the employee edits the description to "new description"
    Then the error message "Not logged in as project manager" is given
    
Scenario: a project manager edits time of project
    Given an employee is registered with TimeManagement
	  And a project is in TimeManagement
	  And an employee is registered as project manager of the project
    When the employee sets the project time to 10 
    Then the activity time is set to 10
    
Scenario: an employee edits time of project
    Given an employee is registered with TimeManagement
	  And a project is in TimeManagement
	  When the employee sets the project time to 10 
    Then the error message "Not logged in as project manager" is given
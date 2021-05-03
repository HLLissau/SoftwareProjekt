Feature: Edit project

Description: The project manager edits a project
Actors: A project manager

Scenario: set project description
    Given an employee is registered with TimeManagement
    And a project is in TimeManagement
    And an employee is registered as project manager of the project
    When the project manager edits the description to "new description"
    Then the project description is "new description"
    
Scenario: a project manager edits time of project
    Given an employee is registered with TimeManagement
	  And a project is in TimeManagement
	  And an employee is registered as project manager of the project
    When the project manager sets the project time to 10 
    Then the activity time is set to 10
#Feature: Edit activity
#
#Description: The project manager edits an activity
#Actors: A project manager
#
#Scenario: set activity description
#    And an employee is registered
#    And an employee is registered as project manager of the project
#    And a project is in TimeManagement
#    When the project manager edits the description to "new description"
#    Then the project description is "new description"
#    
#Scenario: a project manager edits time of activity
#    And an employee is registered
#	And the employee is registered as project manager of the project
#    And a project is in TimeManagement
#    When the project manager sets the activity time to "10" 
#    Then the activity time is set to "10"
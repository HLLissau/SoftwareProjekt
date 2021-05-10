Feature: Check employee status

Description: a project manager needs to assign a project to certain employees and checks for their availability
    Actors: a project manager
        
Scenario: a project manager needs a list of employees on less than 10 acitivities
    Given a project exists and a project manager is logged in
    When the project manager requests a list of employees with under 10 activitties
   	Then system returns a list of  employees with less than 10 activities.

        
Scenario: a project manager needs a list of employees on the specific project
    Given a project exists and a project manager is logged in
    And a project have 10 employees added
    When the project manager requests  a list of employees on the specific project
    Then system returns a list of employees with the 10 added employees


    
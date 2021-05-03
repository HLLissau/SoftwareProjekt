Feature: Check employee status

Description: a project manager needs to assign a project to certain employees and checks for their availability
    Actors: a project manager
        
Scenario: a project manager needs a list of employees on less than 10 acitivities
    Given a project exists and a project manager is logged in
    When the project manager requests a list of employees with under 10 activitties
   	Then system returns a list of  employees with less than 10 activities.

        
#Scenario: a project manager needs a list of employees on less than 20 acitivities
    #Given a project manager is logged in
    #When the project manager requests the list
    #The system checks all employees in the system and their availability
    #When the system finds all employees with < 20 projects assigned to them
    #The system returns a list of those employees.
        #
#Scenario: a project manager needs a list of all employees
#    Given a project manager is logged in
#    When the project manager requests the list
#    The system returns a list of all employees.
#        
#Scenario: a project manager needs a list of employees on the specific project
#    Given a project manager is logged in
#    And the project manager is assigned to the specific project
#    When the project manager requests the list
#    The system checks the certain project for employees
#    When the system finds all employees within the project
#    The system returns a list of those employees, and their time spent on the project

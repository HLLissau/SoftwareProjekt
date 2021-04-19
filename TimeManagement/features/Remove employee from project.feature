Feature: remove employee from project

Description: Employees can remove employees from projects that they are project managers on; 
    trying to remove employees from projects they are not project 
    managers on will result in an error message.


#Scenario: Project manager removes employee from project
#    Given a project is in TimeManagement
#    And an employee is registered
#    And the employee is registered as project manager of the project
#    And another employee with name "some name", email "some email" and ID "some ID" is
#    registered with the project
#    When the project manager removes the employee from the project
#    Then the employee with name "some name", email "some email" and  ID "some ID" is not 
#    registered with the project
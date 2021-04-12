#Feature : Add / remove employee from project
#Description: Employees can add / remove employees from projects that they are project managers on; 
#    trying to add / remove employees from projects they are not project 
#    managers on will result in an error message. You cannot add an employee twice.
#
#Scenario: Project manager adds employee to project
#    Given a project is in TimeManagement
#    And an employee is registered
#    And the employee is registered as project manager of the project
#    When the project manager adds an employee to the project
#    Then the employee is added to the project
#
#Scenario: Employee adds employee to project
#    Given a project is in TimeManagement
#    And an employee is registered
#    And the employee is not registered as project manager of the project
#    When the employee adds another employee to the project
#    Then the error message "Only project managers can add employees to projects" is given
#
#Scenario: Project manager adds employee to project that employee is already in
#    Given a project is in TimeManagement
#    And an employee is registered
#    And the employee is registered as project manager of the project
#    And another employee with name "some name", email "some email" and ID "some ID" is 
#    registered with the project
#    When the project manager adds the employee to the project
#    Then the error "Employee already added to project" is given
#        And the employee is not added to the project
#
#Scenario: Project manager removes employee from project
#    Given a project is in TimeManagement
#    And an employee is registered
#    And the employee is registered as project manager of the project
#    And another employee with name "some name", email "some email" and ID "some ID" is
#    registered with the project
#    When the project manager removes the employee from the project
#    Then the employee with name "some name", email "some email" and  ID "some ID" is not 
#    registered with the project
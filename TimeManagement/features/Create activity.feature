Feature: Create new project

Description: A new project is created

Scenario: Administrator creates new project
    Given that the administrator is logged in
    When a project named "some project" is created
    Then a project named "some project" exists in TimeManagement

#Scenario: Non-administrator creates new project
#    Given that the administrator is not logged in
#    When a project named "some project" is created
#    Then the error "Administrator access required" is given
#    And a project named "some project" exists in TimeManagement
#
#Scenario: Project is assigned unique ID on creation
#    Given that the administrator is logged in
#    When a project named "some project" is created
#    Then an ID is assigned to the project
#    And no other project has the same ID
#
#Scenario: Project is not assigned unique ID on creation
#    Given that the administrator is logged in
#    When a project named "some project" is created
#    And an ID is assigned to the project
#    And another project has the same ID
#    Then the error "UID generation failed" is given
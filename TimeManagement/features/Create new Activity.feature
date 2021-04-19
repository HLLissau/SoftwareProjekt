Feature: Create new Activity

Description: a new activity is created in TimeManagement
Actor: Project manager

Scenario: Create a new activity
    Given a project is in TimeManagement
    And an employee is registered with TimeManagement
    And an employee is registered as project manager of the project
    When a new activity with name "new activity" is created
    Then a activity with the name "new activity" is in TimeManagement


#Scenario: Set time of activity
#    Given a project is in TimeManagement
#    And an employee is registered
#    And an employee is registered as project manager of the project
#    And an activity with the name "new activity" is in TimeManagement
#    When the Project manager sets the time of the activity to "10"
#    Then the time of the activity is set to "10"
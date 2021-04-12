#Feature : Add vacation/sick days to employee
#
#Description: A project manager needs to give an employee a vacation/sick day
#    Actor: administrator, project manager and employee
#    
#Scenario: An employee aske for permission from administrator for vacation/sick and is accepted
#    Given an employee is registered with TimeManagement
#    When an employee asks for permission from administrator for vacation/sick day
#    Then the administrator is alerted 
#    When the administrator accepts 
#    Then the employee is given a vacation/sick day
#            
#Scenario: An employee aske for permission from administrator for vacation/sick and is declined
#    Given an employee is registered with TimeManagement
#    When an employee asks for permission from administrator for vacation/sick day
#    Then the administrator is alerted 
#    When the administrator decline 
#    Then the employee is not given a vacation/sick day and needs to keep working
#    
#Scenario if a administrator wants to report a sick/vation day
#    Given the administrator is logged in
#    when the administrator want a day off
#    then the administrator gets the day off
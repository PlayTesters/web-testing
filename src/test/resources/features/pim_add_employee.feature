#Feature: PIM Add Employee
#  As an HR user, I want to add an employee and navigate to the employee list.
#
#  Scenario: Add an employee and view the employee list
#    Given I am logged into the OrangeHRM system
#    When I navigate to the PIM module
#    And I click the Add button in PIM
#    And I fill in the employee details:
#      | Field        | Value         |
#      | First Name   | John          |
#      | Last Name    | Doe           |
##      | Employee ID  | 123456        |
#    And I click Save in Add Employee
#    Then I navigate to the Employee List

#Feature: PIM Add Employee and Manage Actions
#  As an HR personnel
#  I want to add a new employee, view the employee list, and delete an employee
#  So that I can manage employee data effectively.
#
#  Scenario: Add a new employee and delete from the list
#    Given I am logged into the OrangeHRM system
#    When I navigate to the PIM module
#    And I click the Add button in PIM
#    And I fill in the employee details:
#      | Field         | Value       |
#      | First Name    | John        |
#      | Last Name     | Doe         |
##      | Employee ID   | 12345       |
#    And I click Save in Add Employee
#    And I click the Employee List in the menu bar
#    And I delete the first displayed employee


Feature: PIM Add Employee and Manage Actions
  As an HR personnel
  I want to add a new employee, view the employee list, and delete an employee
  So that I can manage employee data effectively.

  Scenario: Add a new employee
    Given I am logged into the OrangeHRM system
    When I navigate to the PIM module
    And I click the Add button in PIM
    And I fill in the employee details:
      | Field         | Value       |
      | First Name    | John        |
      | Last Name     | Doe         |
    And I click Save in Add Employee

  Scenario: Delete the first displayed employee
    Given I am logged into the OrangeHRM system
    When I navigate to the PIM module
    And I click the Employee List in the menu bar
    And I delete the first displayed employee

  Scenario: Add employee without filling details (Negative Scenario)
    Given I am logged into the OrangeHRM system
    When I navigate to the PIM module
    And I click the Add button in PIM
    And I click Save in Add Employee

Feature: Manage Candidates
  As an admin
  I want to manage candidates in the recruitment system
  So that I can add, shortlist and delete candidate


  Scenario: Add, shortlist, and save a candidate
  Given I am logged into the OrangeHRM system
  When I navigate to the Recruitment module
  And I click the Add button in Recruitment
  And I fill in the candidate details:
  | Field       | Value                 |
  | First Name  | Sugath                |
  | Middle Name | Hasi                  |
  | Last Name   | Sugatha               |
  | Email       | sugath@example.com    |
  And I select a vacancy by index 2
  And I click Save in Add Candidate
  And I shortlist the candidate
  And I fill in the notes "Shortlist Candidate" and save

  Scenario: Delete a candidate
    Given I am logged into the OrangeHRM system
    When I navigate to the Recruitment module
    And I delete a candidate

  Scenario: Enter invalid email and see validation error
    Given I am logged into the OrangeHRM system
    When I navigate to the Recruitment module
    And I click the Add button in Recruitment
    And I fill in the candidate details:
      | Field       | Value                 |
      | First Name  | John                  |
      | Last Name   | Doe                   |
      | Email       | a                     |
#    And I click Save in Add Candidate
    Then I should see the email error message "Expected format: admin@example.com"


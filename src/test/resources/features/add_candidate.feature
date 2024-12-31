Feature: Add Candidate
  As an admin
  I want to add candidates to the recruitment system
  So that I can track their progress

  Scenario: Add a new candidate
    Given I am logged into the OrangeHRM system
    When I navigate to the Recruitment module
    And I click the Add button in Recruitment
    And I fill in the candidate details:
      | Field       | Value                |
      | First Name  | Kamal               |
      | Last Name   | Duminda             |
      | Email       | kam.doe@example.com |
    And I click Save in Add Candidate
#    Then I should see a confirmation message "Successfully Saved"

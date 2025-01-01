Feature: Add Candidate
  As an admin
  I want to add candidates to the recruitment system
  So that I can track their progress

  Scenario: Add a new candidate
    Given I am logged into the OrangeHRM system
    When I navigate to the Recruitment module
    And I click the Add button in Recruitment
    And I fill in the candidate details:
      | Field          | Value                |
      | First Name     | Sugath                |
      | Middle Name     | Hasi                |
      | Last Name      | Sugatha              |
      | Email          | suga.doe@example.com  |

    And I select a vacancy by index 2
    And I click Save in Add Candidate
    Then I navigate to the Candidates table



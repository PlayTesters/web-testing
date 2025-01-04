Feature: Job Title Management

  Scenario: Verify a newly added Job Title appears in the Job Titles table
    Given I am logged into the OrangeHRM system
    When I navigate to the Admin module
    And I navigate to Job Titles from the Admin module
    And I click the Add button in Job Titles
    And I fill in the Job Title details:
      | Job Title       | sAccounting apprentice  |
      | Job Description | Responsible for coding |
      | Note            | For Development Team  |
    And I click Save in Job Titles

  Scenario: Delete the first Job Title from the Job Titles table
    Given I am logged into the OrangeHRM system
    When I navigate to the Admin module
    And I navigate to Job Titles from the Admin module
    When I delete the first Job Title
#    Then I should see the job title is deleted from the Job Titles table

  Scenario: Prevent creation of duplicate Job Title
    Given I am logged into the OrangeHRM system
    When I navigate to the Admin module
    And I navigate to Job Titles from the Admin module
    And I click the Add button in Job Titles
    And I fill in the Job Title details:
      | Job Title       | sAccounting apprentice  |
      | Job Description | Responsible for accounting |
      | Note            | For Finance Team         |
    And I click Save in Job Titles
#    Then I should see an error message "Job Title already exists"



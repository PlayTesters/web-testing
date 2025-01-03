Feature: Job Title Management

  Scenario: Add a Education Qualification For Qualifications
    Given I am logged into the OrangeHRM system for Qualifications
    When I navigate to the Admin module
    And I navigate to Qualifications from the Admin module
    And I navigate to Education from the Qualifications menu
    And I click the Add button in Qualifications
    And I fill in the Education details:
      | Level       | Grade 5 scholarship  |
    And I click Save in Education Valid


  Scenario: Add Education without filling details (Negative Scenario)
    Given I am logged into the OrangeHRM system for Qualifications
    When I navigate to the Admin module
    And I navigate to Qualifications from the Admin module
    And I navigate to Education from the Qualifications menu
    And I click the Add button in Qualifications
    And I click Save in Education

  Scenario: Delete the first displayed Education record
    Given I am logged into the OrangeHRM system for Qualifications
    When I navigate to the Admin module
    And I navigate to Qualifications from the Admin module
    And I navigate to Education from the Qualifications menu
    And I click the Delete button for the first displayed Education record


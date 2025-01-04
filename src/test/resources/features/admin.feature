Feature: Add Candidate
  As an admin
  I want to add candidates to the recruitment system
  So that I can track their progress


  Scenario: Delete the third displayed user
    Given I am logged into the OrangeHRM system
    When I navigate to the Admin module
    And I delete the third displayed user
#    And I confirm the deletion in the popup box

  Scenario: Add a new user (Negative Scenario)
    Given I am logged into the OrangeHRM system
    When I navigate to the Admin module
    And I click the Add button in Admin
    And I fill in the newuser details:
      | Field           | Value                |
      | Password        | Ashok@123            |
      | Confirm Password| Ashok@123            |
    And I click Save in Add NewUser

  Scenario: Add a Language Qualification
    Given I am logged into the OrangeHRM system
    When I navigate to the Admin module
    And I navigate to Qualification from the Admin module
    And I navigate to Languages from the Qualifications menu
    And I click the Add button in Languages
    And I fill in the Language details
      | Name       | Singlish   |
    And I click Save in Language





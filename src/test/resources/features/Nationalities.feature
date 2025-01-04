Feature: Nationalities Management

  Scenario: Verify a newly added Nationality appears in the Nationalities table
    Given I am logged into the OrangeHRM system for Nationalities
    When I navigate to the Admin module for Nationalities
#    And I navigate to Nationalities from the Admin module
    And I click the Add button in Nationalities
    And I fill in the Nationality details:
      | Name       | Albanianaa  |
    And I click Save in Nationalities
    And I navigate to Nationalities from the Admin module


  Scenario: Delete first Record in the Nationalities table
    Given I am logged into the OrangeHRM system for Nationalities
    When I navigate to the Admin module for Nationalities
#    And I navigate to Nationalities from the Admin module
    And I delete the first displayed Nationality
Then I should not see the deleted Nationality in the Nationalities table


  Scenario: Attempt to add a Nationality without filling in the form
    Given I am logged into the OrangeHRM system for Nationalities
    When I navigate to the Admin module for Nationalities
#    And I navigate to Nationalities from the Admin module
    And I click the Add button in Nationalities
    And I click Save in Nationalities
    Then I should see a validation message for required fields in Nationalities



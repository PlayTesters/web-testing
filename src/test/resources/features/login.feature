@SmokeFeature
Feature: Login to SwagLabs
  @Smoke
  Scenario: Valid Login
    Given I navigate to the SwagLabs login page
    When I enter username "standard_user" and password "secret_sauce"
    And I click on the login button
    Then I should see the inventory page

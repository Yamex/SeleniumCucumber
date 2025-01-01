Feature: User Registration

  Scenario: Successful registration
    Given I navigate to "https://membership.basketballengland.co.uk/NewSupporterAccount"
    When I fill in the registration form with valid data
    And I submit the registration form
    Then I should see a confirmation message


  Scenario: Skapa användare – efternamn saknas
    Given I navigate to "https://membership.basketballengland.co.uk/NewSupporterAccount"
    When I fill in the registration form without a last name
    And I submit the registration form
    Then I should see an error message indicating that the last name is required

  Scenario: Skapa användare – lösenorden matchar inte
    Given I navigate to "https://membership.basketballengland.co.uk/NewSupporterAccount"
    When I enter valid information except the passwords do not match
    And I submit the registration form
    Then I should see an error message indicating the passwords do not match

  Scenario: Skapa användare – terms and conditions är inte ifyllda
    Given I navigate to "https://membership.basketballengland.co.uk/NewSupporterAccount"
    When I fill in the registration form without accepting terms and conditions
    And I submit the registration form
    Then I should see an error message indicating terms and conditions must be accepted

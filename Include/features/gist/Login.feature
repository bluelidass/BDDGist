@Login
Feature: Login Feature
  As a user, I want to login to Gist Github
  so that I can write a Gist.

  @Invalid
  Scenario Outline: Login with a invalid credential
    Given I navigate to github login page
    When I enter invalid username <username> and invalid password <password>
    And I click Log in button
    Then I should not be able to login successfully

    Examples: 
      | username | password                         |
      | JohnDoe  |                                  |
      |          | g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM |
      |          |                                  |
      | JohnDoe  | g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM |

  @Valid
  Scenario Outline: Login with a valid credential
    Given I navigate to github login page
    When I enter username <username> and password <password>
    And I click Log in button
    Then I should be able to login successfully

    Examples: 
      | username | password                         |
      | JohnDoe  | g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM |

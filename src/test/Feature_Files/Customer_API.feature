@Customer_API
Feature: Test Customer API with different functionality
  This features is used to validate Customer API Functionality

  @Positive_Scenario
  Scenario Outline: Validate Create Customer API with valid auth key
    Given I have valid auth key
    And I have email "<email>" and description "<description>"
    When I send post request
    Then I verify status code is <statusCode>
    And email in response should be "<email>" description should be "<description>"
    And I verify "<field>" is present

    Examples: 
      | email           | description | statusCode | field |
      | name1@gmail.com | name1       |        200 | id    |

  @Negative_Scenario
  Scenario Outline: Validate Create Customer API with in-valid auth key
    Given I have in-valid auth key
    When I send post request
    Then I verify status code is <statusCode>
    And I verify "<field>" is not present in response
    And response contains the "<message>"

    Examples: 
      | message                                                               | statusCode | field |
      | Invalid API Key provided |        401 | id    |

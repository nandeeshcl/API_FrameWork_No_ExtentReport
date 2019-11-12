@Customer_API
Feature: Test Customer API with different functionality
  This features is used to validate Customer API Functionality

  @Create_Customer_Valid_Key
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

  @Create_Customer_In-Valid_Key
  Scenario Outline: Validate Create Customer API with in-valid auth key
    Given I have in-valid auth key
    When I send post request
    Then I verify status code is <statusCode>
    And I verify "<field>" is not present in response
    And response contains the "<message>"

    Examples: 
      | message                  | statusCode | field |
      | Invalid API Key provided |        401 | id    |

  @Retrieve_Customer
  Scenario Outline: Validate Retrieve Customer API
    This scenario is used to test retrieve customer API

    Given I have valid auth key
    When I send get request with customerID "<cusID>"
    Then I verify status code is <statusCode>
    And I verify same customer ID present in the response "<cusID>"

    Examples: 
      | cusID              | statusCode |
      | cus_G8jJBjNWUuX6LE |        200 |
      | cus_G8k08zfnnNIbmk |        200 |

  @Retrieve_Customer_with_EID
  Scenario Outline: Validate Retrieve Customer API with existing created Customer ID
    This scenario is used to test retrieve customer API

    Given I have valid auth key
    When I get customerID from Createcustomer API
    And I send get request with obtained customerID
    Then I verify status code is <statusCode>
    And I verify same customer ID present in the response

    Examples: 
      | statusCode |
      |        200 |

  @Update_Customer
  Scenario Outline: Validate Update Customer API
    This scenario is used to test Update customer api working fine for balance, description and email change

    Given I have valid auth key
    When I update email "<email>" description "<description>" and balance <balance>
    And I send post request for Update_Customer_API
    Then I verify status code is <statusCode>
    And response contains same email "<email>" description "<description>" and balance <balance>

    Examples: 
      | email           | description              | balance | statusCode |
      | appu1@gmail.com | Testing Update Feature 1 |    5000 |        200 |
      | appu2@gmail.com | Testing Update Feature 2 |    6000 |        200 |

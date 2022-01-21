@ApiAutomationTesting
Feature: This is to test the automation api
  Background:

  @Automation1
  Scenario: validate api testing
    Given url endpoint "restapi"
#    Then user prepare the "" request body
#    When user post reqeust body to service
#    Then user validates the response "200"


#  @Automation2
#  Scenario: login to Gamil and validate home page
#    Given user given gmail "url"
#    And user enter the user name "username"
#    And user enter the password "password"
#    When click on signin button
#    Then user validate the home page loaded
#
#
#  @Automation3
#  Scenario Outline: login to Gamil and validate home page
#    Given user given gmail "<UrL>"
#    And user enter the user name "<username>"
#    And user enter the password "<Password>"
#    When click on signin button
#    Then user validate the home page loaded
#    Examples:
#    |UrL|username|Password|
#    |https://www.gmail.com|Iamuser1|password1|
#    |https://www.yahoo.com|Iamuser1|password1|
#    |https://www.rediff.com|Iamuser1|password1|
#    |https://www.outlook.com|Iamuser1|password1|
#
#
#  @Automation4
#  Scenario Outline: login to Gamil home page is not loaded
#    Given user given gmail "<UrL>"
#    And user enter the user name "<username>"
#    When click on signin button
#    Then user validate the error message "<error_message>"
#    Examples:
#      |UrL|username|error_message|
#      |https://www.gmail.com|Iamuser1|username or password is required|
#      |https://www.yahoo.com|Iamuser1|username or password is required|
#      |https://www.rediff.com|Iamuser1|username or password is required|
#      |https://www.outlook.com|Iamuser1|username or password is required|





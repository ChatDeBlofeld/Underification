Feature: Operations on applications

  Background:
    Given the API server is running

  Scenario: get the list of applications
    When I send a GET to the /applications endpoint
    Then I receive a 200 status code

  Scenario: register an application
    Given I have an application payload
    When I POST the application payload to the /applications endpoint
    Then I receive a 201 status code
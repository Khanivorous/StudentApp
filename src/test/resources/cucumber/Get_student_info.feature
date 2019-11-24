Feature: Get complete student information from web service

  Scenario Outline: Get student information by id
    Given I send a GET request to the students api at path <id>
    When I receive the response
    Then I print the students <name> and <age> to the command line

    Examples:
      | id |name|age|
      | "1"  |Barry|20|
      | "2"  |Sheila|19|

  Scenario Outline: Get all students information
    Given I send a GET request to the students api
    When I receive the response
    Then I all the students <name> and <age>

    Examples:
    |name|age|
    |Barry|20|
    |Sheila|19|

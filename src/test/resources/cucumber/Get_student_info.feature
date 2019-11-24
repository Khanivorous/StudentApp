Feature: Get complete student information from web service

  Scenario Outline: Get student information by id
    Given I send a successful GET request to the students api at path <id>
    Then I print the students <name> and <age> to the command line

    Examples:
      | id |name|age|
      | "1"  |Barry|20|
      | "2"  |Sheila|19|

  Scenario Outline: Get all students information
    Given I send a successful GET request to the students api
    Then I print all the students <name> and <age>

    Examples:
    |name|age|
    |Barry|20|
    |Sheila|19|

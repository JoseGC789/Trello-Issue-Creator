Feature: Create Card
  As a user of Trello's Task manager app
  I want a simplification of its use
  So I can create ticket comfortably

  Background:
    Given the App is running

  Scenario: Create an issue
    Given I have a task of type issue
    And I provide the title and description "Example"
    When I create the Card
    Then Trello creates the card to the "To Do" list as unassigned

  Scenario: Create a bug
    Given I have a task of type bug
    And I only provide the description "Example"
    When I create the Card
    Then Trello creates the card to the "To Do" list
    And it's also labelled as "Bug"
    And a random member is assigned to it
    And the title is randomized with the pattern "bug-\w+-\d+"

  Scenario Outline: Create a generic task
    Given I have a <category> task
    And I provide the title "Example"
    When I create the Card
    Then Trello creates the card to the "To Do" list
    And it is labelled with <label>

    Examples:
      |category     | label       |
      |"maintenance"|"Maintenance"|
      |"research"   |"Research"   |
      |"test"       |"Test"       |

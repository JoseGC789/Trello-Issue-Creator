Feature: Create Card
  As a user of Trello's Task manager app
  I want a simplification of its use
  So I can create ticket comfortably

  Scenario: Create an issue
    Given I have a task of type issue
    When I create the Card
    And I provide the title and description "Example"
    Then Trello creates the card to the "To Do" list as unassigned

  Scenario: Create a bug
    Given I have a task of type bug
    When I create the Card
    And I only provide the description "Example"
    Then Trello creates the card to the "To Do" list
    And it's also labelled as "Bug"
    And a random member is assigned to it
    And the title is randomized with the pattern "bug-{word}-{number}"

  Scenario Outline: Create a generic task
    Given I have a generic task
    When I create the card
    And I provide the title "Excample" and a label: <category>
    Then Trello creates the card to the "To Do" list

    Examples:
      |category     |
      |"Maintenance"|
      |"Reasearch"  |
      |"Test"       |

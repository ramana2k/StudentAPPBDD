Feature: Testing different requests on student application

  Scenario: Check the student application is up and running
    When I send the GET request to the studentlist and get valid response code as 200

  Scenario Outline: Create a new student & verify if the student is added
    When I create a new student by providing the information firstName <firstName> lastName <lastName> email <email> programme <programme> courses <courses>
    Then I verify that the student with <email> is created

    Examples: 
      | firstName | lastName | email                                     | programme        | courses |
      | Declan    | Smith    | nnon.ante.bibendum@risusDonecegestas.edu  | Computer Science | Java    |
      | Mark      | Taylor   | nnon2.ante.bibendum@risusDonecegestas.edu | Computer Science | Java    |

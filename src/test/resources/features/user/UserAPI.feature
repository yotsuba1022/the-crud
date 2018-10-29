@user_api
Feature: User API related features

# Normal Cases
  @user_creation
  Scenario: As an user, I should be able to create an user record in database
    When I create an user with the following information, I should get the response with http status code "200"
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | ruru0513 | !QAZ2wsx | Ruru      | Cheng    | 1991-05-13T09:15:01 | 27  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
    Then the following user record should exist in the database
      | username | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | ruru0513 | Ruru      | Cheng    | 1991-05-13T09:15:01 | 27  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |

  @user_query
  Scenario: As an user, I should be able to query user records with composed query conditions
    Given the following newly-created users
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | test1    | !QAZ2wsx | Irene     | Cheng    | 2001-01-01T01:01:01 | 17  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
      | test2    | !QAZ2wsx | Irene     | Wang     | 2002-01-01T01:01:01 | 18  | FEMALE | 2018-10-16T21:45:01 | false   | false | false  | false       |
      | test3    | !QAZ2wsx | Irene     | Haung    | 2003-01-01T01:01:01 | 19  | FEMALE | 2018-10-16T21:45:01 | false   | true  | false  | false       |
    When I search user with the following information, I should get the response with http status code "200"
      | id | username | firstName | lastName | age | gender | isVip | limit | offset |
      |    | test1    |           |          |     |        |       | 10    | 0      |
    Then I should found the following users in the response body
      | username | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | test1    | Irene     | Cheng    | 2001-01-01T01:01:01 | 17  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
    When I search user with the following information, I should get the response with http status code "200"
      | id | username | firstName | lastName | age | gender | isVip | limit | offset |
      |    |          | Irene     |          |     |        |       | 10    | 0      |
    Then I should found the following users in the response body
      | username | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | test1    | Irene     | Cheng    | 2001-01-01T01:01:01 | 17  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
      | test2    | Irene     | Wang     | 2002-01-01T01:01:01 | 18  | FEMALE | 2018-10-16T21:45:01 | false   | false | false  | false       |
      | test3    | Irene     | Haung    | 2003-01-01T01:01:01 | 19  | FEMALE | 2018-10-16T21:45:01 | false   | true  | false  | false       |
    When I search user with the following information, I should get the response with http status code "200"
      | id | username | firstName | lastName | age | gender | isVip | limit | offset |
      |    |          | Irene     |          |     |        | true  | 10    | 0      |
    Then I should found the following users in the response body
      | username | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | test1    | Irene     | Cheng    | 2001-01-01T01:01:01 | 17  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
      | test3    | Irene     | Haung    | 2003-01-01T01:01:01 | 19  | FEMALE | 2018-10-16T21:45:01 | false   | true  | false  | false       |
    When I search user with the following information, I should get the response with http status code "200"
      | id | username | firstName | lastName | age | gender | isVip | limit | offset |
      |    |          | Irene     |          | 17  | FEMALE | true  | 10    | 0      |
    Then I should found the following users in the response body
      | username | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | test1    | Irene     | Cheng    | 2001-01-01T01:01:01 | 17  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |

  @user_query
  Scenario: As an user, I should be able to query user records with composed query conditions
    Given the following newly-created users
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | test7    | !QAZ2wsx | Rocko     | Tseng    | 2007-01-01T01:01:01 | 23  | MALE   | 2018-10-16T21:45:01 | true    | true  | false  | false       |
    When I search user with the following information, I should get the response with http status code "200"
      | id | username | firstName | lastName | age | gender | isVip | limit | offset |
      |    |          |           | Tseng    |     |        |       | 10    | 0      |
    Then I should found the following users in the response body
      | username | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | test7    | Rocko     | Tseng    | 2007-01-01T01:01:01 | 23  | MALE   | 2018-10-16T21:45:01 | true    | true  | false  | false       |

  @user_query
  Scenario: As an user, I should be able to query user records with composed query conditions
    Given the following newly-created users
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | test5    | !QAZ2wsx | Rabido    | Maomao   | 2005-01-01T01:01:01 | 21  | MALE   | 2018-10-16T21:45:01 | false   | false | false  | false       |
      | test6    | !QAZ2wsx | Carl      | Lu       | 2006-01-01T01:01:01 | 22  | MALE   | 2018-10-16T21:45:01 | false   | true  | false  | false       |
    When I search user with the following information, I should get the response with http status code "200"
      | id | username | firstName | lastName | age | gender | isVip | limit | offset |
      |    |          |           | Maomao   |     | MALE   |       | 10    | 0      |
    Then I should found the following users in the response body
      | username | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | test5    | Rabido    | Maomao   | 2005-01-01T01:01:01 | 21  | MALE   | 2018-10-16T21:45:01 | false   | false | false  | false       |

  @user_query
  Scenario: As an user, I should be able to query user records with composed query conditions
    Given the following newly-created users
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | test4    | !QAZ2wsx | Band      | Tsai     | 2004-01-01T01:01:01 | 20  | FEMALE | 2018-10-16T21:45:01 | false   | false | false  | true        |
    When I search user with the following information, I should get the response with http status code "200"
      | id | username | firstName | lastName | age | gender | isVip | limit | offset |
      |    |          |           |          | 20  |        |       | 10    | 0      |
    Then I should found the following users in the response body
      | username | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | test4    | Band      | Tsai     | 2004-01-01T01:01:01 | 20  | FEMALE | 2018-10-16T21:45:01 | false   | false | false  | true        |

# Error Cases
  @user_creation @user_creation_error_case
  Scenario: As an user, I should get validation error message when creating user without providing username
    When I create an user with the following information, I should get the response with http status code "400"
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      |          | !QAZ2wsx | Ruru      | Cheng    | 1991-05-13T09:15:01 | 27  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
    Then the response body should contains the following messages
      | message                       |
      | username is a required field. |

  @user_creation @user_creation_error_case
  Scenario: As an user, I should get validation error message when creating user without providing password
    When I create an user with the following information, I should get the response with http status code "400"
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | ruru0513 |          | Ruru      | Cheng    | 1991-05-13T09:15:01 | 27  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
    Then the response body should contains the following messages
      | message                       |
      | password is a required field. |

  @user_creation @user_creation_error_case
  Scenario: As an user, I should get validation error message when creating user without providing firstName
    When I create an user with the following information, I should get the response with http status code "400"
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | ruru0513 | !QAZ2wsx |           | Cheng    | 1991-05-13T09:15:01 | 27  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
    Then the response body should contains the following messages
      | message                        |
      | firstName is a required field. |

  @user_creation @user_creation_error_case
  Scenario: As an user, I should get validation error message when creating user without providing lastName
    When I create an user with the following information, I should get the response with http status code "400"
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | ruru0513 | !QAZ2wsx | RuRu      |          | 1991-05-13T09:15:01 | 27  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
    Then the response body should contains the following messages
      | message                       |
      | lastName is a required field. |

  @user_creation @user_creation_error_case
  Scenario: As an user, I should get validation error message when creating user without providing birthday
    When I create an user with the following information, I should get the response with http status code "400"
      | username | password | firstName | lastName | birthday | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | ruru0513 | !QAZ2wsx | RuRu      | Cheng    |          | 27  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
    Then the response body should contains the following messages
      | message                       |
      | birthday is a required field. |

  @user_creation @user_creation_error_case
  Scenario: As an user, I should get validation error message when creating user with future birthday
    When I create an user with the following information, I should get the response with http status code "400"
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | ruru0513 | !QAZ2wsx | RuRu      | Cheng    | 3018-10-16T21:45:01 | 27  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
    Then the response body should contains the following messages
      | message               |
      | back to the future!!! |

  @user_creation @user_creation_error_case
  Scenario: As an user, I should get validation error message when creating user with negative age value
    When I create an user with the following information, I should get the response with http status code "400"
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | ruru0513 | !QAZ2wsx | RuRu      | Cheng    | 1991-05-13T09:15:01 | -10 | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
    Then the response body should contains the following messages
      | message                         |
      | age should be positive or zero. |

  @user_creation @user_creation_error_case
  Scenario: As an user, I should get validation error message when creating user without providing gender value
    When I create an user with the following information, I should get the response with http status code "400"
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | ruru0513 | !QAZ2wsx | RuRu      | Cheng    | 1991-05-13T09:15:01 | 27  |        | 2018-10-16T21:45:01 | true    | true  | false  | false       |
    Then the response body should contains the following messages
      | message                     |
      | gender is a required field. |

  @user_creation @user_creation_error_case
  Scenario: As an user, I should get validation error messages when creating user with multiple invalid inputs
    When I create an user with the following information, I should get the response with http status code "400"
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      |          |          |           |          | 2991-05-13T09:15:01 | -2  |        | 2018-10-16T21:45:01 | false   | false | false  | false       |
    Then the response body should contains the following messages
      | message                         |
      | username is a required field.   |
      | password is a required field.   |
      | firstName is a required field.  |
      | lastName is a required field.   |
      | back to the future!!!           |
      | age should be positive or zero. |
      | gender is a required field.     |

  @user_query @user_query_error_case
  Scenario: As an user, I should get validation error message when querying user records with invalid id value
    When I search user with the following information, I should get the response with http status code "400"
      | id | username | firstName | lastName | age | gender | isVip | limit | offset |
      | -1 |          |           |          |     |        |       |       |        |
    Then the response body should contains the following messages
      | message                        |
      | Invalid criteria parameter: id |

  @user_query @user_query_error_case
  Scenario: As an user, I should get validation error message when querying user records with invalid age value
    When I search user with the following information, I should get the response with http status code "400"
      | id | username | firstName | lastName | age | gender | isVip | limit | offset |
      |    |          |           |          | -2  |        |       |       |        |
    Then the response body should contains the following messages
      | message                         |
      | Invalid criteria parameter: age |

  @user_query @user_query_error_case
  Scenario: As an user, I should get validation error message when querying user records with invalid gender value
    When I search user with the following information, I should get the response with http status code "400"
      | id | username | firstName | lastName | age | gender | isVip | limit | offset |
      |    |          |           |          |     | YOOOO  |       |       |        |
    Then the response body should contains the following messages
      | message               |
      | Invalid gender value. |

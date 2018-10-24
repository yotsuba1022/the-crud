@user_api
Feature: User API related features

  @user_creation
  Scenario: As an user, I should be able to create an user record in database
    When I create an user with the following information, I should get the response with http status code "200"
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | ruru0513 | !QAZ2wsx | Ruru      | Cheng    | 1991-05-13T09:15:01 | 27  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
    Then the following user record should exist in the database
      | username | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      | ruru0513 | Ruru      | Cheng    | 1991-05-13T09:15:01 | 27  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |

  @user_creation @user_creation_error_case
  Scenario: As an user, I should get validation error message when username is not provided
    When I create an user with the following information, I should get the response with http status code "400"
      | username | password | firstName | lastName | birthday            | age | gender | registrationDate    | isAdmin | isVip | isTest | isSuspended |
      |          | !QAZ2wsx | Ruru      | Cheng    | 1991-05-13T09:15:01 | 27  | FEMALE | 2018-10-16T21:45:01 | true    | true  | false  | false       |
    Then the response body should contains the following messages
      | message                       |
      | username is a required field. |

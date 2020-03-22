Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new user is selected
        When valid username "liisa" and password "salainen1" and matching password confirmation are entered
        Then new user is created

    Scenario: creation fails with too short username and valid password
        Given command new user is selected
        When valid username "u" and password "salainen1" and matching password confirmation are entered
        Then user is not created and error message shown about too short username

    Scenario: creation fails with correct username and too short password
        Given command new user is selected
        When valid username "ullamaija" and password "l" and matching password confirmation are entered
        Then user is not created and error message shown about password length

    Scenario: creation fails when password and password confirmation do not match
        Given command new user is selected
        When valid username "ullamaija" and password "laivalaulubest3" and non-matching password confirmation "laulu" are entered
        Then user is not created and error password and password confirmation do not match is reported
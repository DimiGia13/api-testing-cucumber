Feature: Booking API tests

  Scenario: Get all bookings
    Given the Booking API is available
    When I send a GET request to /booking
    Then the response status code should be 200
    And the response should contain at least one booking




  Scenario: Create a new booking
    Given the Booking API is available
    When I send a POST request to /booking with valid booking details
    Then the response status code should be 200
    And the response should contain the booking id


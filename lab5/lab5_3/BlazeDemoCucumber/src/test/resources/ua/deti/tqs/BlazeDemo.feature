Feature: BlazeDemo Purchase Flight

    Background:
        Given I am on the BlazeDemo homepage on "https://blazedemo.com"

    Scenario: Purchase a flight
        When I select "Portland" as the departure city and "Rome" as the destination city
        And I click on the Find Flights button
        And I choose the flight number 1
        And I fill in the form with the following information:
            | Name        | Roberto Castro |
            | Address     | Fundevila |
            | City        | Sago |
            | State       | Viana do Castelo |
            | Zip Code    | 4059 |
            | Credit Card | 11112222333444 |
            | Month       | 12 |
            | Year        | 2023 |
            | Name on Card| Roberto |
        
        And I do not click on the Remember me checkbox
        And I click on the Purchase Flight button
        Then I should see the message "BlazeDemo Confirmation"
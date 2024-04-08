Feature: BusTicket Book Bus
    
    Background:
        Given I am on the BusTicket homepage on "http://localhost:5173"

    Scenario: Book a bus
        When I write "Aveiro" as the departure city and "Porto" as the arrival city
        And I select the date "08-04-2024" for the departure
        And I click on the Search Buses button
        And I select the Currency "¥ - JPY - Japanese Yen"
        And I choose the bus with Bus Number "R045"
        And I fill in the form with the following information:
            | Name  | Roberto   |
            | Surname   | Castro    |
            | Email | robertorcastro@ua.pt  |
            | Address   | Caminho da Alegria 5  |
            | Postal Code   | 4950-750  |
            | City  | Monção    |
            | Country   | Portugal  |
            | Phone Number  | 916162223 |
            | Credit Card Number    | 1111222233334444  |
            | Expiration Date   | 12/2024   |
            | CVV   | 123   |
        And I click on the Confirm Button
        And I capture the confirmation code
        Then I should get the "Booking Confirmed!!"

    Scenario: Check Reservation
        When I click on the Check Reservation button
        And I write the confirmation code
        And I click on the Search Button
        Then I should get the "Booking Details" and the Reservation Status "Confirmed"
            


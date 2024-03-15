package ua.deti.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookFlightSteps {

    ChromeDriver driver = new ChromeDriver();
    private static final Logger logger = getLogger(lookup().lookupClass());

    @Given("I am on the BlazeDemo homepage on {string}")
    public void i_am_on_the_blazedemo_homepage(String url) {
        
        logger.info("Opening browser at {}", url);
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1440, 900));
    }

    @When("I select {string} as the departure city and {string} as the destination city")
    public void i_select_as_the_departure_city_and_as_the_destination_city(String departure, String destination) {
        
        WebElement element = driver.findElement(By.name("fromPort"));
        Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
        logger.info("Departure city is editable: {}", isEditable);
        assertEquals(true, isEditable);

        element = driver.findElement(By.name("toPort"));
        isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
        logger.info("Destination city is editable: {}", isEditable);
        assertEquals(true, isEditable);

        logger.info("Selecting departure city {} and destination city {}", departure, destination);
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.xpath("//option[. = '" + departure + "']")).click();
        driver.findElement(By.name("toPort")).click();
        driver.findElement(By.xpath("//option[. = '" + destination + "']")).click();
    }

    @And("I click on the Find Flights button")
    public void i_click_the_find_flights_button() {
        logger.info("Clicking the Find Flights button");
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @And("I choose the flight number {int}")
    public void i_select_the_first_flight(int flightNumber) {
        logger.info("Selecting the first flight");
        driver.findElement(By.cssSelector("tr:nth-child(" + flightNumber + ") .btn")).click();
    }

    @And("I fill in the form with the following information:")
    public void i_fill_in_the_form_with_the_following_details(Map<String, String> details) {
        logger.info("Filling in the form with the following details: {}", details);
        
        assertTrue(driver.findElement(By.id("inputName")).isEnabled() && driver.findElement(By.id("inputName")).getAttribute("readonly") == null);
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys(details.get("Name"));
        
        assertTrue(driver.findElement(By.id("address")).isEnabled() && driver.findElement(By.id("address")).getAttribute("readonly") == null);
        driver.findElement(By.id("address")).sendKeys(details.get("Address"));
        
        assertTrue(driver.findElement(By.id("city")).isEnabled() && driver.findElement(By.id("city")).getAttribute("readonly") == null);
        driver.findElement(By.id("city")).sendKeys(details.get("City"));
        
        assertTrue(driver.findElement(By.id("state")).isEnabled() && driver.findElement(By.id("state")).getAttribute("readonly") == null);
        driver.findElement(By.id("state")).sendKeys(details.get("State"));
        
        assertTrue(driver.findElement(By.id("zipCode")).isEnabled() && driver.findElement(By.id("zipCode")).getAttribute("readonly") == null);
        driver.findElement(By.id("zipCode")).sendKeys(details.get("Zip Code"));
        
        assertTrue(driver.findElement(By.id("creditCardNumber")).isEnabled() && driver.findElement(By.id("creditCardNumber")).getAttribute("readonly") == null);
        driver.findElement(By.id("creditCardNumber")).sendKeys(details.get("Credit Card"));
        
        assertTrue(driver.findElement(By.id("creditCardMonth")).isEnabled() && driver.findElement(By.id("creditCardMonth")).getAttribute("readonly") == null);
        driver.findElement(By.id("creditCardMonth")).click();
        driver.findElement(By.id("creditCardMonth")).click();
        driver.findElement(By.id("creditCardMonth")).sendKeys(details.get("Month"));
        
        assertTrue(driver.findElement(By.id("creditCardYear")).isEnabled() && driver.findElement(By.id("creditCardYear")).getAttribute("readonly") == null);
        driver.findElement(By.id("creditCardYear")).click();
        driver.findElement(By.id("creditCardYear")).click();
        driver.findElement(By.id("creditCardYear")).sendKeys(details.get("Year"));
        
        assertTrue(driver.findElement(By.id("nameOnCard")).isEnabled() && driver.findElement(By.id("nameOnCard")).getAttribute("readonly") == null);
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys(details.get("Name on Card"));
    }

    @And("I do not click on the Remember me checkbox")
    public void i_do_not_click_on_the_remember_me_checkbox() {
        logger.info("Checking if the Remember me checkbox is not selected");
        assertFalse(driver.findElement(By.id("rememberMe")).isSelected());
    }

    @And("I click on the Purchase Flight button")
    public void i_click_on_the_purchase_flight_button() {
        logger.info("Clicking on the Purchase Flight button");
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I should see the message {string}")
    public void i_should_see_the_message(String message) {
        logger.info("Checking if the message {} is displayed", message);
        assertEquals(message, driver.getTitle());
        driver.quit();
    }
}
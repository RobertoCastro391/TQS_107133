package ua.deti.tqs;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;
import java.util.Map;

public class BookBusTicketsSteps {

    ChromeDriver driver = new ChromeDriver();
    private static final Logger logger = getLogger(lookup().lookupClass());
    String bookingId = "";

    @Given("I am on the BusTicket homepage on {string}")
    public void i_am_on_the_BusTicket_homepage(String url) {
        logger.info("Opening browser at {}", url);
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1440, 820));
    }

    @When("I write {string} as the departure city and {string} as the arrival city")
    public void i_select_as_the_departure_city_and_as_the_destination_city(String departure, String destination) {

        WebElement elementDep = driver.findElement(By.id("from"));
        Boolean isEditable = elementDep.isEnabled() && elementDep.getAttribute("readonly") == null;
        logger.info("Departure city is editable: {}", isEditable);
        assertEquals(true, isEditable);

        WebElement elementArr = driver.findElement(By.name("to"));
        isEditable = elementArr.isEnabled() && elementArr.getAttribute("readonly") == null;
        logger.info("Arrical city is editable: {}", isEditable);
        assertEquals(true, isEditable);

        logger.info("Writing departure city {} and destination city {}", departure, destination);
        elementDep.sendKeys(departure);
        elementArr.sendKeys(destination);
    }

    @And("I select the date {string} for the departure")
    public void i_select_the_departure_date(String depDate) {
        logger.info("Departure Date is: {}", depDate);
        WebElement element = driver.findElement(By.id("date"));
        element.click();
        element.sendKeys(depDate);
    }

    @And("I click on the Search Buses button")
    public void i_click_on_the_SearchButtons() {
        WebElement element = driver.findElement(By.cssSelector(".btn-primary"));
        logger.info("CLick on the Search Buses button");
        element.click();
    }

    @And("I select the Currency {string}")
    public void i_select_the_currency(String currency) {
        WebElement element = driver.findElement(By.id("dropdown-variants-Currency"));
        element.click();
        logger.info("Choosing the currency: {}", currency);
        WebElement currencyElement = driver.findElement(By.xpath("/html/body/div/nav/div/div/div/div/div/div/a[3]"));
        currencyElement.click();

    }

    @And("I choose the bus with Bus Number {string}")
    public void i_choose_the_bus_with_busNumber(String busNumer) {
        WebElement element = driver.findElement(By.linkText("Book Now !"));
        logger.info("Choosing the Bus with Bus Number: {}", busNumer);
        element.click();
    }

    @And("I fill in the form with the following information:")
    public void i_fill_in_the_form_with_the_following_information(Map<String, String> info) {
        logger.info("Filling the info in the form: {}.", info);

        WebElement element = driver.findElement(By.cssSelector(
                ".container-inside-passenger-details-form:nth-child(2) > .column:nth-child(1) > input:nth-child(2)"));
        assertTrue(element.isEnabled() && element.getAttribute("readonly") == null);
        element.click();
        element.sendKeys(info.get("Name"));

        element = driver.findElement(By.cssSelector(
                ".container-inside-passenger-details-form:nth-child(2) > .column:nth-child(1) > input:nth-child(4)"));
        assertTrue(element.isEnabled() && element.getAttribute("readonly") == null);
        element.click();
        element.sendKeys(info.get("Surname"));

        element = driver.findElement(By.cssSelector(".column:nth-child(1) > input:nth-child(6)"));
        assertTrue(element.isEnabled() && element.getAttribute("readonly") == null);
        element.click();
        element.sendKeys(info.get("Email"));

        element = driver.findElement(By.cssSelector(".column:nth-child(1) > input:nth-child(8)"));
        assertTrue(element.isEnabled() && element.getAttribute("readonly") == null);
        element.click();
        element.sendKeys(info.get("Address"));

        element = driver.findElement(By.cssSelector(
                ".container-inside-passenger-details-form:nth-child(2) > .column:nth-child(2) > input:nth-child(2)"));
        assertTrue(element.isEnabled() && element.getAttribute("readonly") == null);
        element.click();
        element.sendKeys(info.get("Postal Code"));

        element = driver.findElement(By.cssSelector(".column:nth-child(2) > input:nth-child(4)"));
        assertTrue(element.isEnabled() && element.getAttribute("readonly") == null);
        element.click();
        element.sendKeys(info.get("City"));

        element = driver.findElement(By.cssSelector(".column:nth-child(2) > input:nth-child(6)"));
        assertTrue(element.isEnabled() && element.getAttribute("readonly") == null);
        element.click();
        element.sendKeys(info.get("Country"));

        element = driver.findElement(By.cssSelector(".column:nth-child(2) > input:nth-child(8)"));
        assertTrue(element.isEnabled() && element.getAttribute("readonly") == null);
        element.click();
        element.sendKeys(info.get("Phone Number"));

        element = driver.findElement(By.cssSelector(
                ".container-inside-passenger-details-form:nth-child(4) > .column:nth-child(1) > input:nth-child(2)"));
        assertTrue(element.isEnabled() && element.getAttribute("readonly") == null);
        element.click();
        element.sendKeys(info.get("Credit Card Number"));

        element = driver.findElement(
                By.cssSelector(".container-inside-passenger-details-form:nth-child(4) input:nth-child(4)"));
        assertTrue(element.isEnabled() && element.getAttribute("readonly") == null);
        element.click();
        element.sendKeys(info.get("Expiration Date"));

        element = driver.findElement(
                By.cssSelector(".container-inside-passenger-details-form:nth-child(4) > .column:nth-child(2) > input"));
        assertTrue(element.isEnabled() && element.getAttribute("readonly") == null);
        element.click();
        element.sendKeys(info.get("CVV"));
    }

    @And("I click on the Confirm Button")
    public void i_click_on_the_Confirm_Button() {
        logger.info("Clicking on the Confirm Button");
        //WebElement element = driver.findElement(By.linkText("Confirm Booking !"));
        WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div/a"));
        element.click();
    }


    @And("I capture the confirmation code")
    public void i_capture_the_confirmation_code() {
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Your booking id is:')]")));

        //WebElement element = driver.findElement(By.xpath("//p[contains(text(), 'Your booking id is:')]"));
        
        bookingId = element.findElement(By.tagName("strong")).getText();

        logger.info("Confirmation code: {}", bookingId);
    }

    @Then("I should get the {string}")
    public void get_Booking_Confirmation_page(String message) {
        WebElement element = driver.findElement(By.xpath("//*[@id='root']/div/h1"));
        assertEquals(message, element.getText());

        // assertEquals(message, element.getText());
        element = driver.findElement(By.xpath("/html/body/div/nav/div/a"));
        element.click();
        logger.info("Booking confirmed");
    }

    // @When("I click on the Check Reservation button")
    // public void i_click_on_the_Check_Reservation_button() {
    //     logger.info("Clicking on the Check Reservation button");
    //     WebElement element = driver.findElement(By.linkText("Check your booking"));
    //     element.click();
    // }

    // @And("I write the confirmation code")
    // public void i_write_the_confirmation_code() {
    //     logger.info("Writing the confirmation code: {}", bookingId);
    //     WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/form/div[1]/input"));
    //     element.sendKeys(bookingId);

    // }

    // @And("I click on the Search Button")
    // public void i_click_on_the_Check_Button() {
    //     logger.info("Clicking on the Search Button");
    //     WebElement element = driver.findElement(By.cssSelector(".btn .btn-primary"));
    //     element.click();
    // }

    // @Then("I should get the {string} and the Reservation Status {string}")
    // public void i_should_get_the_and_the_Reservation_Status(String message, String status) {
    //     logger.info("Checking the reservation status");
    //     WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div/h1"));
    //     assertEquals(message, element.getText());

    //     element = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/p/strong"));
    //     assertEquals(status, element.getText());
    // }
}
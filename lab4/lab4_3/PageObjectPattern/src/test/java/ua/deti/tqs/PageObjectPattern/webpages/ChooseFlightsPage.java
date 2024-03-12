package ua.deti.tqs.PageObjectPattern.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ChooseFlightsPage {

    private WebDriver driver;

    // Choose Flights Button
    @FindBy(css = "tr:nth-child(1) .btn")
    private WebElement chooseFlightButton;

    // Constructor
    public ChooseFlightsPage(WebDriver driver) {
        this.driver = driver;

        // Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        return driver.getTitle().toString().contains("BlazeDemo - reserve");
    }  
    
    public void clickChooseFlightButton() {
        chooseFlightButton.click();
    }
}
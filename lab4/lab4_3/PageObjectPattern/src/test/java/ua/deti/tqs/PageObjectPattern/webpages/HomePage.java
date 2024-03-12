package ua.deti.tqs.PageObjectPattern.webpages;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.How;

public class HomePage {

    private WebDriver driver;

    // Page URL
    private static String PAGE_URL = "https://blazedemo.com";

    // Locators
    // Apply as Developer Button
    @FindBy(how = How.NAME, using = "fromPort")
    private WebElement fromPortInput;

    @FindBy(how = How.NAME, using = "toPort")
    private WebElement toPortInput;

    @FindBy(how = How.XPATH, using = "//option[text()='Portland']")
    private WebElement fromPortOption;

    @FindBy(how = How.XPATH, using = "//option[text()='Rome']")
    private WebElement toPortOption;

    @FindBy(how = How.CSS, using = ".btn-primary")
    private WebElement findFlightsButton;

    @FindBy(how = How.LINK_TEXT, using = "home")
    private WebElement homeButton;

    // Heading
    @FindBy(tagName = "h1")
    private WebElement heading;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        driver.manage().window().setSize(new Dimension(1440, 900));
        
        // Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        return heading.getText().toString().contains("Welcome to the Simple Travel Agency!");
    }

    public boolean isFormPortEditable() {
        return fromPortInput.isEnabled() && fromPortInput.getAttribute("readonly") == null;
    }

    public boolean isToPortEditable() {
        return toPortInput.isEnabled() && toPortInput.getAttribute("readonly") == null;
    }

    public void selectFromPort() {
        fromPortInput.click();
        fromPortOption.click();
    }

    public void selectToPort() {
        toPortInput.click();
        toPortOption.click();
    }

    public void clickFindFlightsButton() {
        findFlightsButton.click();
    }

    public void clickOnHomeButton() {
        homeButton.click();
    }
}
package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

    private WebDriver driver;

    @FindBy(how = How.LINK_TEXT, using = "Travel The World")
    private WebElement travelTheWorldLink;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;

        // Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public String pageTitle() {
        return driver.getTitle();
    }

    public void clickTravelTheWorldLink() {
        travelTheWorldLink.click();
    }
}
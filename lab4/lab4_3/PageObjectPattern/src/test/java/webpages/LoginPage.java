package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(how = How.CSS, using = ".navbar-brand")
    private WebElement homePageButton;
    

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        // Initialize web elements
        PageFactory.initElements(driver, this);
    }
    
    public void clickHomePageButton() {
        homePageButton.click();
    }
}
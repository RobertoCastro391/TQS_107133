package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.How;

public class FillPassengerData {

    private WebDriver driver;

    @FindBy(how = How.ID, using = "inputName")
    private WebElement inputName;

    @FindBy(how = How.ID, using = "address")
    private WebElement address;

    @FindBy(how = How.ID, using = "city")
    private WebElement city;

    @FindBy(how = How.ID, using = "state")
    private WebElement state;

    @FindBy(how = How.ID, using = "zipCode")
    private WebElement zipCode;

    @FindBy(how = How.ID, using = "cardType")
    private WebElement cardType;

    @FindBy(how = How.ID, using = "creditCardNumber")
    private WebElement creditCardNumber;

    @FindBy(how = How.ID, using = "creditCardMonth")
    private WebElement creditCardMonth;

    @FindBy(how = How.ID, using = "creditCardYear")
    private WebElement creditCardYear;

    @FindBy(how = How.ID, using = "nameOnCard")
    private WebElement nameOnCard;

    @FindBy(how = How.ID, using = "rememberMe")
    private WebElement checkBoxRememberMe;

    @FindBy(how = How.CSS, using = "input.btn-primary")
    private WebElement purchaseButton;


    
    public FillPassengerData(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isInputNameEditable() {
        return inputName.isEnabled() && inputName.getAttribute("readonly") == null;
    }

    public boolean isAddressEditable() {
        return address.isEnabled() && address.getAttribute("readonly") == null;
    }

    public boolean isCityEditable() {
        return city.isEnabled() && city.getAttribute("readonly") == null;
    }

    public boolean isStateEditable() {
        return state.isEnabled() && state.getAttribute("readonly") == null;
    }

    public boolean isZipCodeEditable() {
        return zipCode.isEnabled() && zipCode.getAttribute("readonly") == null;
    }

    public boolean isCreditCardNumberEditable() {
        return creditCardNumber.isEnabled() && creditCardNumber.getAttribute("readonly") == null;
    }

    public boolean isCreditCardMonthEditable() {
        return creditCardMonth.isEnabled() && creditCardMonth.getAttribute("readonly") == null;
    }

    public boolean isCreditCardYearEditable() {
        return creditCardYear.isEnabled() && creditCardYear.getAttribute("readonly") == null;
    }

    public boolean isNameOnCardEditable() {
        return nameOnCard.isEnabled() && nameOnCard.getAttribute("readonly") == null;
    }

    public void fillInputName(String name) {
        inputName.sendKeys(name);
    }

    public void fillAddress(String address) {
        this.address.sendKeys(address);
    }

    public void fillCity(String city) {
        this.city.sendKeys(city);
    }

    public void fillState(String state) {
        this.state.sendKeys(state);
    }

    public void fillZipCode(String zipCode) {
        this.zipCode.sendKeys(zipCode);
    }

    public void fillCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber.sendKeys(creditCardNumber);
    }

    public void fillCreditCardMonth(String creditCardMonth) {
        this.creditCardMonth.sendKeys(creditCardMonth);
    }

    public void fillCreditCardYear(String creditCardYear) {
        this.creditCardYear.sendKeys(creditCardYear);
    }

    public void fillNameOnCard(String nameOnCard) {
        this.nameOnCard.sendKeys(nameOnCard);
    }

    public void doubleClickCreditCardMonth() {
        Actions builder = new Actions(driver);
        builder.doubleClick(creditCardMonth).perform();
    }

    public void doubleClickCreditCardYear() {
        Actions builder = new Actions(driver);
        builder.doubleClick(creditCardYear).perform();
    }

    public boolean isCheckedBoxChecked() {
        return checkBoxRememberMe.isSelected();
    }

    public void clickOnCheckBox() {
        checkBoxRememberMe.click();
    }

    public void clickPurchaseButton() {
        purchaseButton.click();
    }   
}
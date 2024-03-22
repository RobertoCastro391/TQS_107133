package tests;

// Generated by Selenium IDE
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.seljup.SeleniumJupiter;

import webpages.HomePage;
import webpages.LoginPage;
import webpages.ChooseFlightsPage;
import webpages.FillPassengerData;
import webpages.ConfirmationPage;

@ExtendWith(SeleniumJupiter.class)
public class BlazeTest {
  
  private WebDriver driver;

  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
  }
  
  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void blazeDemoTest() {

    HomePage homePage = new HomePage(driver);
    assertTrue(homePage.isPageOpened());
    
    assertTrue(homePage.isFormPortEditable());
    homePage.selectFromPort();

    assertTrue(homePage.isToPortEditable());
    homePage.selectToPort();

    homePage.clickFindFlightsButton();

    ChooseFlightsPage chooseFlightsPage = new ChooseFlightsPage(driver);
    assertTrue(chooseFlightsPage.isPageOpened());

    chooseFlightsPage.clickChooseFlightButton();

    FillPassengerData fillPassengerData = new FillPassengerData(driver);
    
    assertTrue(fillPassengerData.isInputNameEditable());
    fillPassengerData.fillInputName("Roberto");

    assertTrue(fillPassengerData.isAddressEditable());
    fillPassengerData.fillAddress("Fundevila");

    assertTrue(fillPassengerData.isCityEditable());
    fillPassengerData.fillCity("Sago");

    assertTrue(fillPassengerData.isStateEditable());
    fillPassengerData.fillState("Viana do Castelo");

    assertTrue(fillPassengerData.isZipCodeEditable());
    fillPassengerData.fillZipCode("4059");

    assertTrue(fillPassengerData.isCreditCardNumberEditable());
    fillPassengerData.fillCreditCardNumber("11112222333444");

    assertTrue(fillPassengerData.isCreditCardMonthEditable());
    fillPassengerData.doubleClickCreditCardMonth();
    fillPassengerData.fillCreditCardMonth("12");

    assertTrue(fillPassengerData.isCreditCardYearEditable());
    fillPassengerData.doubleClickCreditCardYear();
    fillPassengerData.fillCreditCardYear("3433");

    assertTrue(fillPassengerData.isNameOnCardEditable());
    fillPassengerData.fillNameOnCard("OKOKO");

    assertFalse(fillPassengerData.isCheckedBoxChecked());
    fillPassengerData.clickOnCheckBox();
    fillPassengerData.clickOnCheckBox();

    fillPassengerData.clickPurchaseButton();
    
    ConfirmationPage confirmationPage = new ConfirmationPage(driver);
    assertEquals("BlazeDemo Confirmation", confirmationPage.pageTitle());
  
    confirmationPage.clickTravelTheWorldLink();

    homePage.clickOnHomeButton();

    LoginPage loginPage = new LoginPage(driver);

    loginPage.clickHomePageButton();
  }
}
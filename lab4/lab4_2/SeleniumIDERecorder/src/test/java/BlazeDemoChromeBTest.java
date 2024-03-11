// Generated by Selenium IDE
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;


public class BlazeDemoChromeBTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void blazeDemoTest() {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(1440, 900));
    {
      WebElement element = driver.findElement(By.name("fromPort"));
      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
      assertTrue(isEditable);
    }
    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Portland']")).click();
    }
    {
      WebElement element = driver.findElement(By.name("fromPort"));
      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
      assertTrue(isEditable);
    }
    driver.findElement(By.name("toPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'Rome']")).click();
    }
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
    {
      WebElement element = driver.findElement(By.id("inputName"));
      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
      assertTrue(isEditable);
    }
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Roberto");
    {
      WebElement element = driver.findElement(By.id("address"));
      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
      assertTrue(isEditable);
    }
    driver.findElement(By.id("address")).sendKeys("Fundevila");
    {
      WebElement element = driver.findElement(By.id("city"));
      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
      assertTrue(isEditable);
    }
    driver.findElement(By.id("city")).sendKeys("Sago");
    {
      WebElement element = driver.findElement(By.id("state"));
      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
      assertTrue(isEditable);
    }
    driver.findElement(By.id("state")).sendKeys("Viana do Castelo");
    {
      WebElement element = driver.findElement(By.id("zipCode"));
      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
      assertTrue(isEditable);
    }
    driver.findElement(By.id("zipCode")).sendKeys("4059");
    {
      WebElement element = driver.findElement(By.id("creditCardNumber"));
      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
      assertTrue(isEditable);
    }
    driver.findElement(By.id("creditCardNumber")).sendKeys("11112222333444");
    {
      WebElement element = driver.findElement(By.id("creditCardMonth"));
      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
      assertTrue(isEditable);
    }
    driver.findElement(By.id("creditCardMonth")).click();
    driver.findElement(By.id("creditCardMonth")).click();
    driver.findElement(By.id("creditCardMonth")).click();
    {
      WebElement element = driver.findElement(By.id("creditCardMonth"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.id("creditCardMonth")).click();
    {
      WebElement element = driver.findElement(By.id("creditCardYear"));
      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
      assertTrue(isEditable);
    }
    driver.findElement(By.id("creditCardYear")).click();
    driver.findElement(By.id("creditCardYear")).click();
    {
      WebElement element = driver.findElement(By.id("creditCardYear"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.id("creditCardYear")).sendKeys("3433");
    {
      WebElement element = driver.findElement(By.id("nameOnCard"));
      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
      assertTrue(isEditable);
    }
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.id("creditCardMonth")).click();
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.id("nameOnCard")).sendKeys("OKOKO");
    assertFalse(driver.findElement(By.id("rememberMe")).isSelected());
    driver.findElement(By.id("rememberMe")).click();
    driver.findElement(By.id("rememberMe")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    assertEquals("BlazeDemo Confirmation", driver.getTitle());
    driver.findElement(By.linkText("Travel The World")).click();
    driver.findElement(By.linkText("home")).click();
    driver.findElement(By.linkText("BlazeDemo")).click();
    driver.close();
  }
}
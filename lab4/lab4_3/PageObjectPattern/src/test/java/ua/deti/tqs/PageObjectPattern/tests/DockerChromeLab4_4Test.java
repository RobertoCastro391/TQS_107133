package ua.deti.tqs.PageObjectPattern.tests;

import static io.github.bonigarcia.seljup.BrowserType.CHROME;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class DockerChromeLab4_4Test {

    @Test
    public void testDockerChromeLab4_4(@DockerBrowser(type = CHROME, vnc=true) WebDriver driver){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }
    
}

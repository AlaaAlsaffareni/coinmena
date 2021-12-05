package test

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class BaseClass {

    static WebDriver driver

    void setUp() {

        WebDriverManager.chromedriver().setup()
        driver = new ChromeDriver()
        driver.get("https://www.demoblaze.com/")
        driver.manage().window().maximize()
    }

    static WebDriver getWebDriver() {
        return driver
    }

    void tearDown() {
        driver.quit()
    }
}

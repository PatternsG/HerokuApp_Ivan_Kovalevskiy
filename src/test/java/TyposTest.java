import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TyposTest {
    private WebDriver driver;

    @BeforeClass
    public void setup (){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void herokuApp() {
        driver.get("http://the-internet.herokuapp.com/typos");
    }

    @AfterMethod
    //public void quit(){
    //    driver.quit();
    //}

    @Test
    public void typosTest() {
        WebElement checkText = driver.findElement(By.xpath("//p[2]"));
        Assert.assertEquals(checkText.getText(),
                "Sometimes you'll see a typo, other times you won't.",
                "Error in text");
    }
}


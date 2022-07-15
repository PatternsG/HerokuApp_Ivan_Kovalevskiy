import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IFrameTest {

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void herokuApp() {
        driver.get("http://the-internet.herokuapp.com/iframe");
    }

    @AfterMethod
    public void quit(){
        driver.quit();
    }

    @Test
    public void iFramePositiveTest(){
        driver.switchTo().frame("mce_0_ifr");
        WebElement text = driver.findElement(By.cssSelector("#tinymce"));
        Assert.assertEquals(text.getText(), "Your content goes here.");
        driver.switchTo().defaultContent();
    }
}

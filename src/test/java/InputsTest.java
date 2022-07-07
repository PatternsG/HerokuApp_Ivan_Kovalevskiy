import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

public class InputsTest {

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
        driver.get("http://the-internet.herokuapp.com/inputs");
    }

    @AfterMethod
    public void quit(){
        driver.quit();
    }

    @Test
    public  void inputsTest(){

        WebElement number = driver.findElement(By.xpath("//input[@type='number']"));
        number.click();
        number.sendKeys("12389");
        number.sendKeys(Keys.ARROW_UP);
        Assert.assertEquals(number.getAttribute("value"), "12390");
        number.clear();
        number.sendKeys("HI");
        Assert.assertEquals(number.getText(), "", "The string assumes the input of non-numeric values");

    }
}

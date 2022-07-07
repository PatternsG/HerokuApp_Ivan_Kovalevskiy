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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckboxesTest {

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
        driver.get("http://the-internet.herokuapp.com/checkboxes");
    }

    @AfterMethod
    public void quit(){
        driver.quit();
    }


    @Test
    public void checkBoxesTest(){

        List <WebElement> checkBox= driver.findElements(By.cssSelector("[type=checkbox]"));
        WebElement checkBoxElement = checkBox.get(0);
        checkBoxElement.click();
        Assert.assertFalse(checkBoxElement.isSelected(),
                "Initially first checkbox should be checked");
        checkBoxElement = checkBox.get(1);
        checkBoxElement.click();
        Assert.assertFalse(checkBoxElement.isSelected(),
                "After click second checkbox should be unchecked");

    }

}


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DropDownTest {

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
        driver.get("http://the-internet.herokuapp.com/dropdown");
    }

    @AfterMethod
    public void quit (){
        driver.quit();
    }

    @Test
    public void dropDownTest(){
        WebElement dropDownElement = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropDownElement);
        List<WebElement> options = select.getOptions();
        List <String> expectedOptions = new ArrayList<>();
        expectedOptions.add("Please select an option");
        expectedOptions.add("Option 1");
        expectedOptions.add("Option 2");
        List <String> actualOptions = options.stream().map(option -> option.getText()).toList();
        Assert.assertEquals(actualOptions, expectedOptions);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Please select an option");
        select.selectByValue("1");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 1");
        select.selectByValue("2");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 2");
    }
}

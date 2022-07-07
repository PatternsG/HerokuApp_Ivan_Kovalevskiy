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

public class AddRemoveElementsTest {

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
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
    }

    @AfterMethod
    public void quit(){
        driver.quit();
    }

    @Test
    public void addRemoveElementsTest(){

        WebElement addElement = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addElement.click();
        addElement.click();
        List <WebElement> allElements = driver.findElements(By.xpath("//button[text()='Delete']"));
        WebElement removeElement = allElements.get(0);
        removeElement.click();
        allElements = driver.findElements(By.xpath("//button[text()='Delete']"));
        Assert.assertEquals(allElements.size(), 1,
                "Number of added elements is different from the expected value");
    }
}

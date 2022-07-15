import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.function.Function;

public class DynamicControlsTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }

    @BeforeMethod
    public void herokuApp() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
    }

    @AfterMethod
    public void quit(){
        driver.quit();
    }

    @Test
    public void dynamicControlsPositiveTest(){
        WebElement checkbox = driver.findElement(By.cssSelector("#checkbox"));
        WebElement removeButton = driver.findElement(By.xpath("//button[text()='Remove']"));
        removeButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#checkbox")));
        Assert.assertFalse(checkbox.isSelected());
        WebElement input = driver.findElement(By.cssSelector("input[type='text']"));
        Assert.assertFalse(input.isEnabled());
        driver.findElement(By.cssSelector("button[onclick='swapInput()']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'enabled!')]")));
        Assert.assertTrue(input.isEnabled());
    }
}

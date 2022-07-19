import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DynamicControlsTest extends BaseTest {

    @BeforeMethod
    public void herokuApp() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }

    @Test
    public void dynamicControlsPositiveTest() {
        WebElement removeButton = driver.findElement(By.xpath("//button[text()='Remove']"));
        removeButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), ' gone!')]")));
        WebElement removeMessage = driver.findElement(By.xpath("//p[contains(text(), ' gone!')]"));
        Assert.assertTrue(removeMessage.isDisplayed());
        WebElement input = driver.findElement(By.cssSelector("input[type='text']"));
        Assert.assertFalse(input.isEnabled());
        driver.findElement(By.cssSelector("button[onclick='swapInput()']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'enabled!')]")));
        Assert.assertTrue(input.isEnabled());
    }
}

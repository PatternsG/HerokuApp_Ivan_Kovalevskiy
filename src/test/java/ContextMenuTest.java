import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContextMenuTest extends BaseTest {

    @BeforeMethod
    public void herokuApp() {
        driver.get("http://the-internet.herokuapp.com/context_menu");
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }

    @Test
    public void contextMenuPositiveTest() {
        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(By.cssSelector("#hot-spot"))).build().perform();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "You selected a context menu");
        alert.accept();
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FileUploadTest extends BaseTest{

    @BeforeMethod
    public void herokuApp() {
        driver.get("http://the-internet.herokuapp.com/upload");
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }

    @Test
    public void fileUploadPositiveTest() {
        By fileUpload = By.cssSelector("#file-upload");
        String input = System.getProperty("user.dir") +
                "/src/main/resources/Hohenwerfen-Castle-Austria-806x400_tcm10-156135_w806.jpg";
        driver.findElement(fileUpload).sendKeys(input);
        driver.findElement(By.cssSelector("#file-submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#uploaded-files")));
        Assert.assertEquals(driver.findElement(By.cssSelector("#uploaded-files")).getText(),
                "Hohenwerfen-Castle-Austria-806x400_tcm10-156135_w806.jpg");
    }
}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FileUploadTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void herokuApp() {
        driver.get("http://the-internet.herokuapp.com/upload");
    }

    @AfterMethod
    public void quit(){
        driver.quit();
    }

    @Test
    public void fileUploadPositiveTest(){
        By fileUpload = By.cssSelector("#file-upload");
        String input = "D:\\Programs\\QA\\HerokuApp_Ivan_Kovalevskiy\\src\\main" +
                "\\resources\\Hohenwerfen-Castle-Austria-806x400_tcm10-156135_w806.jpg";
                //если указать относительный путь - тест не проходит, файл не отправляется
        driver.findElement(fileUpload).sendKeys(input);
        driver.findElement(By.cssSelector("#file-submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#uploaded-files")));
        Assert.assertEquals(driver.findElement(By.cssSelector("#uploaded-files")).getText(),
                "Hohenwerfen-Castle-Austria-806x400_tcm10-156135_w806.jpg");
    }
}

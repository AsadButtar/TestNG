// All imports
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class EdgeTest {

    public static void SearchHotelsInEdge(String BaseURL, String Location) {

        //Declare Webdriver
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver","C:\\webdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));

        // Maximize Window
        driver.manage().window().maximize();

        // Opening BaseURL in Browser, BaseURL is passed as parameter from xml file
        driver.get(BaseURL);

        // Finding Web element and performing actions accordingly...
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("accept-all-cookies-button")));
        driver.findElement(By.id("accept-all-cookies-button")).click();
        driver.findElement(By.xpath("//*[contains(@placeholder,'Enter place, postcode or hotel')]")).sendKeys(Location);
        driver.findElement(By.id("search-console__form-button")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Hotels found']")));
        String Result = driver.findElement(By.xpath("//span[text()='Hotels found']")).getText();
        System.out.print(Result);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"hotel-card-0\"]/cta-wrapper/hotel-rate/cta-button")));
        driver.findElement(By.xpath("//*[@id=\"hotel-card-0\"]/cta-wrapper/hotel-rate/cta-button")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid=\"Standard Room__see-details\"]")));

        //Scroll into page to find hidden element
        Actions actions = new Actions(driver);
        WebElement elem = driver.findElement(By.cssSelector("[class='rate-tile rate-tile--selected'] span[class='m0']"));
        actions.moveToElement(elem);

        // Fetch Price and convert into integer
        String  Rate = driver.findElement(By.cssSelector("[class='rate-tile rate-tile--selected'] span[class='m0']")).getText();
        System.out.print(Rate);

        //Replacing all characters except numbers
        Rate = Rate.replaceAll("[^0-9]","");//Replace anything wil space other than numbers
        float Int_Rate = Float.parseFloat(Rate);
        System.out.print(Int_Rate);

    }
}

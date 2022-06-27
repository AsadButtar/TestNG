import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.time.Duration;  // All imports



public class ChromeTest {

    public  static WebDriver driver;

//    public  ChromeTest (String URI){
//        this.URI = URI;
//    };
//    public ChromeTest(){};

    public static void SearchHotelsInChrome(String browser, String BaseURL, String Location) throws IOException {

        //declare webdriver
        if(browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","webdrivers/chromedriver.exe");
            driver = new ChromeDriver(); //initialize webdriver
        }else if (browser.equals("FireFox")){
            System.setProperty("webdriver.gecko.driver","C:\\webdrivers\\geckodriver.exe");
            driver = new FirefoxDriver(); //initialize webdriver
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        driver.manage().window().maximize(); // maximizing Window

        // opening BaseURL, BaseURL is passed as parameter from xml file

//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        File yamlFile = new File(classLoader.getResource("testFile.yaml").getFile());
//        ObjectMapper om = new ObjectMapper(new YAMLFactory());
//        ChromeTest URI = om.readValue(yamlFile, ChromeTest.class);
        driver.get(BaseURL);

        // Finding WebElements and performing actions accordingly...
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("accept-all-cookies-button")));
        driver.findElement(By.id("accept-all-cookies-button")).click();
        driver.findElement(By.xpath("//*[contains(@placeholder,'Enter place, postcode or hotel')]")).sendKeys(Location); // Sending Location in Search Bar
        driver.findElement(By.id("search-console__form-button")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Hotels found']")));
        String Result = driver.findElement(By.xpath("//span[text()='Hotels found']")).getText();
        System.out.print(Result);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"hotel-card-0\"]/cta-wrapper/hotel-rate/cta-button")));
        driver.findElement(By.xpath("//*[@id=\"hotel-card-0\"]/cta-wrapper/hotel-rate/cta-button")).click();
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid=\"Standard Room__see-details\"]")));

        //Scroll into page to find hidden element
        Actions actions = new Actions(driver);
        WebElement elem = driver.findElement(By.cssSelector("[class='rate-tile rate-tile--selected'] span[class='m0']"));
        actions.moveToElement(elem);

        // Fetch Price and convert into integer
        String  Rate = driver.findElement(By.cssSelector("[class='rate-tile rate-tile--selected'] span[class='m0']")).getText();
        System.out.print(Rate);

        //Replacing all characters except numbers
        Rate = Rate.replaceAll("[^0-9.]","");//Replace anything wil space other than numbers
        float Float_Rate = Float.parseFloat(Rate);
        System.out.print(Float_Rate);
    }
}

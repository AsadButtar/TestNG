import org.testng.annotations.*;

import java.io.IOException;

public class SeleniumAssignment {



    @Test
    @Parameters({"browser","BaseURL","Location-1"})
    public void FirstSearch(String browser, String BaseURL, String Location) throws IOException {
        ChromeTest.SearchHotelsInChrome(browser,BaseURL,Location);
    }
    @Test
    @Parameters({"browser","BaseURL","Location-1"})
    public void SecondSearch(String browser, String BaseURL, String Location) throws IOException {
        ChromeTest.SearchHotelsInChrome(browser,BaseURL,Location);
    }
    @Test
    @Parameters({"browser","BaseURL","Location-1"})
    public void ThirdSearch(String browser, String BaseURL, String Location) throws IOException {
        ChromeTest.SearchHotelsInChrome(browser,BaseURL,Location);
    }

}

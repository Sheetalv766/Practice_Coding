
package apTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
///
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestCases {
    RemoteWebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);


        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {

        System.out.println("Start Test case: testCase01");

        //Launch the Amazon India website
        driver.get("https://www.amazon.in/");

        //get url
        String currentUrl = driver.getCurrentUrl();

        //expected title in url
        String expectedTitle = "amazon";

        if(currentUrl.contains(expectedTitle)){
            System.out.println("Url contains the expected title : " + expectedTitle);
        }else{
            System.out.println("Not contain : " + currentUrl);
        }

        System.out.println("end Test case: testCase01");
        
    }

    public void testCase02() {
        System.out.println("Start Test case: testCase02");

        //Step 1: Locate the search bar using its ID or class name
        WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
        System.out.println("Locate the search bar : " + searchBar);

        //Step 2 :Enter a search term (e.g., "laptop") into the search bar
        searchBar.sendKeys("laptop");

        //then submit the keys that we have to send
        searchBar.submit();

        /*Step 3: Make sure that the resulting page contains the search 
        term in at least one of the product titles or descriptions*/
        List<WebElement> productTitles = driver.findElements(By.xpath("//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div[5]/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span"));

        //then we have search the term
        String searchTerm = "laptop";
        boolean searchTermFound = false;

        for(WebElement productTitle : productTitles){
            String textOfTitle = productTitle.getText().toLowerCase();
            if(textOfTitle.contains(searchTerm)){
                searchTermFound = true;
                break;
            }
        }

        //check search term in at least one of the product titles or descriptions
        if(searchTermFound){
            System.out.println("Search term found : " + searchTerm);
        }else{
            System.out.println("Search term not found : " + searchTerm);
        }
        System.out.println("end Test case: testCase02");
    }

    public void testCase03() {
    System.out.println("Start Test case: testCase03");

    //Step 1: Click on a category from the navigation menu (e.g., "Electronics")
    WebElement navgMenu = driver.findElement(By.linkText("Electronics"));
    navgMenu.click();

    //Step 2: Make sure that the resulting page corresponds to the clicked category (e.g., the page url includes "electronics")
    String currentURL = driver.getCurrentUrl();
    String clickedCategory = "Electronics";

    //check if it resulting page corresponds to the clicked category
    if(currentURL.contains(clickedCategory.toLowerCase())){
        System.out.println("Resulting page corresponds to the clicked category : " + clickedCategory);
    }else{
        System.out.println("Resulting page does not corresponds to the clicked category : " + currentURL);
    }
    System.out.println("end Test case: testCase03");
}
}


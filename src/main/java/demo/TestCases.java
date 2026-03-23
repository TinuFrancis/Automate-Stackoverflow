package demo;

import java.time.Duration;
import java.util.List;
import java.util.ResourceBundle.Control;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    // TODO: Write Test Cases Here

    //Verify that the Stack Overflow homepage URL contains 'stackoverflow.'
    public void testCase01(){
    System.out.println("Start Test case: testCase01");
    driver.get("https://stackoverflow.com/");
    String url = driver.getCurrentUrl();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    WebElement popupElement = driver.findElement(By.id("onetrust-banner-sdk"));
    if (popupElement.isDisplayed()){
    WebElement acceptCookies = driver.findElement(By.id("onetrust-reject-all-handler"));
    acceptCookies.click();
    }
    if(url.contains("stackoverflow")){
    System.out.println("Test case passed. Url contains stackoverflow");
    }
    else{
    System.out.println("Test case failed. Url does not contain stackoverflow");
    }
    System.out.println("end Test case: testCase01");
    }



    //Search for a Specific Topic and Verify Results
    public void testCase02() throws InterruptedException{
    System.out.println("Start Test case: testCase02");
    driver.get("https://stackoverflow.com/");
    Thread.sleep(3000);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    WebElement popupElement = driver.findElement(By.id("onetrust-banner-sdk"));
    if (popupElement.isDisplayed()){
    WebElement acceptCookies = driver.findElement(By.id("onetrust-reject-all-handler"));
    acceptCookies.click();
    }
    Thread.sleep(3000);
    WebElement searchBox = driver.findElement(By.xpath("//input[@name='q']"));
    searchBox.sendKeys("Python list comprehension");

    Actions actions = new Actions(driver);
    actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
    String searchedURL = driver.getCurrentUrl();

    Thread.sleep(10000);


    String mainWindow = driver.getWindowHandle();

    driver.switchTo().newWindow(WindowType.TAB);
    driver.navigate().to(searchedURL);
    List<WebElement> answerElementTitles = driver.findElements(By.className("s-post-summary--content-title"));
    for (int i=0; i<=2; i++){
        String titleText = answerElementTitles.get(i).getText().toLowerCase();
        if(titleText.contains("python") || titleText.contains("list") || titleText.contains("comprehension")){
            System.out.println("Test case passed. Answer title contains one of the searched words");
        }
        else{
            System.out.println("Test case failed. Answer title does not contain any of the searched words");
        }
    }

    System.out.println("end Test case: testCase02");
    }


}

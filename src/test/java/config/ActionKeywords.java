package config;

import org.apache.commons.lang.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import utility.PropertyFileReader;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

/**
 * Created by vibhu on 2/14/2017.
 */
public class ActionKeywords {

    private static WebDriver driver = null;
    public static Select dropdown = null;
    static String locatorFirstFile = System.getProperty("user.dir") + "\\src\\main\\resources\\locatorsfile\\locators.properties";
    static Properties prop = PropertyFileReader.locatorFileReader(locatorFirstFile);
  //  String loc = prop.getProperty();
    public static void openBrowser(String object){

        String chromeDriverFileLocation = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverFileLocation);
        driver = new ChromeDriver();
    }

    public static void openUrl(String object) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Constants.URL);
        driver.manage().window().maximize();

    }

    public static void inputUsername(String object){

       driver.findElement(By.name(prop.getProperty(object))).sendKeys(Constants.UserName);

    }

    public static void inputPassword(String object){

       driver.findElement(By.name(prop.getProperty(object))).sendKeys(Constants.Password);

    }

    public static void clickLogin(String object){

        driver.findElement(By.xpath(prop.getProperty(object))).click();

    }

    public static void openNewbookingPage(String object) throws InterruptedException {

        Thread.sleep(10000);
        driver.findElement(By.xpath(prop.getProperty(object))).click();
    }



    public static void selectDropDownValue (String xpath) throws InterruptedException {
        Thread.sleep(3000);
        WebElement mySelectElement = driver.findElement(By.xpath(prop.getProperty(xpath)));
        dropdown= new Select(mySelectElement);

    }



    public static void selectCity(String cityXpath) throws InterruptedException {

        selectDropDownValue(cityXpath);
        dropdown.selectByVisibleText(Constants.CityToTravel);

    }

    public static void pickupLocation(String pickUpXpath) throws InterruptedException {

        selectDropDownValue(pickUpXpath);
        dropdown.selectByVisibleText(Constants.pickupLocation);

    }

    public static void dropLocation(String dropXpath) throws InterruptedException {

        selectDropDownValue(dropXpath);
        dropdown.selectByVisibleText(Constants.dropLocation);

    }

    public static void enterChargeCode (String chargecodeXpath) throws InterruptedException {
       // Thread.sleep(3000);
        driver.findElement(By.xpath(prop.getProperty(chargecodeXpath))).sendKeys(Constants.chargeCode);
    }



    public static void enterMobileNumber(String mobileXpath) throws InterruptedException {
        Thread.sleep(2000);
        String mobileNumber = driver.findElement(By.xpath(prop.getProperty(mobileXpath))).getText();
        if (mobileNumber.contains("")){
        //    driver.findElement(By.xpath(prop.getProperty(mobileXpath))).sendKeys("9999999999");

        }

    }

    public static void submitBooking (String bookingXpath) throws InterruptedException {
        driver.findElement(By.xpath(prop.getProperty(bookingXpath))).click();
    }

    public static void validateBookingCreationSuccess (String manageBookingXpath) throws InterruptedException {
        Thread.sleep(2000);
        String manageBookingHeader = driver.findElement(By.xpath(prop.getProperty(manageBookingXpath))).getText();

            assertEquals(manageBookingHeader, "Manage Bookings");

    }
    public static void closeBrowser(String object){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();

    }

}

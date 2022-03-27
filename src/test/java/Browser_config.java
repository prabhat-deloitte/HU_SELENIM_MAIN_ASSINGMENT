import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class Browser_config {
    public static WebDriver driver;
    public static String url  = ("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");

    public  static  WebDriver Driver_setup(){
        System.setProperty("WebDriver.chrome.driver", "C:\\Users\\praparihar\\IdeaProjects\\Main_Assignment_Selenium_Track\\chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }
}

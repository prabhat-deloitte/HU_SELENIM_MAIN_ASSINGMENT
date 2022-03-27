import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.Driver;

public class Home_Page extends Browser_config{
  WebDriver driver  =  Browser_config.Driver_setup();

  String url = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";
  By customer = By.xpath("//*[text() = \"Customer Login\"]");
  By manager_login = By.xpath("//*[text() = \"Bank Manager Login\"]");


  public void click_customer(){
      driver.findElement(customer).click();

  }

    public void click_manager(){
        driver.findElement(manager_login).click();

    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Transaction extends Browser_config {
    By transaction_button = By.xpath("(//div[@class ='center'])[2]/child::*[1]");
    By Table_selection = By.xpath("//table[@class = 'table table-bordered table-striped']/child::tbody");

    public void Click_Transaction(){
        driver.findElement(transaction_button).click();
    }
    public WebElement Transaction_Table(){
       WebElement element1 =  driver.findElement(Table_selection);
       return element1;
    }

}

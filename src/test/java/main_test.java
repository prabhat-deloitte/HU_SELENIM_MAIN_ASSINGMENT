import jdk.swing.interop.SwingInterOpUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class main_test extends Browser_config {
    Home_Page home = new Home_Page();
    Add_customer customer = new Add_customer();
    Open_Account account = new Open_Account();
    Deposit_dollars deposit = new Deposit_dollars();
    @Test(priority =1)
    public void cLick_customer()
    {   driver.get(home.url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        home.click_customer();
    }
     @Test(priority =2 )
    public void click_manager(){
        driver.get(home.url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        home.click_manager();
     }
     @Test(priority =3 )
     public void Add_Customer(){
        customer.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        home.click_manager();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        customer.Click_Add_customer();
        customer.Enter_data();

    }
    @Test(priority  =4)
    public void Open_Account(){
        /*driver.get(home.url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        home.click_manager();*/
        account.Click_Open_account().click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        for(int i = 0; i<account.read_data().size(); i+=2) {
            for (int j = 1; j<=3; j++){
                String name = account.read_data().get(i) + " " +account.read_data().get(i+1);
                driver.findElement(By.xpath(account.Customer_Drop_Down().replace("prev_name", name))).click();
                account.Currency_select().selectByIndex(j);
                account.click_proceed();
                driver.switchTo().alert().accept();
            }

        }deposit.Click_home().click();

    }
    @Test(priority = 5)
    public void Check_deposit(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        for(int i = 0; i<deposit.read_data().size(); i+=3){
            deposit.Click_home().click();
        home.click_customer();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String name = deposit.read_data().get(i) + " " +deposit.read_data().get(i+1);
        deposit.select_name().selectByVisibleText(name);
        deposit.click_login();
        deposit.click_deposit();
        deposit.click_Amount_input().sendKeys(deposit.read_data().get(i+2));
        deposit.click_Final_deposit();
        Assert.assertEquals(deposit.read_data().get(i+2), deposit.get_amount_displayed());
        Boolean verify_message = deposit.message_check().equalsIgnoreCase("Deposit Successful");
        Assert.assertTrue(verify_message);

        }



    }


    //@AfterTest
    public void Close(){
        driver.quit();
     }}


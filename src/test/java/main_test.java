import jdk.swing.interop.SwingInterOpUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class main_test extends Browser_config {
    Home_Page home = new Home_Page();
    Add_customer customer = new Add_customer();
    Open_Account account = new Open_Account();
    Deposit_dollars deposit = new Deposit_dollars();
    Withdrawl_dollars withdraw = new Withdrawl_dollars();
    Transaction tran = new Transaction();
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

        }

    }
    @Test(priority = 5,dependsOnMethods = {"Open_Account"})
    public void Check_deposit(){

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        assertTrue(verify_message);

        }//withdraw.click_withdraw_main();

    }
    @Test(priority =  6,dependsOnMethods={"Check_deposit"})
    public void Check_Withdrawals(){
      try {
          withdraw.click_withdraw_main();
          int prev_balance = Integer.parseInt(deposit.get_amount_displayed());
          WebDriverWait wait = new WebDriverWait(driver, 30);
          wait.until(ExpectedConditions.visibilityOfElementLocated(withdraw.Amount_withdraw));
          withdraw.Click_withdraw_amount().click();
          Thread.sleep(10000);
          withdraw.Click_withdraw_amount().sendKeys("1000");
          String xpath1 = "//span[text()='Transaction successful']";
          withdraw.click_withdraw_final();
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath1)));
          int updated_balance = Integer.parseInt(deposit.get_amount_displayed());
          Assert.assertEquals("1000",String.valueOf(prev_balance-updated_balance));
      }catch(Exception e){
          System.out.println(e);
      }

    }


    @Test(priority = 7,dependsOnMethods={"Check_Withdrawals"})
    public void Check_Invalid_withdraw(){
        try {
            withdraw.click_withdraw_main();
            int prev_balance = Integer.parseInt(deposit.get_amount_displayed());
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(withdraw.Amount_withdraw));

            withdraw.Click_withdraw_amount().click();
            Thread.sleep(10000);
            withdraw.Click_withdraw_amount().sendKeys("11000");
            withdraw.click_withdraw_final();
            Assert.assertEquals(driver.findElement(withdraw.Withdrawal_error_message).getText(),("Transaction Failed. You can not withdraw amount more than the balance."));


        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Test(priority = 8,dependsOnMethods={"Check_Withdrawals"})
    public void Check_transaction(){
        tran.Click_Transaction();
        assertTrue(driver.findElement(tran.Table_selection).isDisplayed());

    }

    @AfterTest
    public void Close(){
        driver.quit();
     }}


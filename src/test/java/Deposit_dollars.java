import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Deposit_dollars extends Browser_config {
    By Home_button = By.xpath("//button[contains(text(),'Home')]");
    By Select_user = By.xpath("//select[@id = 'userSelect']");
    By Login_button = By.xpath("//button[text()='Login']");
    By deposit_button = By.xpath("(//button[@class='btn btn-lg tab'])[2]");
    By Amount_input = By.xpath("//input[@type = 'number']");
    By final_deposit = By.xpath("//button[text() = 'Deposit']");
    By Balance_amount = By.xpath("//div[@class=  'center']/child::strong[@class='ng-binding'][2]");
    By message = By.xpath("//span[text()='Deposit Successful']");


    public WebElement Click_home(){
        return driver.findElement(Home_button);
    }

    public ArrayList<String> read_data(){
        ArrayList<String> name_list = new ArrayList<>();
        String Excel_file_path = ("C:\\Users\\praparihar\\Desktop\\customer.xlsx");
        try {
            FileInputStream fis = new FileInputStream(Excel_file_path);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = null;
            XSSFCell cell = null;
            String first_name = null;
            String Last_name = null;
            String Deposit = null;
            for(int i=1; i<= sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                for (int j =0; j<=row.getLastCellNum(); j++){
                    cell = row.getCell(j);
                    if (j==0){
                        first_name = cell.getStringCellValue();
                    }
                    if (j==1){
                        Last_name = cell.getStringCellValue();
                    }
                    if (j==3){
                        Deposit = cell.getStringCellValue();
                    }

                }name_list.add(first_name);
                name_list.add(Last_name);
                name_list.add(Deposit);


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }return name_list;
    }
    public Select select_name(){
        Select name = new Select(driver.findElement(Select_user));
        return name;
    }
    public void click_login(){
        driver.findElement(Login_button).click();
    }
    public void click_deposit(){
        driver.findElement(deposit_button).click();
    }
    public WebElement click_Amount_input(){
        return driver.findElement(Amount_input);
    }
    public void click_Final_deposit(){
        driver.findElement(final_deposit).click();

    }
    public String get_amount_displayed(){
        String amount = driver.findElement(Balance_amount).getText();
        return amount;
    }
    public String message_check(){
        String mess = driver.findElement(message).getText();
        return mess;
    }
}




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

public class Withdrawl_dollars extends Browser_config {
    By withdrawl_button = By.xpath("(//div[@class ='center'])[2]/child::*[3]");
    By Amount_withdraw = By.xpath("//label['Amount to be Withdrawn :']/following-sibling::input");
    By final_Withdraw_button = By.xpath("//button[@type = 'submit']");
    By Withdrawal_error_message =By.xpath("//span[text()='Transaction Failed. You can not withdraw amount more than the balance.']");

    public void click_withdraw_main(){
        driver.findElement(withdrawl_button).click();
    }
    public WebElement Click_withdraw_amount(){
        WebElement element1 = driver.findElement(Amount_withdraw);
        return element1;
    }
    public void click_withdraw_final(){
        driver.findElement(final_Withdraw_button).click();
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
            String Withdraw = null;
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
                    if (j==4){
                        Withdraw = cell.getStringCellValue();
                    }

                }name_list.add(first_name);
                name_list.add(Last_name);
                name_list.add(Withdraw);


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }return name_list;
    }
}

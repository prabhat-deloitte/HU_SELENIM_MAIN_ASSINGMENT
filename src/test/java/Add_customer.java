import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.schema.FileResourceLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Add_customer extends Browser_config{
   // WebDriver driver = Browser_config.Driver_setup();
    By add_customer = By.xpath("(//button[@class = \"btn btn-lg tab\"])[1]");

    By Customer_table = By.xpath("(//button[@class = \"btn btn-lg tab\"])[3]");
    By First_name = By.xpath("//input[@placeholder='First Name']");
    By last_name = By.xpath("//input[@placeholder='Last Name']");
    By Post_code = By.xpath("//input[@placeholder='Post Code']");
    By Add_button = By.xpath("//button[text()='Add Customer']");


    public void click(){
        driver.get(url);

    }
    public void Click_Add_customer(){
        driver.findElement(add_customer).click();

    }
    public WebElement First_name(){
        return driver.findElement(First_name);
    }
    public WebElement Last_name(){
        return driver.findElement(last_name);
    }
    public WebElement post_code(){
        return driver.findElement(Post_code);
    }
    public void Enter_data(){
        String Excel_file_path = ("C:\\Users\\praparihar\\Desktop\\customer.xlsx");
        try {
            FileInputStream fis = new FileInputStream(Excel_file_path);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = null;
            XSSFCell cell = null;
            String first_name = null;
            String Last_name = null;
            String Postal_code = null;
            //String windowHandle=driver.getWindowHandle();
            for(int i=1; i<= sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                for (int j =0; j< row.getLastCellNum(); j++){
                    cell = row.getCell(j);
                    if (j==0){
                        first_name = cell.getStringCellValue();
                    }
                    if (j==1){
                        Last_name = cell.getStringCellValue();
                    }
                    if (j==2){
                        Postal_code = cell.getStringCellValue();
                    }

                }
                driver.findElement(First_name).sendKeys(first_name);
                driver.findElement(last_name).sendKeys(Last_name);
                driver.findElement(Post_code).sendKeys(Postal_code);
                driver.findElement(Add_button).click();
                driver.switchTo().alert().accept();


            }
            //driver.switchTo().window(windowHandle);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //
}

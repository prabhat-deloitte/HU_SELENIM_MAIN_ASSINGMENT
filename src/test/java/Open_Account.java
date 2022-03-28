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

public class Open_Account extends Browser_config {
    By Open_account = By.xpath("(//button[@class='btn btn-lg tab'])[1]");
    By Customer_dropdown = By.xpath("//select[@name = 'userSelect']");
    By currency_type = By.xpath(("//select[@name = 'currency']"));
    By process_button = By.xpath("//button[text()='Process']");


    public WebElement Click_Open_account(){
        return driver.findElement(Open_account);
    }

    public ArrayList<String> read_data(){
        ArrayList<String> list1 = new ArrayList<>();
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
                    /*if (j==2){
                        Postal_code = cell.getStringCellValue();
                    }*/

                }list1.add(first_name);
                list1.add(Last_name);



            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }return list1;
    }
    public String Customer_Drop_Down(){
        String Dynamic_xpath = "//option[text() = \"prev_name\"]";
        return  Dynamic_xpath;

    }
    public Select Currency_select(){
        Select curr_sel= new Select(driver.findElement(currency_type));
        return curr_sel;
    }

    public void click_proceed(){
        driver.findElement(process_button).click();
    }
}

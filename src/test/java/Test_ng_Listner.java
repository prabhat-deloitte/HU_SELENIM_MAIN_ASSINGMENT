import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;


public class Test_ng_Listner extends  Browser_config implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String filepath = "C:\\Users\\praparihar\\IdeaProjects\\Main_Assignment_Selenium_Track\\src\\utils\\Screenshot_folder\\";
        File destination = new File(filepath+result.getName()+".png");
        try{
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onTestFailure(ITestResult result) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String filepath = "C:\\Users\\praparihar\\IdeaProjects\\Main_Assignment_Selenium_Track\\src\\utils\\Screenshot_folder\\Screenshot.png";
            File destination = new File(filepath);
            try{
                FileUtils.copyFile(src, destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test_case_sucessfull"+result.getTestName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext result) {

    }

    @Override
    public void onFinish(ITestContext result) {

    }
}

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.File;
import java.io.IOException;


public class Test_ng_Listner extends  Browser_config implements ITestListener {
    log4j_file log = new log4j_file();
    Extent_Manager manager =new Extent_Manager();
    public static ExtentReports extent;
    public static ExtentTest test;


    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(""+result.getName());

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
            log.logger.error("There is Error in Copying File");

            e.printStackTrace();
        }
        test.pass("the test case is passed ");
        log.logger.info("The Screenshot is Taken for success of  " + result.getName());


    }

    @Override
    public void onTestFailure(ITestResult result) {

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String filepath = "C:\\Users\\praparihar\\IdeaProjects\\Main_Assignment_Selenium_Track\\src\\utils\\Screenshot_folder_Failure\\";
        File destination = new File(filepath+result.getName()+".png");
        try{
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            log.logger.error("There is some error copying the file");
            e.printStackTrace();
        }
        test.fail("The test is failed ");
        log.logger.info("The Screenshot is Taken for Failure of " + result.getName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.logger.info("The Test case is skipped "+result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext result) {
        extent = Extent_Manager.Extent_reporter();
        log.logger.info("The Test "+result.getName()+" started");

    }

    @Override
    public void onFinish(ITestContext result) {
        extent.flush();
        log.logger.info("The Testing is Completed");

    }
}

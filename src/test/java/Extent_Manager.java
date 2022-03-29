import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extent_Manager {

    public static ExtentReports Extent_reporter() {
        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter("C:\\Users\\praparihar\\IdeaProjects\\Main_Assignment_Selenium_Track\\logs\\extents.html");
        extentReports.attachReporter(reporter);
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("XYZ_Bank");
        reporter.config().setReportName("XYZ_Extent_report");
        return extentReports;
    }
}

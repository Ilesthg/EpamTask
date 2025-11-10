package reportss;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import constants.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class ExtentReportsClass {

    private ExtentReportsClass() {
    }


    public static ExtentReports setUpExtentReports() {


        ExtentReports extent = new ExtentReports();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyy HH/mm/ss");
        Date date = new Date();
        String ssDate = format.format(date);


        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(Constants.getRouteDir());


        extent.attachReporter(sparkReporter);


        extent.setSystemInfo("Hostname", "RHEL8");
        extent.setSystemInfo("Username", "root");
        extent.setSystemInfo("Executed By User: ", System.getProperty("user.name"));

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Automation Test results");


        return extent;
    }

   /* public static ExtentTest createTest(String testName){
        ExtentTest test = setUpExtentReports().createTest(testName);// here setUpExtentReports wont work cause its not initialazed, bc it will init on listener
        return test;
    }*/


}

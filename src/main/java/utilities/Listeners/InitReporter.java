package utilities.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reportss.ExtentReportsClass;
import reportss.ExtentTestSingleton;

import java.util.Objects;

public class InitReporter {
    private ExtentReports extentReports;
    private ExtentTest logger;

    public  void testStart(ITestResult result){
        logger = extentReports.createTest(result.getMethod().getMethodName());// For each test create his own logger(Extent test)
        ExtentTestSingleton.getInstance().setExtentTest(logger);//********ExtentTest which will log into Report*********//

    }
    public void onTestPass(ITestResult result){
        if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test case SUCCESS", ExtentColor.GREEN));
        }
    }
    public void setUpReporter() {
        extentReports = ExtentReportsClass.setUpExtentReports();//Init the extent Report
    }

    public void endReporter() {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();// WE NEED TO FLUSH REPORTS, IF WE forget report wont be created
        }
    }

    public void onTestFail(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test case Failed", ExtentColor.RED));
        }

    }
}

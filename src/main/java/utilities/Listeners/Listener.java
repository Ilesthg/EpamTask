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

public class Listener implements ITestListener {
    private ExtentReports extentReports;
    private ExtentTest logger;


    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);

        logger = extentReports.createTest(result.getMethod().getMethodName());// For each test create his own logger(Extent test)
        ExtentTestSingleton.getInstance().setExtentTest(logger);//********ExtentTest which will log into Report*********//

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test case SUCCESS", ExtentColor.GREEN));
        }

         ExtentTestSingleton.getInstance().removeExtentObject();//*****************//
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        extentReports = ExtentReportsClass.setUpExtentReports();//Init the extent Report
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
    }
    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);

    }
    /*
    Below are override methods from interface but this can be erased, cause on interface this abstract methods are also default
   meaning we can leave unimplemented and no compilation error will appear


    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }*/
}

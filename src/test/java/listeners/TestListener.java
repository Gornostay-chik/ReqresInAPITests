package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class TestListener implements ITestListener {
    private static final Logger infoLogger = LogManager.getLogger();

    @Override
    public void onTestStart(ITestResult result){
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        Test test = method.getAnnotation(Test.class);
        String testname = test.testName();
        infoLogger.info("[START]: " + testname + ": " + result.getMethod().getDescription());
    }



}

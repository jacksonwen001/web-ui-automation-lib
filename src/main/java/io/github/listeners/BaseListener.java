package io.github.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNG;

public class BaseListener implements ITestListener {
    Logger logger = LoggerFactory.getLogger(BaseListener.class);
    private static       int count  = 0;
    private final static int maxTry = 1;
    @Override
    public void onTestFailure(ITestResult result) {
        if (count < maxTry) {
            count++;
            TestNG tng = new TestNG();
            tng.setDefaultTestName("RETRY TEST");
            Class[] classes1 = { result.getTestClass().getRealClass() };
            tng.setTestClasses(classes1);
            tng.addListener(new AllureReportListener());
            logger.info("Rerun!!!!");
            tng.run();
        }
    }
}

package io.github.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestNG;

public class RetryListener implements ITestListener {
    Logger logger = LoggerFactory.getLogger(RetryListener.class);
    @Override
    public void onTestFailure(ITestResult result) {
        TestNG tng = new TestNG();
        tng.setDefaultTestName("RETRY TEST CASE");
        Class[] classes1 = { result.getTestClass().getRealClass() };
        tng.setTestClasses(classes1);
        tng.addListener(new AllureReportListener());
        tng.run();
    }
}

package io.github.listeners;

import io.github.core.BaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import static io.github.core.BaseAllure.*;

/**
 * author: jackson
 */
public class AllureReportListener implements ITestListener {
    static Logger logger = LoggerFactory.getLogger(AllureReportListener.class);
    private BaseConfig config = new BaseConfig();

    @Override
    public void onStart(ITestContext iTestContext) {
        logger.info("Starting Test Suite '" + iTestContext.getName() + "'.......");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger.info("Finished Test Suite '" + iTestContext.getName() + "'");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info("Starting Test Method '" + getTestMethodName(iTestResult) + "'");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info("Test Method '" + getTestMethodName(iTestResult) + "' is Passed");
        attachServerVideo();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.error("Test Method '" + getTestMethodName(iTestResult) + "' is Failed");
        saveScreenshotPNG();
        attachServerVideo();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.warn("Test Method '" + getTestMethodName(iTestResult) + "' is Skipped");
        attachServerVideo();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {}

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    private void attachServerVideo(){
        if (config.isServer()) {
            attachVideo(config.selenoidPortalUrl());
        }
    }

}

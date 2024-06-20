package com.Listeners;

import com.Implementations.ReportRepository;
import com.Repository.IReportRepository;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class SauceDemoTestListener implements ITestListener {
    private final IReportRepository _reportRepository;
    private ExtentTest extentTest;

    public SauceDemoTestListener(){
        _reportRepository = new ReportRepository();
    }

    public void onStart(ITestContext result){
        _reportRepository.generateReport("SauceDemo - Report","SauceDemo - Report", Theme.DARK);
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = _reportRepository.getExtentReport().createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, "Test Case: [" + result.getMethod().getMethodName() + "] failed");
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Case: [" + result.getMethod().getMethodName() + "] passed");
    }

    @Override
    public void onFinish(ITestContext result){
        _reportRepository.getExtentReport().flush();
    }
}
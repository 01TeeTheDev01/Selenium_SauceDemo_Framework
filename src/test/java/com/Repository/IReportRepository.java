package com.Repository;

import com.Enums.ReportFileType;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.configuration.Theme;

public interface IReportRepository {
    void generateReport(String documentTitle, String reportName, Theme theme);
    void generateReport(String documentTitle, String reportName, Theme theme,  ReportFileType reportFileType);
    ExtentReports getExtentReport();
}
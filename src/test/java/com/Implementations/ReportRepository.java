package com.Implementations;

import com.Enums.ReportFileType;
import com.Helpers.UtilHelper;
import com.Repository.IReportRepository;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.lang3.NotImplementedException;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class ReportRepository implements IReportRepository {

    private final ExtentReports _extentReports;

    public ReportRepository(){
        _extentReports = new ExtentReports();
    }

    @Override
    public void generateReport(String documentTitle, String reportName, Theme theme) {
        try{
            ExtentSparkReporter _extentSparkReporter = new ExtentSparkReporter(reportSavePath(reportName));
            _extentReports.attachReporter(_extentSparkReporter);
            _extentSparkReporter.config().setDocumentTitle(documentTitle);
            _extentSparkReporter.config().setReportName(reportName);
            _extentSparkReporter.config().setTheme(theme);
            _extentReports.setSystemInfo("Operating System", UtilHelper.OS);
            _extentReports.setSystemInfo("Architecture", UtilHelper.ARCH);
            _extentReports.setSystemInfo("Java version", UtilHelper.JAVA_VERSION);
            _extentReports.setSystemInfo("Created By", UtilHelper.REPORT_CREATOR);
            _extentReports.setSystemInfo("Date", UtilHelper.CURRENT_DATE_TIME.format(DateTimeFormatter.ISO_DATE));
        }catch (Exception error){
            System.out.printf("%s error: %s", ReportRepository.class.getName(), error.getMessage());
        }
    }

    @Override
    public void generateReport(String documentTitle, String reportName, Theme theme, ReportFileType reportFileType) {
        switch (reportFileType){
            case HTML:
                throw new NotImplementedException("Generate report message: Default export file type!");

            case PDF:
                throw new NotImplementedException("Generate report message: Feature not implemented. Export failed!");

            default:
                throw new NotImplementedException("Generate report message: Unknown format. Export failed!");
        }
    }

    @Override
    public ExtentReports getExtentReport() {
        return _extentReports;
    }

    private String reportSavePath(String reportName){
        return Path.of(UtilHelper.REPORT_PATH, reportName + UtilHelper.REPORT_HTML_FILE_EXT).toAbsolutePath().toString();
    }
}
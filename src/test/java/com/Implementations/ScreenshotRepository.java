package com.Implementations;

import com.Enums.ScreenShotFileType;
import com.Helpers.UtilHelper;
import com.Models.ScreenshotModel;
import com.Repository.IScreenshotRepository;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotRepository implements IScreenshotRepository {

    private final TakesScreenshot _takeScreenshot;

    public ScreenshotRepository(WebDriver driver){
        _takeScreenshot = (TakesScreenshot) driver;
    }

    @Override
    public void takeScreenShot(String screenshotName, LocalDateTime localDateTime, ScreenShotFileType screenShotFileType) {
        try{
            var sourceImage = _takeScreenshot.getScreenshotAs(OutputType.FILE);
            var dateTimeFormat = formatDateTime(localDateTime);
            var fullFileName = setFullImageName(screenshotName, dateTimeFormat, screenShotFileType);
            var screenshot = new ScreenshotModel(sourceImage, dateTimeFormat, fullFileName);
            var destinationImage = new File(UtilHelper.SCREENSHOT_PATH, screenshot.getScreenshotFullName());
            FileUtils.copyFile(sourceImage, destinationImage);
        }catch (Exception error){
            System.out.printf("Screenshot error: %s", error.getMessage());
        }
    }

    private String formatDateTime(LocalDateTime localDateTime){
        var date = localDateTime.toLocalDate().format(DateTimeFormatter.ISO_DATE);
        var time = localDateTime.toLocalTime();
        return String.format("%s_%d_%d_%d", date, time.getHour(), time.getMinute(), time.getSecond());
    }

    private String setFullImageName(String screenshotName, String formatDateTime, ScreenShotFileType screenShotFileType){
        return String.format("%s__%s.%s", screenshotName, formatDateTime, screenShotFileType.name()).trim().toLowerCase();
    }
}
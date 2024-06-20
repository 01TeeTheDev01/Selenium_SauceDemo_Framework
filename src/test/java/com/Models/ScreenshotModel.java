package com.Models;

import java.io.File;

public class ScreenshotModel {
    private final File _screenshotFile;
    private final String _dateTime;
    private final String _screenshotFullName;

    public ScreenshotModel(File screenshotFile, String dateTime, String screenshotFullName) {
        _screenshotFile = screenshotFile;
        _dateTime = dateTime;
        _screenshotFullName = screenshotFullName;
    }

    public File getScreenshotFile() {
        return _screenshotFile;
    }

    public String getDateTime() {
        return _dateTime;
    }

    public String getScreenshotFullName() {
        return _screenshotFullName;
    }
}
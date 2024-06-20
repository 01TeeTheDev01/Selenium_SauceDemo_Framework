package com.Repository;

import com.Enums.ScreenShotFileType;

import java.time.LocalDateTime;

public interface IScreenshotRepository {
    void takeScreenShot(String screenshotName, LocalDateTime localDateTime, ScreenShotFileType screenShotFileType);
}


package com.Helpers;

import java.time.LocalDateTime;

public class UtilHelper {
    public static final String SAUCE_DEMO_URL = "https://www.saucedemo.com/";
    public static final String WORKSHEET_DATAFILE_PATH = "src/test/java/com/Data/data.xlsx";
    public static final String SCREENSHOT_PATH = "Screenshots";
    public static final String REPORT_PATH = "Reports";
    public static final String WORKSHEET_TITLE = "Login Details";
    public static final String OS = System.getProperty("os.name");
    public static final String ARCH = System.getProperty("os.arch");
    public static final String REPORT_CREATOR = "Tshego";
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final int BROWSER_DELAY_IN_SECONDS = 30;
    public static final LocalDateTime CURRENT_DATE_TIME = LocalDateTime.now();
    public static final String REPORT_HTML_FILE_EXT = ".html";
    public static final int HALT_INPUT_DELAY_IN_MILLISECONDS = 850;
    public static final int PRODUCT_INDEX = 5;

}
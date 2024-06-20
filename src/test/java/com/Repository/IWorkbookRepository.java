package com.Repository;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface IWorkbookRepository {
    void initializeWorkbook();
    XSSFSheet getWorksheet();
}
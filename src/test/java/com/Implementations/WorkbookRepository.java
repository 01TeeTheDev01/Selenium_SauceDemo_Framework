package com.Implementations;

import com.Repository.IWorkbookRepository;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkbookRepository implements IWorkbookRepository {

    private XSSFWorkbook _workBook;

    private final String _path, _workSheetName;

    public WorkbookRepository(String path, String workSheetName){
        _path = path;
        _workSheetName = workSheetName;
    }

    @Override
    public void initializeWorkbook() {
        try{
            _workBook = new XSSFWorkbook(_path);
        }catch(Exception error){
            System.out.printf("Error message: %s", error.getMessage());
        }
    }

    @Override
    public XSSFSheet getWorksheet() {
        if(_workBook == null)
            return null;

        return _workBook.getSheet(_workSheetName);
    }
}
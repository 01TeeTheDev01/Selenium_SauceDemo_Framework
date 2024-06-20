package com.Implementations;

import com.Models.UserModel;
import com.Repository.IUserRepository;
import com.Repository.IWorkbookRepository;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class UserRepository implements IUserRepository {

    private final XSSFSheet _workSheet;

    private final UserModel _userModel;

    public UserRepository(IWorkbookRepository workbookRepository, UserModel userModel){
        workbookRepository.initializeWorkbook();
        _workSheet = workbookRepository.getWorksheet();
        _userModel = userModel;
    }

    @Override
    public String getUserName(int rowIndex, int colIndex) {
        if(_workSheet == null)
            return "Unable to locate worksheet! Operation failed!";

        String username = null;

        try{
            username = _workSheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
        }catch (Exception error){
            username = "not found";
        }

        _userModel.setUserName(username);

        return _userModel.getUserName();
    }

    @Override
    public String getUserPassword(int rowIndex, int colIndex) {
        if(_workSheet == null)
            return "Unable to locate worksheet! Operation failed!";

        String password = null;

        try{
            password = _workSheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
        }catch (Exception error){
            password = "not found";
        }

        _userModel.setUserPassword(password);

        return _userModel.getUserPassword();
    }
}

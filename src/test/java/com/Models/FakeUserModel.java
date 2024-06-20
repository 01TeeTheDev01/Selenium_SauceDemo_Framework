package com.Models;

public class FakeUserModel {
    private final String _firstName;
    private final String _lastName;
    private final String _zipCode;

    public FakeUserModel(String firstName, String lastName, String zipCode){
        _firstName = firstName;
        _lastName = lastName;
        _zipCode = zipCode;
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public String getZipCode() {
        return _zipCode;
    }
}

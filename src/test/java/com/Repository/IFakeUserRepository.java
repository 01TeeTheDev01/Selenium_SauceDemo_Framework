package com.Repository;

import com.Models.FakeUserModel;

public interface IFakeUserRepository {
    void generateFakeShippingInfo();
    FakeUserModel getFakeUserDetails();
}


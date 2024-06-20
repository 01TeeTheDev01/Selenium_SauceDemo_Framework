package com.Implementations;

import com.Models.FakeUserModel;
import com.Repository.IFakeUserRepository;
import com.github.javafaker.Faker;

public class FakeUserRepository implements IFakeUserRepository {
    private FakeUserModel _fakeUser;
    private final Faker _faker;

    public FakeUserRepository(Faker faker){
        _faker = faker;
    }

    @Override
    public void generateFakeShippingInfo() {
        if(_faker == null){
            System.out.printf("%s generator error: Required object not in a valid state! Failed to generate user details!", FakeUserRepository.class.getName());
            return;
        }

        _fakeUser = new FakeUserModel(_faker.name().firstName(), _faker.name().lastName(), _faker.address().zipCode());
    }

    @Override
    public FakeUserModel getFakeUserDetails() {
        if(_fakeUser == null)
            return null;

        return _fakeUser;
    }
}

package com.example.biblioteis;

import com.example.biblioteis.API.models.User;
import com.example.biblioteis.API.repository.BookRepository;

import java.util.List;

public class LoginApiCallback implements BookRepository.ApiCallback<List<User>> {
    @Override
    public void onSuccess(List<User> result) {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}

package com.trunip.friendSuggester.service;

import com.trunip.friendSuggester.exception.BaseException;
import com.trunip.friendSuggester.request.CreateUserRequest;

public interface UserValidatorService {
    void validateCreateUserRequest(CreateUserRequest createUserRequest) throws BaseException;
}

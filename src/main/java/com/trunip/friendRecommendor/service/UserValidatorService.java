package com.trunip.friendRecommendor.service;

import com.trunip.friendRecommendor.exception.BaseException;
import com.trunip.friendRecommendor.request.CreateUserRequest;

public interface UserValidatorService {
    void validateCreateUserRequest(CreateUserRequest createUserRequest) throws BaseException ;
}

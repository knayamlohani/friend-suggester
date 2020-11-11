package com.trunip.friendRecommendor.service.impl;

import com.trunip.friendRecommendor.exception.BadRequestException;
import com.trunip.friendRecommendor.exception.BaseException;
import com.trunip.friendRecommendor.request.CreateUserRequest;
import com.trunip.friendRecommendor.service.UserValidatorService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("UserValidatorServiceImpl")
public class UserValidatorServiceImpl implements UserValidatorService {
    @Override
    public void validateCreateUserRequest(CreateUserRequest createUserRequest) throws BaseException {
        if(createUserRequest == null) {
            throw new BadRequestException("request cannot be null");
        }


        if(StringUtils.isEmpty(createUserRequest.getUsername())) {
            throw new BadRequestException("username cannot be null/empty");
        }
    }
}

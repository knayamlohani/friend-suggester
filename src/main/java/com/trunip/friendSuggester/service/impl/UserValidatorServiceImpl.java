package com.trunip.friendSuggester.service.impl;

import com.trunip.friendSuggester.exception.BadRequestException;
import com.trunip.friendSuggester.exception.BaseException;
import com.trunip.friendSuggester.request.CreateUserRequest;
import com.trunip.friendSuggester.service.UserValidatorService;
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

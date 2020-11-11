package com.trunip.friendSuggester.service.impl;


import com.trunip.friendSuggester.constant.Status;
import com.trunip.friendSuggester.exception.BaseException;
import com.trunip.friendSuggester.response.BaseResponse;
import com.trunip.friendSuggester.service.ExceptionHelperService;
import org.springframework.stereotype.Service;

@Service("ExceptionHelperServiceImpl")
public class ExceptionHelperServiceImpl implements ExceptionHelperService {

    @Override
    public BaseResponse getBaseResponseFromBaseException(BaseException exception) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(Status.FAILURE);
        baseResponse.setReason(exception.getMessage());

        return baseResponse;
    }

    @Override
    public BaseResponse getBaseResponseFromException(Exception exception) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(Status.FAILURE);
        baseResponse.setReason(exception.getMessage());
        return baseResponse;
    }
}

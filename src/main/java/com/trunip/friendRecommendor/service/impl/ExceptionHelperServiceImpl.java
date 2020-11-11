package com.trunip.friendRecommendor.service.impl;


import com.trunip.friendRecommendor.constant.Status;
import com.trunip.friendRecommendor.exception.BaseException;
import com.trunip.friendRecommendor.response.BaseResponse;
import com.trunip.friendRecommendor.service.ExceptionHelperService;
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

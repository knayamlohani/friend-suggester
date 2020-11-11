package com.trunip.friendRecommendor.service;

import com.trunip.friendRecommendor.exception.BaseException;
import com.trunip.friendRecommendor.response.BaseResponse;

public interface ExceptionHelperService {
    BaseResponse getBaseResponseFromBaseException(BaseException exception);

    BaseResponse getBaseResponseFromException(Exception exception);
}

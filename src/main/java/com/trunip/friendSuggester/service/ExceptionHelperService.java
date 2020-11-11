package com.trunip.friendSuggester.service;

import com.trunip.friendSuggester.exception.BaseException;
import com.trunip.friendSuggester.response.BaseResponse;

public interface ExceptionHelperService {
    BaseResponse getBaseResponseFromBaseException(BaseException exception);

    BaseResponse getBaseResponseFromException(Exception exception);
}

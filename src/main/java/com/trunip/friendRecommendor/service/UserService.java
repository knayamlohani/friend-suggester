package com.trunip.friendRecommendor.service;

import com.trunip.friendRecommendor.exception.BaseException;
import com.trunip.friendRecommendor.request.CreateUserRequest;
import com.trunip.friendRecommendor.response.*;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest) throws BaseException;

    BaseResponse processFriendRequest(String from, String to) throws BaseException;

    GetPendingFriendRequestsResponse getPendingFriendRequestsOfUser(String username) throws BaseException;

    GetAllFriendsOfUserResponse getAllFriendsOfUser(String username) throws BaseException;

    GetSuggestionForUserResponse getSuggestionsForUser(String username) throws BaseException;
}

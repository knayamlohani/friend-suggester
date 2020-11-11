package com.trunip.friendSuggester.service;

import com.trunip.friendSuggester.exception.BaseException;
import com.trunip.friendSuggester.request.CreateUserRequest;
import com.trunip.friendSuggester.response.*;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest createUserRequest) throws BaseException;

    BaseResponse processFriendRequest(String from, String to) throws BaseException;

    GetPendingFriendRequestsResponse getPendingFriendRequestsOfUser(String username) throws BaseException;

    GetAllFriendsOfUserResponse getAllFriendsOfUser(String username) throws BaseException;

    GetSuggestionForUserResponse getSuggestionsForUser(String username) throws BaseException;
}

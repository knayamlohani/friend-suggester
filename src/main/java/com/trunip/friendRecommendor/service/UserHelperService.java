package com.trunip.friendRecommendor.service;

import com.trunip.friendRecommendor.request.CreateUserRequest;
import com.trunip.friendRecommendor.request.User;
import com.trunip.friendRecommendor.response.CreateUserResponse;

import java.util.List;

public interface UserHelperService {
    User getUserFromCreateUserRequest(CreateUserRequest createUserRequest);

    CreateUserResponse getCreateUserResponseFromUser(User user);

    boolean checkIfUserInPendingRequest(String source, String toFind);

    void makeFriends(String firstUser, String secondUser);

    boolean checkIfUserExists(String username);

    // do a breadth first search traversal from source to find friend recommendation
    List<String> getSuggestionsForUser(String username);
}

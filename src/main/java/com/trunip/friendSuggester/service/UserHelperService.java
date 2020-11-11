package com.trunip.friendSuggester.service;

import com.trunip.friendSuggester.request.CreateUserRequest;
import com.trunip.friendSuggester.request.User;
import com.trunip.friendSuggester.response.CreateUserResponse;

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

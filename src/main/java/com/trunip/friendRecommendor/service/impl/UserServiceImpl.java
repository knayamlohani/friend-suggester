package com.trunip.friendRecommendor.service.impl;

import com.trunip.friendRecommendor.constant.Status;
import com.trunip.friendRecommendor.exception.BadRequestException;
import com.trunip.friendRecommendor.exception.BaseException;
import com.trunip.friendRecommendor.exception.NotFoundException;
import com.trunip.friendRecommendor.request.CreateUserRequest;
import com.trunip.friendRecommendor.request.User;
import com.trunip.friendRecommendor.response.*;
import com.trunip.friendRecommendor.service.InMemoryDataService;
import com.trunip.friendRecommendor.service.UserHelperService;
import com.trunip.friendRecommendor.service.UserService;
import com.trunip.friendRecommendor.service.UserValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private InMemoryDataService inMemoryDataService;

    @Autowired
    private UserHelperService userHelperService;

    @Autowired
    private UserValidatorService userValidatorService;

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) throws BaseException {

        log.info("received request to create user: {}", createUserRequest);

        userValidatorService.validateCreateUserRequest(createUserRequest);

        if (userHelperService.checkIfUserExists(createUserRequest.getUsername())) {
            throw new BadRequestException("user with username: " + createUserRequest.getUsername() + " already exists");
        }


        return userHelperService.getCreateUserResponseFromUser(
                inMemoryDataService.addUser(
                        userHelperService.getUserFromCreateUserRequest(createUserRequest)
                )
        );
    }

    @Override
    public BaseResponse processFriendRequest(String from, String to) throws BaseException {

        log.info("received friend request from: {} to : {} ", from, to);

        User fromUser = inMemoryDataService.getUserByUserName(from);
        User toUser = inMemoryDataService.getUserByUserName(to);


        if (fromUser == null) {
            throw new BadRequestException("user with username: " + from + " not found");
        }

        if (toUser == null) {
            throw new BadRequestException("user with username: " + to + " not found");
        }

        inMemoryDataService.addFriendRequest(from, to);

        // bi-directional pending request -> they both are friends
        if (userHelperService.checkIfUserInPendingRequest(from, to)) {
            log.info(" {} has friend request from {}", to, from);
            userHelperService.makeFriends(to, from);
        }


        return new BaseResponse(Status.SUCCESS);
    }


    @Override
    public GetPendingFriendRequestsResponse getPendingFriendRequestsOfUser(String username) throws BaseException {
        log.info("received request to get pending friend request for user with username: {}", username);

        if (inMemoryDataService.getUserByUserName(username) == null) {
            throw new BadRequestException("user with username: " + username + " not found");
        }

        List<String> pendingFriends = inMemoryDataService.getPendingFriendRequestsOfUser(username);

        if (pendingFriends.isEmpty()) {
            throw new NotFoundException("No Pending Friend Requests for user with username: " + username);
        }
        GetPendingFriendRequestsResponse response = new GetPendingFriendRequestsResponse();
        response.setFriendRequests(pendingFriends);
        return response;
    }


    @Override
    public GetAllFriendsOfUserResponse getAllFriendsOfUser(String username) throws BaseException {

        log.info("received request to get all friends for user with username: {}", username);

        if (inMemoryDataService.getUserByUserName(username) == null) {
            throw new BadRequestException("user with username: " + username + " not found");
        }

        List<String> friends = inMemoryDataService.getAllFriendsOfUser(username);

        if (friends.isEmpty()) {
            throw new NotFoundException("No Friends found for user with username: " + username);
        }

        GetAllFriendsOfUserResponse response = new GetAllFriendsOfUserResponse();
        response.setFriends(friends);
        return response;
    }

    @Override
    public GetSuggestionForUserResponse getSuggestionsForUser(String username) throws BaseException {

        log.info("received request to get friend suggestion for user with username: {}", username);

        if (inMemoryDataService.getUserByUserName(username) == null) {
            throw new BadRequestException("user with username: " + username + " not found");
        }

        GetSuggestionForUserResponse getSuggestionForUserResponse = new GetSuggestionForUserResponse();
        getSuggestionForUserResponse.setSuggestions(userHelperService.getSuggestionsForUser(username));

        if (getSuggestionForUserResponse.getSuggestions() == null || getSuggestionForUserResponse.getSuggestions().size() == 0) {
            throw new NotFoundException("no friends suggestions available for user with username: " + username);
        }

        return getSuggestionForUserResponse;
    }
}

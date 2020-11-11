package com.trunip.friendSuggester.controller;

import com.trunip.friendSuggester.request.CreateUserRequest;
import com.trunip.friendSuggester.response.BaseResponse;
import com.trunip.friendSuggester.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    ResponseEntity<BaseResponse> createUser(@RequestBody CreateUserRequest createUserRequest) throws Exception {
        return new ResponseEntity<>(
                userService.createUser(createUserRequest),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/user/add/{from}/{to}")
    ResponseEntity<BaseResponse> processFriendRequest(@PathVariable(name = "from") String from,
                                                      @PathVariable(name = "to") String to) throws Exception {
        return new ResponseEntity<>(
                userService.processFriendRequest(from, to),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/user/friendRequests/{username}")
    ResponseEntity<BaseResponse> getPendingFriendRequestsOfUser(@PathVariable(name = "username") String username) throws Exception {
        return new ResponseEntity<>(
                userService.getPendingFriendRequestsOfUser(username),
                HttpStatus.OK
        );
    }

    @GetMapping("/user/friends/{username}")
    ResponseEntity<BaseResponse> getAllFriendsOfUser(@PathVariable(name = "username") String username) throws Exception {

        return new ResponseEntity<>(
                userService.getAllFriendsOfUser(username),
                HttpStatus.OK
        );
    }

    @GetMapping("/user/suggestions/{username}")
    ResponseEntity<BaseResponse> getSuggestionsForUser(@PathVariable(name = "username") String username) throws Exception {

        return new ResponseEntity<>(
                userService.getSuggestionsForUser(username),
                HttpStatus.OK
        );
    }


}

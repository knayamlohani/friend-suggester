package com.trunip.friendRecommendor.service;

import com.trunip.friendRecommendor.exception.BaseException;
import com.trunip.friendRecommendor.request.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface InMemoryDataService {
    User getUserByUserName(String username);

    User addUser(User user);

    void addFriendRequest(String from, String to) throws BaseException;

    List<String> getPendingFriendRequestsOfUser(String username);

    List<String> getAllFriendsOfUser(String username);

    void addFriendForUser(String user, String friend);

    void deletePendingFriendRequestBetweenUsers(String userA, String userB);

    Map<String, Set<String>> getFriends();
}

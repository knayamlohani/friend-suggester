package com.trunip.friendRecommendor.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trunip.friendRecommendor.exception.BaseException;
import com.trunip.friendRecommendor.request.User;
import com.trunip.friendRecommendor.service.InMemoryDataService;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Service("InMemoryDataServiceImpl")
public class InMemoryDataServiceImpl implements InMemoryDataService {

    private Map<String, User> users = new HashMap<>();
    private Map<String, Set<String>> userPendingFriendRequest = new HashMap<>();
    private Map<String, Set<String>> friends = new HashMap<>();


    @PostConstruct
    void init() {
        String friendsString = "{\"a\":[\"b\"],\"b\":[\"c\",\"d\",\"a\"],\"c\":[\"e\",\"f\",\"b\"],\"d\":[\"g\",\"h\",\"b\"],\"e\":[\"c\"],\"f\":[\"c\"],\"g\":[\"d\"],\"h\":[\"d\"]}";
        String usersString = "{\"a\":{},\"b\":{},\"c\":{},\"d\":{},\"e\":{},\"f\":{},\"g\":{},\"h\":{}}";
        try {
            users = new ObjectMapper().readValue(usersString, new TypeReference<Map<String, User>>() {});
            friends = new ObjectMapper().readValue(friendsString, new TypeReference<Map<String, Set<String>>>() {});
        } catch (Exception e) {
            log.error("error while mapping {}", e);
        }

    }

    @Override
    public User getUserByUserName(String username) {
        return users.get(username);
    }

    @Override
    public User addUser(User user) {
        users.put(user.getUsername(), user);
        return user;
    }


    @Override
    public void addFriendRequest(String from, String to) throws BaseException {
        userPendingFriendRequest.computeIfAbsent(to, (k) -> new HashSet<>());
        boolean added = userPendingFriendRequest.get(to).add(from);

        if (!added) {
            throw new BaseException("Friend Request already exists");
        }
    }

    @Override
    public List<String> getPendingFriendRequestsOfUser(String username) {
        return new ArrayList<>(Optional.ofNullable(userPendingFriendRequest.get(username)).orElse(new HashSet<>()));
    }

    @Override
    public List<String> getAllFriendsOfUser(String username) {
        return new ArrayList<>(
                Optional.ofNullable(friends.get(username)).orElse(new HashSet<>())
        );
    }


    @Override
    public void addFriendForUser(String user, String friend) {
        friends.computeIfAbsent(user, (k) -> new HashSet<>());
        friends.get(user).add(friend);
    }

    @Override
    public void deletePendingFriendRequestBetweenUsers(String userA, String userB) {
        userPendingFriendRequest.get(userA).remove(userB);
        userPendingFriendRequest.get(userB).remove(userA);
    }

    @Override
    public Map<String, Set<String>> getFriends() {
        return friends;
    }




}

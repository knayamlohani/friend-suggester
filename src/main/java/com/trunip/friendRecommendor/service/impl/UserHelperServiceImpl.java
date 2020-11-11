package com.trunip.friendRecommendor.service.impl;

import com.trunip.friendRecommendor.model.FriendsConfig;
import com.trunip.friendRecommendor.request.CreateUserRequest;
import com.trunip.friendRecommendor.request.User;
import com.trunip.friendRecommendor.response.CreateUserResponse;
import com.trunip.friendRecommendor.service.InMemoryDataService;
import com.trunip.friendRecommendor.service.UserHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service("UserHelperServiceImpl")
public class UserHelperServiceImpl implements UserHelperService {

    @Autowired
    private FriendsConfig friendsConfig;

    @Autowired
    private InMemoryDataService inMemoryDataService;

    @Override
    public User getUserFromCreateUserRequest(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setEmail(createUserRequest.getEmail());
        user.setCreated(new Date());

        return user;
    }


    @Override
    public CreateUserResponse getCreateUserResponseFromUser(User user) {
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setUsername(user.getUsername());
        createUserResponse.setFirstName(user.getFirstName());
        createUserResponse.setLastName(user.getLastName());
        createUserResponse.setEmail(user.getEmail());
        createUserResponse.setCreated(user.getCreated());

        return createUserResponse;
    }

    @Override
    public boolean checkIfUserInPendingRequest(String source, String toFind) {
        return inMemoryDataService.getPendingFriendRequestsOfUser(source).contains(toFind);
    }

    @Override
    public void makeFriends(String firstUser, String secondUser) {
        inMemoryDataService.addFriendForUser(firstUser, secondUser);
        inMemoryDataService.addFriendForUser(secondUser, firstUser);
        inMemoryDataService.deletePendingFriendRequestBetweenUsers(firstUser, secondUser);
    }

    @Override
    public boolean checkIfUserExists(String username) {
        return inMemoryDataService.getUserByUserName(username) != null;
    }

    // do a breadth first search traversal from source to find friend recommendation
    @Override
    public List<String> getSuggestionsForUser(String username) {
        List<String> suggestions = new ArrayList<>();
        int level = -1;
        Map<String, Boolean> visited = new HashMap<>();


        Map<String, Set<String>> friends = inMemoryDataService.getFriends();

        Queue<String> queue = new LinkedList<>();


        queue.add(username);

        while (!queue.isEmpty() || level < friendsConfig.getEnd() + 1) {

            int queueSize = queue.size();


            for (int i = 0; i < queueSize; i++) {

                String user = queue.remove();

                if (!StringUtils.isEmpty(user) && !checkIfUserAlreadyProcessed(visited, user)) {
                    if (level >= friendsConfig.getStart() && level <= friendsConfig.getEnd()) {
                        suggestions.add(user);
                    }
                    visited.put(user, true);
                    queue.addAll(Optional.ofNullable(friends.get(user)).orElse(new HashSet<>()));
                }
            }

            level++;


        }


        return suggestions;
    }


    private boolean checkIfUserAlreadyProcessed(Map<String, Boolean> visited, String username) {
        return visited.get(username) != null && visited.get(username);
    }
}

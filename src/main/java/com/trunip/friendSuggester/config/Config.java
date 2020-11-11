package com.trunip.friendSuggester.config;

import com.trunip.friendSuggester.model.FriendsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${friends.depth.start}")
    int start;

    @Value("${friends.depth.end}")
    int end;

    @Bean
    public FriendsConfig getFriendsConfig() {
        return new FriendsConfig(start, end);
    }
}

package com.google.oauth2.service;

import com.google.oauth2.entity.Users;
import com.google.oauth2.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UsersRepository usersRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Users users = usersRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (users == null) {
            users = new Users();
            users.setEmail(oAuth2User.getAttribute("email"));
        }
        users.setName(oAuth2User.getAttribute("name"));
        users.setPicture(oAuth2User.getAttribute("picture"));
        usersRepository.save(users);
        return users;
    }
}

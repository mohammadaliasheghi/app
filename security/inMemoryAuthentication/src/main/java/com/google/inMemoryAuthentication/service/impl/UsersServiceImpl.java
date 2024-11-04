package com.google.inMemoryAuthentication.service.impl;

import com.google.inMemoryAuthentication.entity.Users;
import com.google.inMemoryAuthentication.repository.UsersRepository;
import com.google.inMemoryAuthentication.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public List<Users> getList() {
        return usersRepository.findAll();
    }
}

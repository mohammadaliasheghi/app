package com.google.jdbcAuthentication.service;

import com.google.jdbcAuthentication.entity.Users;
import com.google.jdbcAuthentication.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usersRepository.findByEmail(email);
    }

    @PreAuthorize("#users.email != authentication.name")
    public void deleteUser(Users users) {
        usersRepository.deleteById(users.getId());
    }

    @PostAuthorize("returnObject.email == authentication.name")
    public Users getUsers(Long id) {
        return usersRepository.get(id);
    }

    public Optional<Users> get(Long id) {
        return usersRepository.findById(id);
    }
}

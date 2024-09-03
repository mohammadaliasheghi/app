package com.mmad.oauth.repository;

import com.mmad.oauth.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersByUsername(String userName);

    Optional<Users> findByUsernameAndIdNot(String username, Long id);
}

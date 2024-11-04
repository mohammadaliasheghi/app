package com.google.oauth2.repository;

import com.google.oauth2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    @Query("select u from Users u where u.id = :id ")
    Users get(Long id);
}

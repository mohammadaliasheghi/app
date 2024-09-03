package com.mmad.oauth.repository;

import com.mmad.oauth.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Boolean existsByUsersIdAndRolesIdAndIdNot(Long userId, Long roleId, Long id);

    Boolean existsByUsersId(Long userId);

    List<UserRoleEntity> getByUsersId(Long id);
}

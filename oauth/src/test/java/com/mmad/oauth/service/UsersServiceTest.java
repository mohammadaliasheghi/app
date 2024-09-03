package com.mmad.oauth.service;

import com.mmad.oauth.config.Constant;
import com.mmad.oauth.config.ResourceBundle;
import com.mmad.oauth.entity.Users;
import com.mmad.oauth.exception.ResourceNotFoundException;
import com.mmad.oauth.mapper.UsersMapper;
import com.mmad.oauth.model.UsersModel;
import com.mmad.oauth.repository.UsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@SpringBootTest
public class UsersServiceTest {

    @Autowired
    protected UsersService usersService;
    @MockBean
    protected UsersRepository usersRepository;

    protected UsersModel usersModel;

    @BeforeEach
    protected void setUp() {
        usersModel = UsersModel.builder()
                .id(Constant.ID_TEST)
                .username(Constant.USERNAME_TEST)
                .password(Constant.PASS_TEST)
                .build();
    }

    @Test
    protected void itShouldSaveNewUser() {
        //Given
        Users entity = UsersMapper.get().modelToEntity(usersModel);
        Mockito.when(usersRepository.save(entity)).thenReturn(entity);
        //When
        UsersModel createdModel = usersService.create(usersModel);
        //Then
        Assertions.assertThat(usersModel).isEqualTo(createdModel);
    }

    @Test
    protected void itShouldUpdateUsernameExistUser() {
        //Given
        Users entity = UsersMapper.get().modelToEntity(usersModel);
        entity.setUsername("newUsername");
        Mockito.when(usersRepository.save(entity)).thenReturn(entity);
        //When
        usersModel.setUsername("newUsername");
        UsersModel updatedUsers = usersService.updateUsername(usersModel);
        //Then
        Assertions.assertThat(usersModel).isEqualTo(updatedUsers);
    }

    @Test
    protected void itShouldThrowNotFoundExceptionWhenUpdateUsername() {
        //Given
        Users entity = UsersMapper.get().modelToEntity(usersModel);
        entity.setUsername("newUsername");
        Mockito.when(usersRepository.save(entity)).thenReturn(entity);
        //When
        //Then
        usersModel.setId(null);
        usersModel.setUsername("newUsername");
        Assertions
                .assertThatThrownBy(() -> usersService.updateUsername(usersModel))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(ResourceBundle.getMessageByKey("RecordNotFound"));
    }

    @Test
    protected void itShouldLoadUserByUserName() {
        //Given
        Users findUser = UsersMapper.get().modelToEntity(usersModel);
        BDDMockito.given(usersRepository.findUsersByUsername(usersModel.getUsername())).willReturn(Optional.of(findUser));
        //When
        UserDetails details = usersService.loadUserByUsername(usersModel.getUsername());
        //Then
        Assertions.assertThat(details).hasNoNullFieldsOrProperties();
    }

    @Test
    protected void itShouldEmptyWhenUserNotFoundByUsername() {
        //Given
        BDDMockito.given(usersRepository.findUsersByUsername(usersModel.getUsername())).willReturn(Optional.empty());
        //When
        Optional<UsersModel> returnUser = usersService.findUsersByUsername(usersModel.getUsername());
        //Then
        Assertions.assertThat(returnUser).isEqualTo(Optional.empty());
    }

    @Test
    protected void itShouldFindUserById() {
        //Given
        Users entity = UsersMapper.get().modelToEntity(usersModel);
        BDDMockito.given(usersRepository.findById(usersModel.getId())).willReturn(Optional.of(entity));
        //When
        Optional<Users> findUser = usersRepository.findById(usersModel.getId());
        //Then
        Assertions.assertThat(findUser).isNotEqualTo(Optional.empty());
    }

    @Test
    protected void itShouldThrowExceptionWhenDeleteAdminById() {
        //Given ,When ,Then
        usersModel.setId(1L);
        Assertions
                .assertThatThrownBy(() -> usersService.delete(usersModel.getId()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(ResourceBundle.getMessageByKey("CannotBeDeletedAdminUsers"));
    }

    @Test
    void itShouldThrowExceptionWhenLoadUserByUserName() {
        //Given
        BDDMockito.given(usersRepository
                        .findUsersByUsername(usersModel.getUsername()))
                .willReturn(Optional.empty());
        //When
        //Then
        Assertions
                .assertThatThrownBy(() -> usersService.loadUserByUsername(usersModel.getUsername()))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining(Constant.USER_INVALID);
    }

    @Test
    void itShouldNotFoundUserById() {
        //Given
        BDDMockito.given(usersRepository
                        .getById(usersModel.getId()))
                .willReturn(null);
        //When
        //Then
        Assertions
                .assertThatThrownBy(() -> usersService.get(usersModel.getId()))
                .isInstanceOf(ResourceNotFoundException.class);
    }

}

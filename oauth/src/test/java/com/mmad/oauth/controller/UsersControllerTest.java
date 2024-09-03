package com.mmad.oauth.controller;

import com.mmad.oauth.config.Constant;
import com.mmad.oauth.model.UsersModel;
import com.mmad.oauth.service.UsersService;
import com.mmad.oauth.util.JSON;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class UsersControllerTest {

    @MockBean
    UsersService usersService;
    @Autowired
    MockMvc mockMvc;

    protected UsersModel usersModel;

    @BeforeEach
    void setUp() {
        usersModel = UsersModel.builder()
                .id(Constant.ID_TEST)
                .username(Constant.USERNAME_TEST)
                .password(Constant.PASS_TEST)
                .build();
    }

    @Test
    void itShouldSaveNewUser() throws Exception {
        //Given
        String input = JSON.convertToJSON(usersModel);
        BDDMockito.given(usersService.findUsersByUsername(usersModel.getUsername())).willReturn(Optional.empty());
        //When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(Constant.USER_CONTEXT)
                        .contentType(MediaType.APPLICATION_JSON).content(input))
                .andDo(MockMvcResultHandlers.print());
        //Then
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(Constant.USER_CREATED)));
    }

    @Test
    void itShouldNotSaveNewUser() throws Exception {
        //Given
        String input = JSON.convertToJSON(usersModel);
        BDDMockito.given(usersService.findUsersByUsername(ArgumentMatchers.any())).willReturn(Optional.of(usersModel));
        //When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(Constant.USER_CONTEXT)
                        .contentType(MediaType.APPLICATION_JSON).content(input))
                .andDo(MockMvcResultHandlers.print());
        //Then
        resultActions.andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(Constant.USERNAME_CONFLICT)));
    }

    @Test
    void itShouldUpdateExistUser() throws Exception {
        //Given
        String input = JSON.convertToJSON(usersModel);
        BDDMockito.given(usersService.findUsersByUsername(usersModel.getUsername())).willReturn(Optional.empty());
        //When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put(Constant.USER_CONTEXT)
                        .contentType(MediaType.APPLICATION_JSON).content(input))
                .andDo(MockMvcResultHandlers.print());
        //Then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(Constant.USER_UPDATED)));
    }

    @Test
    void itShouldNotUpdateExistUser() throws Exception {
        //Given
        String input = JSON.convertToJSON(usersModel);
        BDDMockito.given(usersService.findUsersByUsername(ArgumentMatchers.any())).willReturn(Optional.of(usersModel));
        //When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put(Constant.USER_CONTEXT)
                        .contentType(MediaType.APPLICATION_JSON).content(input))
                .andDo(MockMvcResultHandlers.print());
        //Then
        resultActions.andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(Constant.USERNAME_CONFLICT)));
    }

    @Test
    void itShouldFindExistUser() throws Exception {
        //Given
        BDDMockito.given(usersService.get(usersModel.getId())).willReturn(usersModel);
        //When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.
                        get(Constant.USER_CONTEXT + "/" + usersModel.getId()))
                .andDo(MockMvcResultHandlers.print());
        //Then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(Constant.SUCCESS)));
    }

    @Test
    void itShouldDeleteExistUser() throws Exception {
        //Given
        BDDMockito.given(usersService.delete(usersModel.getId())).willReturn(Constant.SUCCESS);
        //When
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.
                        delete(Constant.USER_CONTEXT + "/" + usersModel.getId()))
                .andDo(MockMvcResultHandlers.print());
        //Then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}

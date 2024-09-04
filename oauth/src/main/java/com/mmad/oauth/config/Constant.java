package com.mmad.oauth.config;

public class Constant {

    //TEST
    public static final String USERNAME_TEST = "user";
    public static final String PASS_TEST = "user";
    public static final Long ID_TEST = 2L;
    //END_TEST

    //API_ADDRESS
    public static final String USER_CONTEXT = "/api/users";
    public static final String ROLES_CONTEXT = "/api/roles";
    public static final String JWT_CONTEXT = "/jwt";
    public static final String MESSAGE_CONTEXT = "/message";
    public static final String SEND_CONTEXT = "/send";
    //END_API_ADDRESS

    //MESSAGE
    public static final String SUCCESS = "SUCCESS";
    public static final String USER_CREATED = "New User successfully created";
    public static final String USER_UPDATED = "Exist User successfully updated";
    public static final String USERNAME_CONFLICT = "The username has already been registered ";
    public static final String USER_INVALID = "Incorrect username/password supplied";
    public static final String ROLE_CREATED = "New Role successfully created";
    public static final String ROLE_UPDATED = "Exist Role successfully updated";
    public static final String INVALID_TOKEN = "Invalid Token";
    //END_MESSAGE

    //VALUE
    public static final Long ONE_LONG = 1L;
    public static final Long TWO_LONG = 2L;
    //END_VALUE

    //METHOD_TYPE
    public static final String CREATE = "CREATE";
    public static final String UPDATE = "UPDATE";
    //END_METHOD_TYPE

    //SECURITY
    public static final String DEFAULT_SECURITY_ROLE_CONSTANT = "ROLE_";
    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN = "/token";
    //END_SECURITY
}

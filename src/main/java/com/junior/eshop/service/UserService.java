package com.junior.eshop.service;

import com.junior.eshop.dto.request.UserRequest;
import com.junior.eshop.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAll();

    Long add(UserRequest userRequest);

    UserResponse update(Long id, UserRequest request);

    Long delete(Long id);

    UserResponse findByUsername(String username);

    UserResponse findByUserId(Long id);
}

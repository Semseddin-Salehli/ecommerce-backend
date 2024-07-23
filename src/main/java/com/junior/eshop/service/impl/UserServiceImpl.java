package com.junior.eshop.service.impl;

import com.junior.eshop.dto.request.UserRequest;
import com.junior.eshop.dto.response.UserResponse;
import com.junior.eshop.enums.ExceptionCode;
import com.junior.eshop.exception.NotFoundException;
import com.junior.eshop.model.User;
import com.junior.eshop.repository.UserRepository;
import com.junior.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getAll() {
        List<UserResponse> allUsers = userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponse.class)).collect(Collectors.toList());
        return allUsers;
    }

    @Override
    public Long add(UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User savedUser = userRepository.save(modelMapper.map(userRequest, User.class));
        return savedUser.getId();
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id,
                ExceptionCode.USER_NOT_FOUND.getCode()));

        User newUser = User.builder()
                .id(id)
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .name(request.getName())
                .surname(request.getSurname()).build();

        userRepository.save(newUser);

        return modelMapper.map(newUser, UserResponse.class);
    }

    @Override
    public Long delete(Long id) {
        User deletedUser = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(User.class, id, ExceptionCode.USER_NOT_FOUND.getCode()));
        userRepository.deleteById(deletedUser.getId());

        return deletedUser.getId();
    }

    @Override
    public UserResponse findByUsername(String username) {
        User findUser = userRepository.findByUsername(username).orElseThrow(() ->
                new NotFoundException(User.class, username, ExceptionCode.USER_NOT_FOUND.getCode()));
        return modelMapper.map(findUser, UserResponse.class);
    }

    @Override
    public UserResponse findByUserId(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(User.class, id, ExceptionCode.USER_NOT_FOUND.getCode()));
        return modelMapper.map(findUser, UserResponse.class);
    }
}

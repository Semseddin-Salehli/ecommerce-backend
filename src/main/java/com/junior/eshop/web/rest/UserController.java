package com.junior.eshop.web.rest;

import com.junior.eshop.dto.request.UserRequest;
import com.junior.eshop.dto.response.UserResponse;
import com.junior.eshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody UserRequest userRequest) {
        return userService.add(userRequest);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }

    @GetMapping("/all")
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{username}")
    public UserResponse getByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping
    public UserResponse getByUserId(@RequestParam(name = "id") Long id) { return userService.findByUserId(id); }
}
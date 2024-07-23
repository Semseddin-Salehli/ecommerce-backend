package com.junior.eshop.configuration.security;


import com.junior.eshop.enums.ExceptionCode;
import com.junior.eshop.exception.NotFoundException;
import com.junior.eshop.model.User;
import com.junior.eshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(User.class, username,
                        ExceptionCode.USER_NOT_FOUND.getCode()));
        return new MyUserDetail(user);
    }

}

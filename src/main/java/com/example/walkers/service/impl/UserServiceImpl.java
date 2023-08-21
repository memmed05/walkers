package com.example.walkers.service.impl;


import com.example.walkers.dto.user.UserRegisterRequest;
import com.example.walkers.dto.user.UserRegisterResponse;
import com.example.walkers.exception.EmailAlreadyTakenException;
import com.example.walkers.exception.PasswordDoesntMatchException;
import com.example.walkers.exception.UserNotFoundException;
import com.example.walkers.model.User;
import com.example.walkers.repository.UserRepository;
import com.example.walkers.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        if (userRepository.findUserByEmail(request.email()).isPresent()) {
            throw new EmailAlreadyTakenException("Email already taken: " + request.email());
        }
        if (userRepository.findUserByUsername(request.email()).isPresent()) {
            throw new EmailAlreadyTakenException("Username already taken: " + request.username());
        }
        if (!request.password().equals(request.rePassword())) {
            throw new PasswordDoesntMatchException("Password doesn't matches");
        }
        User user = userRepository.save(UserRegisterRequest.toEntity(request, passwordEncoder.encode(request.password())));
        return new UserRegisterResponse(
                user.getEmail(),
                user.getUsername(),
                user.getConfirmed());
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }
}

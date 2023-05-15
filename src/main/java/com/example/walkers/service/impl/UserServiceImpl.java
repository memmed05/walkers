package com.example.walkers.service.impl;


import com.example.walkers.dto.user.UserRegisterRequest;
import com.example.walkers.dto.user.UserRegisterResponse;
import com.example.walkers.exception.EmailAlreadyTakenException;
import com.example.walkers.exception.PasswordDoesntMatchException;
import com.example.walkers.exception.UserNotFoundException;
import com.example.walkers.mapsturct.UserMapstruct;
import com.example.walkers.model.User;
import com.example.walkers.repository.UserRepository;
import com.example.walkers.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapstruct userMapstruct;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        if (userRepository.findUserByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyTakenException("Email already taken: "+request.getEmail());
        }
        if (userRepository.findUserByUsername(request.getEmail()).isPresent()){
            throw new EmailAlreadyTakenException("Username already taken: "+request.getUsername());
        }
        if (!request.getPassword().equals(request.getRePassword())){
            throw new PasswordDoesntMatchException("Password doesn't matches");
        }
        User user=userRepository.save(userMapstruct.toEntity(request));
        return UserRegisterResponse.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .confirmed(user.getConfirmed())
                .build();
    }

    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username)
                .orElseThrow(()-> new UserNotFoundException("user not found"));
    }
}

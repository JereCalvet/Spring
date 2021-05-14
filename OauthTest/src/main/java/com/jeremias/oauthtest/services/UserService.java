package com.jeremias.oauthtest.services;

import com.jeremias.oauthtest.exceptions.EmailAlreadyTakenException;
import com.jeremias.oauthtest.exceptions.UserNotFoundException;
import com.jeremias.oauthtest.models.User;
import com.jeremias.oauthtest.models.UserDto;
import com.jeremias.oauthtest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserService {

    private static final String USER_ID_NOT_FOUND_ERROR_MSG = "User id %d not found.";
    private static final String USER_EMAIL_NOT_FOUND_ERROR_MSG = "User with email %s not found.";
    private static final String EMAIL_ALREADY_TAKEN_ERROR_MSG = "Email %s already taken.";

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllStudents() {
        return userRepository.findAll();
    }

    public void registerUser(UserDto userRequest) {
        userRepository
                .findByEmail(userRequest.getEmail())
                .ifPresent(s -> {
                    throw new EmailAlreadyTakenException(String.format(EMAIL_ALREADY_TAKEN_ERROR_MSG, userRequest.getEmail()));
                });

        var userToSave = new User(
                userRequest.getEmail(),
                passwordEncoder.encode(userRequest.getPassword()),
                userRequest.getRole());
        userRepository.save(userToSave);
    }

    public void deleteUser(Long id) {
        userRepository
                .findById(id)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new UserNotFoundException(String.format(USER_ID_NOT_FOUND_ERROR_MSG, id));
                });
    }

    public User findStudentByEmail(String email) {
        final Optional<User> userFound = userRepository.findByEmail(email);
        return userFound.orElseThrow(() ->
                new UserNotFoundException(String.format(USER_EMAIL_NOT_FOUND_ERROR_MSG, email)));
    }

    public User findStudentById(Long id) {
        final Optional<User> userFound = userRepository.findById(id);
        return userFound.orElseThrow(() ->
                new UserNotFoundException(String.format(USER_ID_NOT_FOUND_ERROR_MSG, id)));
    }

    @Transactional
    public User updateUser(Long id, UserDto userRequest) {
        final Optional<User> userToUpdate = userRepository.findById(id);
        Consumer<User> updateUser = user -> {
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user.setRole(userRequest.getRole());
        };
        userToUpdate
                .ifPresentOrElse(updateUser, () -> {
                    throw new UserNotFoundException(String.format(USER_ID_NOT_FOUND_ERROR_MSG, id));
                });

        //noinspection OptionalGetWithoutIsPresent
        return userToUpdate.get();
    }
}

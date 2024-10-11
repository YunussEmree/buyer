package com.yunussemree.buyer.user;

import com.yunussemree.buyer.core.utilities.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User saveUser(CreateUserRequest request) {
        return Optional.of(request).filter(user -> !userRepository.existsByEmail(user.getEmail()))
                .map(user -> userRepository.save(User.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .build()))
                .orElseThrow(() -> new ResourceNotFoundException(request.getEmail() + " already exists in the system!"));
    }

    @Override
    public User updateUser(UpdateUserRequest request, Long userId) {
        return userRepository.findById(userId).map(user -> {
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found when update user service!"));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found when get user by id service!"));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresentOrElse(userRepository::delete, () -> {
            throw new ResourceNotFoundException("User not found when delete user by id service!");
        });
    }

    @Override
    public UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}

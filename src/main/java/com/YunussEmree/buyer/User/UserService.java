package com.yunussemree.buyer.user;

import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

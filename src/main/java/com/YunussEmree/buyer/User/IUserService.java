package com.yunussemree.buyer.user;

import java.util.List;

public interface IUserService {
    User getUserById(Long id);
    User saveUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long userId);

    List<User> getUsers();

    void deleteUser(Long id);

    UserDto convertToDto(User user);
}

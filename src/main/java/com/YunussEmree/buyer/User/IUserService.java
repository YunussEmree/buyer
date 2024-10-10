package com.yunussemree.buyer.user;

public interface IUserService {
    User getUserById(Long id);
    User saveUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long userId);
    void deleteUser(Long id);
}

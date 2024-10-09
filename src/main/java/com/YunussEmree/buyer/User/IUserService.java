package com.yunussemree.buyer.user;

public interface IUserService {
    public User saveUser(User user);
    public User getUserById(Long id);
    public User getUserByName(String username);
    public void deleteUser(Long id);
}

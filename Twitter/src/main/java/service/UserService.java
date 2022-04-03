package service;

import entity.User;
import service.base.BaseService;

public interface UserService extends BaseService<User,Integer> {
    User findByUserName(String username);
    void addTwit(User user);
    void deleteTwit(User user);
    void updateTwit(User user);
}

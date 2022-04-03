package service;

import entity.User;
import service.base.BaseService;

public interface UserService extends BaseService<User,Integer> {
    User findByUserName(String username);
    void addTwit(User user);
    void deleteTwit(User user);
    void updateTwit(User user);
    void showMyTwit(User user);
    void showAllTwit(User user,Integer number);
    void updateComment(User user);
    void deleteComment(User user);
    void showComment(User user);
    User checkUsername();
    void setFollowers(User user);
    void unSetFollowers(User user);
}

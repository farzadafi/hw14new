package service;

import entity.User;

public interface FollowerService {
    Integer unfollow(User user1,User user2);
    public void showFollowers(User user);
}

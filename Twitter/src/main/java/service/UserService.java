package service;

import entity.User;
import service.baseService.BaseService;

public interface UserService extends BaseService<User,Integer> {
    User findByUserName(String username);
}

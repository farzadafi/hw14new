package repository;

import entity.User;
import repository.base.GenericRepository;

public interface UserRepository extends GenericRepository<User,Integer> {
    User findByUserName(String username);
}

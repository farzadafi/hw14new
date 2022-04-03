package service;


import entity.Twit;
import entity.User;
import service.base.BaseService;

import java.util.List;

public interface TwitService extends BaseService<Twit,Integer> {
    Integer showMyTwit(User user);
    List<Twit> findTwitById(User user);
    Twit findTwitByTwoId(User user,Integer TwitId);
}

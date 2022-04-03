package repository;

import entity.Twit;
import repository.base.GenericRepository;

import java.util.List;

public interface TwitRepository extends GenericRepository<Twit,Integer> {
    List<Twit> showTwitById(Integer userId);
    Twit findTwitByTwoId(Integer userId,Integer twitId);
    List<Twit> findAllTwit();
}

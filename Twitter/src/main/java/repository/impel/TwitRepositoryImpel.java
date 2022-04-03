package repository.impel;

import connection.SessionFactorySingleton;
import entity.Twit;
import org.hibernate.SessionFactory;
import repository.TwitRepository;

import java.util.List;

public class TwitRepositoryImpel extends GenericRepositoryImpel<Twit,Integer> implements TwitRepository {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public List<Twit> showTwitById(Integer userId) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("FROM Twit as t WHERE t.user.id = :user_id",Twit.class);
        query.setParameter("user_id",userId);
        return query.list();
    }

    @Override
    public Twit findTwitByTwoId(Integer userId, Integer twitId) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("FROM Twit as t WHERE t.id = :id AND" +
                " t.user.id = :user_id",Twit.class);
        query.setParameter("user_id",userId);
        query.setParameter("id",twitId);
        return query.getSingleResult();
    }

}

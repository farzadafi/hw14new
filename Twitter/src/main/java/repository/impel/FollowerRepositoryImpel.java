package repository.impel;

import connection.SessionFactorySingleton;
import org.hibernate.SessionFactory;
import repository.FollowerRepository;

public class FollowerRepositoryImpel implements FollowerRepository {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    @Override
    public Integer unFollow(Integer id, Integer idUnfollow) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createSQLQuery("DELETE FROM followers as a WHERE a.user_id = ? AND a.follower_id = ?");
        query.setParameter(1,id);
        query.setParameter(2,idUnfollow);
        return query.executeUpdate();
    }
}

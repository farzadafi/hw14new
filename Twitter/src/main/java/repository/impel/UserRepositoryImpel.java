package repository.impel;

import connection.SessionFactorySingleton;
import entity.User;
import org.hibernate.SessionFactory;

public class UserRepositoryImpel extends GenericRepositoryImpel<User,Integer> implements repository.UserRepository {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public User findByUserName(String username) {
            var session = sessionFactory.getCurrentSession();
            var query = session.createQuery("FROM User as a WHERE a.userName = :userName",User.class);
            query.setParameter("userName",username);
            return query.getSingleResult();
        }
}

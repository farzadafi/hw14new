package service.impel;

import connection.SessionFactorySingleton;
import entity.User;
import org.hibernate.SessionFactory;
import repository.impel.UserRepositoryImpel;
import service.UserService;

public class UserServiceImpel extends GenericServiceImpel<User,Integer> implements UserService {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final UserRepositoryImpel userRepositoryImpel = new UserRepositoryImpel();

    @Override
    public User findByUserName(String username) {
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return userRepositoryImpel.findByUserName(username);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}

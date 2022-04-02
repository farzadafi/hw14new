package service.impel;

import connection.SessionFactorySingleton;
import entity.User;
import org.hibernate.SessionFactory;
import repository.impel.UserRepositoryImpel;
import service.UserService;
import utility.Utility;

public class UserServiceImpel extends GenericServiceImpel<User,Integer> implements UserService {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final UserRepositoryImpel userRepositoryImpel = new UserRepositoryImpel();
    private static final Utility utility = new Utility();

    @Override
    public User add(User userNull) {
        String fullName = utility.setFullName();
        String userName = utility.setUserName();
        String password = utility.setPassword();
        User user = new User(fullName,userName,password,null,null);
        User userResult = super.add(user);
        if(userResult != null)
            System.out.println(fullName + " successful added!");
        else
            System.out.println("Something is wrong!");
        return userResult;
    }

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

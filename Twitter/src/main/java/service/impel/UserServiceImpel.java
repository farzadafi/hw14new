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
        User newUser = new User(fullName,userName,password,null,null);
        User user1 = super.add(newUser);
        if(user1 == null )
            System.out.println("SomeThing is wrong!");
        else
            System.out.println(newUser.getFullName() + " successful added!");
        return newUser;
    }

    @Override
    public void update(User user) {
        String fullName = utility.setFullName();
        String password = utility.setPassword();
        user.setFullName(fullName);
        user.setPassword(password);
        super.update(user);
        System.out.println(fullName + " successful updated!");
    }

    @Override
    public void delete(User user) {
        super.delete(user);
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

package service.impel;

import connection.SessionFactorySingleton;
import entity.Comment;
import entity.Twit;
import entity.User;
import org.hibernate.SessionFactory;
import repository.impel.UserRepositoryImpel;
import service.UserService;
import utility.Utility;

import java.util.Scanner;

public class UserServiceImpel extends GenericServiceImpel<User,Integer> implements UserService {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final UserRepositoryImpel userRepositoryImpel = new UserRepositoryImpel();
    private static final Utility utility = new Utility();
    private static final TwitServiceImpel twitServiceImpel = new TwitServiceImpel();
    private final CommentServiceImpel commentServiceImpel = new CommentServiceImpel();
    private final Scanner input = new Scanner(System.in);

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

    @Override
    public void addTwit(User user){
        Twit twit = Twit.builder().user(user).build();
        twitServiceImpel.add(twit);
    }

    @Override
    public void deleteTwit(User user){
        Twit twit = Twit.builder().user(user).build();
        twitServiceImpel.delete(twit);
    }

    @Override
    public void updateTwit(User user){
        Twit twit = Twit.builder().user(user).build();
        twitServiceImpel.update(twit);
    }

    @Override
    public void showMyTwit(User user) {
        twitServiceImpel.showMyTwit(user);
    }

    @Override
    public void showAllTwit(User user,Integer number){
        twitServiceImpel.showAllTwit(user,number);
    }

    @Override
    public void updateComment(User user){
        Comment comment = Comment.builder().user(user).build();
        commentServiceImpel.update(comment);
    }

    @Override
    public void deleteComment(User user){
        Comment comment = Comment.builder().user(user).build();
        commentServiceImpel.delete(comment);
    }

    @Override
    public void showComment(User user){
        commentServiceImpel.showComment(user.getId());
    }

    @Override
    public User checkUsername(){
            System.out.print("Enter User Name:");
            String userName = input.nextLine();
            User user = findByUserName(userName);
            if(user == null ) {
                System.out.println("We dont have this user!");
                return null;
            }
            else
                System.out.println(userName + " is found with name : " + user.getFullName());
            return user;
    }

    @Override
    public void setFollowers(User user){
        User findUser = checkUsername();
        if(findUser == null )
            return;
        user.addFollower(findUser);
        super.update(user);
    }
}

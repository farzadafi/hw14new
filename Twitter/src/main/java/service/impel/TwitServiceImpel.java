package service.impel;

import connection.SessionFactorySingleton;
import entity.Comment;
import entity.Twit;
import entity.User;
import org.hibernate.SessionFactory;
import repository.impel.TwitRepositoryImpel;
import service.TwitService;
import utility.Utility;

import java.util.List;
import java.util.Scanner;

public class TwitServiceImpel extends GenericServiceImpel<Twit,Integer> implements TwitService {

    private final Scanner input = new Scanner(System.in);
    private final TwitRepositoryImpel twitRepositoryImpel = new TwitRepositoryImpel();
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final Utility utility = new Utility();
    private final UserServiceImpel userServiceImpel = new UserServiceImpel();
    private final CommentServiceImpel commentServiceImpel = new CommentServiceImpel();

    @Override
    public Twit add(Twit twit) {
        System.out.print("Enter Twit:");
        String twitText = input.nextLine();
        if(twitText.length() > 280 ){
            System.out.println("Twit must be less than 280 character!");
            return null;
        }
        twit.setTwit(twitText);
        Twit twit1 = super.add(twit);
        if(twit1 != null)
            System.out.println("This twit successful added!");
        else
            System.out.println("Something is wrong!");
        return twit;
    }

    @Override
    public void delete(Twit twit) {
        if(showMyTwit(twit.getUser()) == 0)
            return;
        System.out.print("Enter Id for delete twit:");
        Integer id = utility.giveIntegerInput();
        Twit twit1 = findTwitByTwoId(twit.getUser(),id);
        if(twit1 == null )
            System.out.println("You enter a wrong id");
        else{
            super.delete(twit1);
            System.out.println("This twit successful deleted!");
        }
    }

    @Override
    public void update(Twit twit) {
        if(showMyTwit(twit.getUser()) == 0)
            return;
        System.out.print("Enter Id for edit twit:");
        Integer id = utility.giveIntegerInput();
        Twit twit1 = findTwitByTwoId(twit.getUser(),id);
        if(twit1 == null)
            System.out.println("You enter a wrong id!");
        else{
            System.out.print("Enter your new twit:");
            String newTwit = input.nextLine();
            twit1.setTwit(newTwit);
            super.update(twit1);
            System.out.println("This twit successful updated!");
        }
    }

    @Override
    public Integer showMyTwit(User user){
        System.out.println("You have This Twit:");
        List<Twit> twitList = findTwitById(user);
        if(twitList.size() == 0 ){
            System.out.println("You dont have any Twit until now!");
            return 0;
        }
        else {
            for (Twit t : twitList
            ) {
                System.out.println("Id:" + t.getId() + " " + t);
            }
        }
        return 1;
    }

    @Override
    public List<Twit> findTwitById(User user) {
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return twitRepositoryImpel.showTwitById(user.getId());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public Twit findTwitByTwoId(User user,Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return twitRepositoryImpel.findTwitByTwoId(user.getId(),id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public void showAllTwit(User user,Integer number){
        List<Twit> twitList = findTwit(number);
        if(twitList == null )
            System.out.println("No Twit found!");
        else{
            System.out.println("like(1),dislike(2),comment(3),showComment(4),replyComment(5),next(6),exit(7)");
            Integer command;
            for (Twit t:twitList
            ) {
                showTwit(t);
                System.out.print("Enter a number:");
                command = utility.giveIntegerInput();
                if(command > 6 || command < 1 )
                    return;
                else{
                    switch(command){
                        case 1:
                            likeTwit(t);
                            break;

                        case 2:
                            dislikeTwit(t);
                            break;

                        case 3:
                            Comment comment = Comment.builder().user(t.getUser()).twit(t).build();
                            commentServiceImpel.add(comment);
                            break;

                        case 4:
                            commentServiceImpel.showComment(t);
                            break;

                        case 5:
                            commentServiceImpel.replyComment(user);
                            break;

                        case 6:

                            break;

                        case 7:
                            return;

                        default:
                            System.out.println("You enter a wrong number!");
                    }
                }

            }
        }
    }

    @Override
    public List<Twit> findTwit(Integer number){
        if(number == 8) {
            return findAllTwit();
        }
        else{
            System.out.print("Enter user name:");
            String userName = input.nextLine();
            User user = userServiceImpel.findByUserName(userName);
            return findTwitById(user);
        }
    }

    @Override
    public void showTwit(Twit twit){
        System.out.println(" id : " + twit.getId() +
                " twit : " + twit.getTwit() +
                " likeNumber: " + twit.getLikeNumber() +
                " dislikeNumber :" + twit.getDislikeNumber() +
                " fullName: " + twit.getUser().getFullName() +
                " userName: " + twit.getUser().getUserName());
    }

    public void likeTwit(Twit twit){
        if(twit.getLikeNumber() == null )
            twit.setLikeNumber(1);
        else
            twit.setLikeNumber(twit.getLikeNumber()+1);
        super.update(twit);
    }

    public void dislikeTwit(Twit twit){
        if(twit.getDislikeNumber() == null )
            twit.setDislikeNumber(1);
        else
            twit.setDislikeNumber(twit.getDislikeNumber()+1);
        super.update(twit);
    }

    @Override
    public List<Twit> findAllTwit() {
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return twitRepositoryImpel.findAllTwit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}

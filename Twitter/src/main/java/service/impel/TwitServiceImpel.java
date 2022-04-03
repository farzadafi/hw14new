package service.impel;

import connection.SessionFactorySingleton;
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
}

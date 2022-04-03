package service.impel;

import entity.Twit;
import service.TwitService;

import java.util.Scanner;

public class TwitServiceImpel extends GenericServiceImpel<Twit,Integer> implements TwitService {

    private final Scanner input = new Scanner(System.in);

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
}

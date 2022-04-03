package view;

import entity.User;
import service.impel.CommentServiceImpel;
import service.impel.FollowerServiceImpel;
import service.impel.TwitServiceImpel;
import service.impel.UserServiceImpel;
import utility.Utility;

import java.util.Scanner;

public class Menu {
    private final Utility utility = new Utility();
    private final UserServiceImpel userServiceImpel = new UserServiceImpel();
    private final Scanner input = new Scanner(System.in);
    private final TwitServiceImpel twitServiceImpel = new TwitServiceImpel();
    private final CommentServiceImpel commentServiceImpel = new CommentServiceImpel();
    private final FollowerServiceImpel followerServiceImpel = new FollowerServiceImpel();

    public int mainMenu() {
        System.out.println("\n**********WELCOME**********");
        System.out.println("1-Enter.");
        System.out.println("2-Register.");
        System.out.println("3-Exit.");
        System.out.print("Please enter your command:");
        Integer command = utility.giveIntegerInput();
        switch (command) {
            case 1:
                return 1;

            case 2:
                return 2;

            case 3:
                return 3;

            default:
                return 4;
        }
    }

    public void registerMenu(){
        userServiceImpel.add(null);
    }

    public void enterMenu(){
        System.out.print("Please enter your username:");
        String userName = input.nextLine();
        System.out.print("Please enter your password:");
        String password = input.nextLine();
        User user = userServiceImpel.findByUserName(userName);
        if(user == null)
            System.out.println("You enter wrong user name or password!");
        else if(password.equals(user.getPassword()))
            userMenu(user);
        else
            System.out.println("You enter wrong user name or password!");

    }

    public void userMenu(User user) {
        boolean finalWhile = true;
        while (finalWhile) {
            System.out.println("\n****** Hi " + user.getFullName() + " ******");
            System.out.println("1-View my information.");
            System.out.println("2-Edit my information.");
            System.out.println("3-Delete my!");
            System.out.println("4-Add twit.");
            System.out.println("5-Delete twit.");
            System.out.println("6-Edit twit.");
            System.out.println("7-View my Twit.");
            System.out.println("8-View All Twit.");
            System.out.println("9-View Twit with UserName.");
            System.out.println("10-Update comment.");
            System.out.println("11-Delete comment.");
            System.out.println("12-Show my comment.");
            System.out.println("13-Find user with username.");
            System.out.println("14-Follow user.");
            System.out.println("15-Unfollow user.");
            System.out.println("16-Show followers.");
            System.out.println("20-Exit.");
            System.out.print("Select a number:");
            Integer command = utility.giveIntegerInput();
            switch (command) {
                case 1:
                    System.out.println(user);
                    break;

                case 2:
                    userServiceImpel.update(user);
                    break;

                case 3:
                    userServiceImpel.delete(user);
                    finalWhile = false;
                    break;

                case 4:
                    userServiceImpel.addTwit(user);
                    break;

                case 5:
                    userServiceImpel.deleteTwit(user);
                    break;

                case 6:
                    userServiceImpel.updateTwit(user);
                    break;

                case 7:
                    userServiceImpel.showMyTwit(user);
                    break;

                case 8:
                    userServiceImpel.showAllTwit(user,8);
                    break;

                case 9:
                    userServiceImpel.showAllTwit(user,9);
                    break;

                case 10:
                    userServiceImpel.updateComment(user);
                    break;

                case 11:
                    userServiceImpel.deleteComment(user);
                    break;

                case 12:
                    userServiceImpel.showComment(user);
                    break;

                case 13:
                    userServiceImpel.checkUsername();
                    break;

                case 14:
                    userServiceImpel.setFollowers(user);
                    break;

                case 15:
                    userServiceImpel.unSetFollowers(user);
                    break;

                case 16:
                    userServiceImpel.showFollowers(user);
                    break;


                case 20:
                    System.out.println("Good luck!");
                    finalWhile = false;
                    break;

                default :
                    System.out.println("You enter a wrong number!");
            }
        }
    }








}

package view;

import entity.User;
import service.*;

import java.util.Scanner;

public class Menu {
    private final Utility utility = new Utility();
    private final UserService userService = new UserService();
    private final Scanner input = new Scanner(System.in);
    private final TwitService twitService = new TwitService();
    private final CommentService commentService = new CommentService();
    private final FollowersService followersService = new FollowersService();

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
        userService.add(null);
    }

    public void enterMenu(){
        System.out.print("Please enter your username:");
        String userName = input.nextLine();
        System.out.print("Please enter your password:");
        String password = input.nextLine();
        User user = userService.findByUserName(userName);
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
                    userService.update(user);
                    break;

                case 3:
                    userService.delete(user);
                    finalWhile = false;
                    break;

                case 4:
                    twitService.add(user);
                    break;

                case 5:
                    twitService.delete(user);
                    break;

                case 6:
                    twitService.update(user);
                    break;

                case 7:
                    twitService.showMyTwit(user);
                    break;

                case 8:
                    twitService.showAllTwit(user,8);
                    break;

                case 9:
                    twitService.showAllTwit(user,9);

                case 10:
                    commentService.update(user);
                    break;

                case 11:
                    commentService.delete(user);
                    break;

                case 12:
                    commentService.showComment(user.getId());
                    break;

                case 13:
                    userService.CheckUserName();
                    break;

                case 14:
                    userService.setFollowers(user);
                    break;

                case 15:
                    userService.unSetFollowers(user);
                    break;

                case 16:
                    followersService.showFollowers(user);
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

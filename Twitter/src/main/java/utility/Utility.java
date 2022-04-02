package utility;

import exception.InvalidName;
import exception.InvalidPassword;
import service.impel.UserServiceImpel;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility {
    private final Scanner input = new Scanner(System.in);
    private final UserServiceImpel userServiceImpel = new UserServiceImpel();

    public Integer giveIntegerInput() {
        int i;
        while (true) {
            try {
                i = input.nextInt();
                input.nextLine();
                return i;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.print("Just enter number please:");
            }
        }
    }

    public String setFullName(){
        String fullName;
        while(true){
            System.out.print("Enter name(just alpha):");
            try {
                fullName = input.nextLine();
                checkName(fullName);
                return fullName;
            }catch (InvalidName except){
                System.out.println(except.getMessage());
            }
        }
    }

    public void checkName(String name){
        if(name.length() < 3 )
            throw new InvalidName("name should be more than 2 character!");
        for (Character ch:name.toCharArray()
        ) {
            if(Character.isDigit(ch))
                throw new InvalidName("name can not have number!");
        }
        for (Character ch:name.toCharArray()
        ) {
            if(!Character.isAlphabetic(ch))
                throw new InvalidName("name can't have Sign(!,@,#,%,...)");
        }
    }

    public String setUserName(){
        String userName;
        while (true) {
            while (true) {
                System.out.print("Enter UserName:");
                    userName = input.nextLine();
                    if(userName.length() > 2 )
                        break;
                    else
                        System.out.println("UserName must bigger than 2!");
            }
            var result = userServiceImpel.findByUserName(userName);
            if(result == null )
               break;
            else
               System.out.println("You enter a wrong nationalId!");
        }
        return userName;
    }

    public String setPassword(){
        String password;
        while(true) {
            System.out.print("Enter your password:");
            try {
                password = input.nextLine();
                passwordCheck(password);
                return password;
            } catch (InvalidPassword except) {
                System.out.println(except.getMessage());
            }
        }
    }

    public void passwordCheck(String password){
        if(password.length() < 3 )
            throw new InvalidPassword("password should be more than 2 ");
        char[] passwordArray = password.toCharArray();
        char[] signArray =  new char[] {'!','@','#','$','%','^','&','*','(',')','-','+','=','.',',','>','<','?','/','|',':',';'};
        int space = 0,lowerCase = 0,upperCase = 0,sign = 0,digit = 0;
        for(int i=0;i<passwordArray.length;i++)
            if(Character.isSpaceChar(passwordArray[i]))
                ++space;
        for(int i = 0;i<passwordArray.length;i++)
            if(Character.isUpperCase(passwordArray[i]))
                ++upperCase;
        for(int i = 0;i<passwordArray.length;i++)
            if(Character.isLowerCase(passwordArray[i]))
                ++lowerCase;
        for(int i = 0;i<passwordArray.length;i++)
            if(Character.isDigit(passwordArray[i]))
                ++digit;
        for(int i=0;i<signArray.length;i++)
            for(int j=0;j<passwordArray.length;j++)
                if(signArray[i] == passwordArray[j])
                    ++sign;
        if( (space == 0) || (lowerCase == 0) || (upperCase == 0) || (sign == 0) || (digit == 0) )
            throw new InvalidPassword("password should have space+lowerCase+upperCase+sign+digit!");
    }

}

import view.Menu;

public class App {
    public static void main(String[] args) {
        Menu menu = new Menu();
        while (true) {
            switch (menu.mainMenu()) {
                case 1:
                    menu.enterMenu();
                    break;

                case 2:
                    menu.registerMenu();
                    break;

                case 3:
                    System.out.println("Have a nice day!");
                    System.exit(0);

                case 4:
                    System.out.println("You enter a wrong number");
                    break;
            }
        }
    }
}


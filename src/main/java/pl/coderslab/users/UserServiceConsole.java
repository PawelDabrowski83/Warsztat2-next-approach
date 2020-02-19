package pl.coderslab.users;

import pl.coderslab.userGroup.UserGroupDao;

import java.util.Optional;
import java.util.Scanner;

public class UserServiceConsole {

    private static final UserDao USER_DAO = new UserDao();
    private static final UserGroupDao USERGROUP_DAO = new UserGroupDao();
    private static final String COMMAND_LINE = "Wybierz wpis, aby modyfikować, N aby dodać nowy, X aby wyjść";
    private static final String USER_MENU = "D - kasuj, E - edytuj, N - stwórz nowego, X - anuluj";

    public static void manage() {

        User[] users = USER_DAO.findAll();


        for (User u : users) {
            System.out.println("[ " + u.getId() + " ] " + u);
        }
        System.out.println(COMMAND_LINE);

        int userId = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while(scanner.hasNextLine()) {
                String key = scanner.nextLine().trim().toLowerCase();

                switch (key) {
                    case "n":
                        System.out.println("Nowy użytkownik");
                        break;
                    case "x":
                        System.out.println("Exit");
                        return;
                    default:
                        System.out.println("Sprawdzam czy wpisano prawidłowe id");
                        try {
                            userId = Integer.parseInt(key);
                            Optional<User> optionalUser = Optional.ofNullable(USER_DAO.read(userId));
                            User user = optionalUser.orElseGet(User::new);
                            if (user.getId() == 0) {
                                System.out.println("Nie ma takiego użytkownika. Naciśnij enter, aby kontynuować");
                            } else {
                                userSettings(scanner, user);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Niepoprawny numer.");
                            System.out.println(COMMAND_LINE);
                        }
                }
            }
        }
    }
    private static void userSettings(Scanner scanner, User user) {
        System.out.println(USER_MENU);
        while(scanner.hasNextLine()) {
            String key = scanner.nextLine().trim().toLowerCase();

            switch (key) {
                case "n":
                    System.out.println("stwórz nowego");
                    break;
                case "x":
                    System.out.println("Exit");
                    return;
                case "d":
                    System.out.println("Delete");
                    break;
                case "e":
                    System.out.println("Edycja");
                    break;
                default:
                    System.out.println(USER_MENU);
            }
            return;
        }
    }


}

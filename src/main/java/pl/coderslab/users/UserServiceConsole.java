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

        findAllUsers();

        int userId = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while(scanner.hasNextLine()) {
                String key = scanner.nextLine().trim().toLowerCase();

                switch (key) {
                    case "n":
                        System.out.println("Nowy użytkownik");
                        User newUser = createUser(scanner);
                        System.out.println("User created: " + newUser);
                        findAllUsers();
                        break;
                    case "x":
                        System.out.println("Exit");
                        return;
                    default:
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
                            findAllUsers();
                        }
                }
            }
        }
    }

    private static void findAllUsers() {
        User[] users = USER_DAO.findAll();


        for (User u : users) {
            System.out.println("[ " + u.getId() + " ] " + u);
        }
        System.out.println(COMMAND_LINE);
    }

    private static void userSettings(Scanner scanner, User user) {
        System.out.println("Current user: " + user);
        System.out.println(USER_MENU);
        while(scanner.hasNextLine()) {
            String key = scanner.nextLine().trim().toLowerCase();

            switch (key) {
                case "n":
                    System.out.println("stwórz nowego");
                    User newUser = createUser(scanner);
                    System.out.println("User created: " + newUser);
                    findAllUsers();
                    return;
                case "x":
                    System.out.println("Exit");
                    return;
                case "d":
                    System.out.println("Delete");
                    user = deleteUser(user);
                    System.out.println("User deleted");
                    findAllUsers();
                    return;
                case "e":
                    System.out.println("Edycja");
                    user = editUser(scanner, user);
                    System.out.println("User edited: " + user);
                    findAllUsers();
                    return;
                default:
                    findAllUsers();
                    return;
            }
        }
    }

    private static User createUser(Scanner scanner) {
        String[] messages = {"Podaj nazwę użytkownika:", "Email:", "Hasło:"};
        String[] buffer = new String[messages.length];
        int counter = 0;
        while(counter < messages.length) {
            System.out.println(messages[counter]);
            buffer[counter] = scanner.nextLine().trim();
            counter++;
        }
        System.out.println("Tworzę nowego użytkownika");
        User user = new User(buffer[0], buffer[1], buffer[2]);
        user = USER_DAO.create(user);
        return user;
    }

    private static User editUser(Scanner scanner, User user) {
        String[] messages = {"Podaj nazwę użytkownika: ", "Email: ", "Hasło: "};
        String[] buffer = new String[messages.length];
        buffer[0] = user.getName();
        buffer[1] = user.getEmail();
        buffer[2] = user.getPassword();
        int counter = 0;
        while(counter < messages.length) {
            System.out.println("Wpisz nowe dane lub wciśnij ENTER, aby zostawić bez zmian");
            System.out.println(messages[counter] + buffer[counter]);
            String key = scanner.nextLine().trim();
            if(!"".equals(key)) {
                buffer[counter] = key;
            }
            counter++;
        }
        System.out.println("Edytuję użytkownika");
        user.setName(buffer[0]);
        user.setEmail(buffer[1]);
        user.setPassword(buffer[2]);
        USER_DAO.update(user);
        return user;
    }

    private static User deleteUser(User user) {
        USER_DAO.delete(user.getId());
        return new User();

    }


}

package pl.coderslab.users;

import java.util.Optional;
import java.util.Scanner;

public class UserManagement {

    private static final String COMMAND_LINE = "C - create, R - read, U - update, D - delete, F - findall, X - exit";

    private static final UserDao userDao = new UserDao();

    public static void manage() {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Wybierz rodzaj operacji na użytkownikach. Wpisz X aby zakończyć");
            System.out.println(COMMAND_LINE);
            while (scanner.hasNextLine()) {
                String key = scanner.nextLine().trim().toLowerCase();

                switch (key) {
                    case "c":
                        System.out.println("Create");
                        create(scanner);
                        break;
                    case "r":
                        System.out.println("Read");
                        read(scanner);
                        break;
                    case "u":
                        System.out.println("Update");
                        update(scanner);
                        break;
                    case "d":
                        System.out.println("Delete");
                        break;
                    case "x":
                        System.out.println("Exit");
                        return;
                    default:
                        System.out.println(COMMAND_LINE);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Nullpointer");
        }
    }


    private static void create(Scanner scanner) {

        System.out.println("MODUŁ CREATE - wpisz X aby zakończyć");
        String[] messages = {"Wpisz nazwę użytkownika", "Podaj email", "Podaj hasło"};
        String[] results = new String[messages.length];
        int counter = 0;
        while(counter < messages.length) {
            System.out.println(messages[counter]);
            String key = scanner.nextLine().trim().toLowerCase();
            if("x".equals(key)) {
                System.out.println("Zamykam moduł CREATE");
                System.out.println(COMMAND_LINE);
                return;
            }
            results[counter] = key;
            counter++;
        }
//        UserDao userDao = new UserDao();
        User user = new User(results[0], results[1], results[2]);
        System.out.println("Zapisuję użytkownika: " + user);
        user = userDao.create(user);
        System.out.println("zapisany użytkownik: " + user);
        System.out.println("Naciśnij ENTER, aby kontynuować");
    }

    private static void read(Scanner scanner) {

        System.out.println("MODUŁ READ - wpisz X aby zakończyć");
        String[] messages = {"Podaj id użytkownika, którego chcesz wczytać"};
        int counter = 0;
        int userId = 0;
        while(counter == 0) {
            System.out.println(messages[0]);
            String key = scanner.nextLine().trim().toLowerCase();
            if("x".equals(key)) {
                System.out.println("Zamykam moduł READ");
                System.out.println(COMMAND_LINE);
                return;
            }
            try {
                userId = Integer.parseInt(key);
                counter++;
            } catch (NumberFormatException e) {
                System.out.println("Podaj poprawny numer lub X aby wyjść");
            }
        }
        Optional<User> optionalUser = Optional.ofNullable(userDao.read(userId));
        User user = optionalUser.orElseGet(User::new);
        if(user.getId() == 0) {
            System.out.println("Nie odnaleziono użytkownika");
        } else {
            System.out.println("Wczytany użytkownik to: " + user);
        }

        System.out.println("Naciśnij ENTER aby kontynuować");

    }

    private static void update(Scanner scanner) {

        System.out.println("MODUŁ UPDATE - wpisz X aby zakończyć");
        String message = "Podaj id użytkownika, którego chcesz zmodyfikować";
        int counter = 0;
        int userId = 0;
        while(counter == 0) {
            System.out.println(message);
            String key = scanner.nextLine().trim().toLowerCase();
            if ("x".equals(key)) {
                System.out.println("Zamykam moduł UPDATE");
                System.out.println(COMMAND_LINE);
                return;
            }
            try {
                userId = Integer.parseInt(key);
                counter++;
            } catch (NumberFormatException e) {
                System.out.println("Podaj poprawny nr lub X aby zakończyć");
            }
        }

        Optional<User> optionalUser = Optional.ofNullable(userDao.read(userId));
        User user = optionalUser.orElseGet(User::new);

        if (user.getId() == 0) {
            System.out.println("Nie odnaleziono użytkownika. Naciśnij ENTER, aby kontynuować.");
        } else {
            System.out.println("Dane użytkownika: " + user);
        }

    }

}

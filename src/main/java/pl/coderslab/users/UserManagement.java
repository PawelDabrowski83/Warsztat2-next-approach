package pl.coderslab.users;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;
import java.util.Scanner;

public class UserManagement {

    private static final String COMMAND_LINE = "C - create, R - read, U - update, D - delete, F - findall, X - exit";

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
                        break;
                    case "u":
                        System.out.println("Update");
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
//        System.out.println("Zapisuję użytkownika:" +
//                "Nazwa: " + results[0] +
//                "Email: " + results[1] +
//                "Hasło: " + results[2]);
        UserDao userDao = new UserDao();
        User user = new User(results[0], results[1], results[2]);
        System.out.println("Zapisuję użytkownika: " + user);
        user = userDao.create(user);
        System.out.println("zapisany użytkownik: " + user);
        System.out.println("Naciśnij ENTER, aby kontynuować");



    }

}

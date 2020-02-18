package pl.coderslab.users;

import java.util.Scanner;

public class UserManagement {

    private static final String COMMAND_LINE = "C - create, R - read, U - update, D - delete, F - findall, X - exit";

    public static void manage() {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Wybierz rodzaj operacji na użytkownikach. Wpisz X aby zakończyć");
            System.out.println(COMMAND_LINE);
            while (scanner.hasNextLine()) {
                String key = scanner.nextLine().trim();

                switch (key) {
                    case "C":
                        System.out.println("Create");
                        break;
                    case "R":
                        System.out.println("Read");
                        break;
                    case "U":
                        System.out.println("Update");
                        break;
                    case "D":
                        System.out.println("Delete");
                        break;
                    case "X":
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

    private static void create() {

    }

}

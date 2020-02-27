package pl.coderslab.solution;

import pl.coderslab.exercise.Exercise;
import pl.coderslab.exercise.ExerciseDao;
import pl.coderslab.users.User;
import pl.coderslab.users.UserDao;

import java.util.Scanner;

public class SolutionAssignmentConsole {

    private static final String COMMAND_LINE = "Choose your action: A - assign exercise to user, V - view user's solutions, X - exit";
    private static final UserDao USER_DAO = new UserDao();
    private static final ExerciseDao EXERCISE_DAO = new ExerciseDao();

    public static void manage() {

        System.out.println(COMMAND_LINE);

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String key = scanner.nextLine().trim().toLowerCase();

                switch (key) {
                    case "a":
                        System.out.println("Assign exercise to user");
                        break;
                    case "v":
                        System.out.println("View user's solutions");
                        break;
                    case "x":
                        System.out.println("Exit");
                        return;
                }
                System.out.println(COMMAND_LINE);
            }
        }
    }

    private static void assignExerciseToUser(Scanner scanner) {

        getAllUsers();
        while (scanner.hasNextLine()) {
            String key = scanner.nextLine().trim().toLowerCase();

            switch (key) {
                case "x":
                    System.out.println("Exit");
                    return;
                default:
                    try {
                        int userId = Integer.parseInt(key);
                    } catch (NumberFormatException e) {

                    }
            }
        }




    }

    private static void getAllUsers() {
        User[] users = USER_DAO.findAll();

        for (User u : users) {
            System.out.println("[ " + u.getId() + " ] " + u);
        }
        System.out.println("Enter user id to select or X for exit");
    }

    private static Exercise[] getAllExercises() {
        return EXERCISE_DAO.findAll();
    }
}

package pl.coderslab.solution;

import pl.coderslab.exercise.Exercise;
import pl.coderslab.exercise.ExerciseDao;
import pl.coderslab.users.User;
import pl.coderslab.users.UserDao;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

public class SolutionAssignmentConsole {

    private static final String COMMAND_LINE = "Choose your action: A - assign exercise to user, V - view user's solutions, X - exit";
    private static final UserDao USER_DAO = new UserDao();
    private static final ExerciseDao EXERCISE_DAO = new ExerciseDao();
    private static final SolutionDao SOLUTION_DAO = new SolutionDao();

    public static void manage() {

        System.out.println(COMMAND_LINE);

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String key = scanner.nextLine().trim().toLowerCase();

                switch (key) {
                    case "a":
                        System.out.println("Assign exercise to user");
                        assignExerciseToUser(scanner);
                        break;
                    case "v":
                        System.out.println("View user's solutions");
                        viewSolutionsByUserId(scanner);
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

        Solution solution = new Solution();

        while (scanner.hasNextLine()) {
            String key = scanner.nextLine().trim().toLowerCase();

            switch (key) {
                case "x":
                    System.out.println("Exit");
                    return;
                default:
                    try {
                        int userId = Integer.parseInt(key);
                        Optional<User> optionalUser = Optional.ofNullable(USER_DAO.read(userId));
                        User user = optionalUser.orElseGet(User::new);
                        if (user.getId() == 0) {
                            System.out.println("No User with given id");
                        } else {
                            solution.setUsersId(user.getId());
                            assignExerciseToSolution(scanner, solution);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID should be a proper number");
                    }
            }
            getAllUsers();
        }
    }

    private static void assignExerciseToSolution (Scanner scanner, Solution solution) {
        getAllExercises();

        while (scanner.hasNextLine()) {
            String key = scanner.nextLine().trim().toLowerCase();

            switch (key) {
                case "x":
                    System.out.println("Exit");
                    return;
                default:
                    try {
                        int exerciseId = Integer.parseInt(key);
                        Optional<Exercise> optionalExercise = Optional.ofNullable(EXERCISE_DAO.read(exerciseId));
                        Exercise exercise = optionalExercise.orElseGet(Exercise::new);
                        if (exercise.getId() == 0) {
                            System.out.println("No Exercise on given id");
                        } else {
                            solution.setExerciseId(exercise.getId());
                            solution.setCreated(LocalDateTime.now());
                            System.out.println("Saving solution: " + solution);
                            SOLUTION_DAO.create(solution);
                            System.out.println("Solution created: " + solution);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format");
                    }
            }
            getAllExercises();
        }
    }

    private static void viewSolutionsByUserId (Scanner scanner) {
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
                        Optional<User> optionalUser = Optional.ofNullable(USER_DAO.read(userId));
                        User user = optionalUser.orElseGet(User::new);
                        if (user.getId() == 0) {
                            System.out.println("No User with given ID");
                        } else {
                            Solution[] solutions = SOLUTION_DAO.findAllByUserId(user.getId());
                            System.out.println("Solution by current user: " + user);
                            for (Solution s : solutions) {
                                System.out.println("[ " + s.getId() + " ] " + s);
                            }
                        }
                        return;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number");
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

    private static void getAllExercises() {
        Exercise[] exercises = EXERCISE_DAO.findAll();

        for (Exercise e : exercises) {
            System.out.println("[ " + e.getId() + " ] " + e);
        }
        System.out.println("Enter exercise id to select or X to exit");
    }
}

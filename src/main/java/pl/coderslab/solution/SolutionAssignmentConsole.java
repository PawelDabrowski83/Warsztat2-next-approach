package pl.coderslab.solution;

import pl.coderslab.exercise.ExerciseEntity;
import pl.coderslab.exercise.ExerciseDao;
import pl.coderslab.users.UserEntity;
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

        SolutionEntity solutionEntity = new SolutionEntity();

        while (scanner.hasNextLine()) {
            String key = scanner.nextLine().trim().toLowerCase();

            switch (key) {
                case "x":
                    System.out.println("Exit");
                    return;
                default:
                    try {
                        int userId = Integer.parseInt(key);
                        Optional<UserEntity> optionalUser = Optional.ofNullable(USER_DAO.read(userId));
                        UserEntity userEntity = optionalUser.orElseGet(UserEntity::new);
                        if (userEntity.getId() == 0) {
                            System.out.println("No User with given id");
                        } else {
                            solutionEntity.setUsersId(userEntity.getId());
                            assignExerciseToSolution(scanner, solutionEntity);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID should be a proper number");
                    }
            }
            getAllUsers();
        }
    }

    private static void assignExerciseToSolution (Scanner scanner, SolutionEntity solutionEntity) {
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
                        Optional<ExerciseEntity> optionalExercise = Optional.ofNullable(EXERCISE_DAO.read(exerciseId));
                        ExerciseEntity exerciseEntity = optionalExercise.orElseGet(ExerciseEntity::new);
                        if (exerciseEntity.getId() == 0) {
                            System.out.println("No Exercise on given id");
                        } else {
                            solutionEntity.setExerciseId(exerciseEntity.getId());
                            solutionEntity.setCreated(LocalDateTime.now());
                            System.out.println("Saving solution: " + solutionEntity);
                            SOLUTION_DAO.create(solutionEntity);
                            System.out.println("Solution created: " + solutionEntity);
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
                        Optional<UserEntity> optionalUser = Optional.ofNullable(USER_DAO.read(userId));
                        UserEntity userEntity = optionalUser.orElseGet(UserEntity::new);
                        if (userEntity.getId() == 0) {
                            System.out.println("No User with given ID");
                        } else {
                            SolutionEntity[] solutionEntities = SOLUTION_DAO.findAllByUserId(userEntity.getId());
                            System.out.println("Solution by current user: " + userEntity);
                            for (SolutionEntity s : solutionEntities) {
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
        UserEntity[] userEntities = USER_DAO.findAll();

        for (UserEntity u : userEntities) {
            System.out.println("[ " + u.getId() + " ] " + u);
        }
        System.out.println("Enter user id to select or X for exit");
    }

    private static void getAllExercises() {
        ExerciseEntity[] exerciseEntities = EXERCISE_DAO.findAll();

        for (ExerciseEntity e : exerciseEntities) {
            System.out.println("[ " + e.getId() + " ] " + e);
        }
        System.out.println("Enter exercise id to select or X to exit");
    }
}

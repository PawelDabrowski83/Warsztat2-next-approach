package pl.coderslab.exercise;

import java.util.Optional;
import java.util.Scanner;

public class ExerciseServiceConsole {

    private static final ExerciseDao EXERCISE_DAO = new ExerciseDao();
    private static final String COMMAND_LINE = "Choose id for details, N for new, X for exit";
    private static final String DETAILS_MENU = "Choose operation: D - delete, E - edit, N - new, X - exit";

    public static void manage() {

        try (Scanner scanner = new Scanner(System.in)) {
            findAllExercises();
            while (scanner.hasNextLine()) {


                String key = scanner.nextLine().trim().toLowerCase();

                switch (key) {
                    case "x":
                        System.out.println("Exit");
                        return;
                    case "n":
                        System.out.println("New Exercise");
                        newExercise(scanner);
                        break;
                    default:
                        try {
                            int exerciseId = Integer.parseInt(key);
                            Optional<Exercise> optionalExercise = Optional.ofNullable(EXERCISE_DAO.read(exerciseId));
                            Exercise exercise = optionalExercise.orElseGet(Exercise::new);

                            if (exercise.getId() == 0) {
                                System.out.println("No such exercise or invalid id");
                            } else {
                                exerciseDetails(scanner, exercise);
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Niepoprawny numer");
                        }
                }
                findAllExercises();
            }


        }



    }

    private static void findAllExercises() {
        Exercise[] exercises = EXERCISE_DAO.findAll();

        for (Exercise exercise : exercises) {
            System.out.println("[ " + exercise.getId() + " ] " + exercise);
        }
        System.out.println(COMMAND_LINE);
    }

    private static void exerciseDetails(Scanner scanner, Exercise exercise) {
        System.out.println("Selected Exercise: " + exercise);
        System.out.println(DETAILS_MENU);

        while (scanner.hasNextLine()) {

            String key = scanner.nextLine().trim().toLowerCase();

            switch (key) {
                case "n":
                    System.out.println("New Exercise");
                    newExercise(scanner);
                    return;
                case "e":
                    System.out.println("Edit Exercise");
                    editExercise(scanner, exercise);
                    return;
                case "d":
                    System.out.println("Delete Exercise");
                    deleteExercise(exercise);
                    return;
                case "x":
                    System.out.println("Exit details");
                    return;
                default:
                    System.out.println("Command not recognized");
                    System.out.println(DETAILS_MENU);
            }
        }
    }

    private static void newExercise(Scanner scanner) {
        String[] messages = {"Enter title of Exercise", "Enter description"};
        String[] results = new String[messages.length];
        int counter = 0;
            for (String ignored : messages) {
                System.out.println(messages[counter]);
                results[counter] = scanner.nextLine().trim();
                counter++;
            }

        Exercise exercise = new Exercise(results[0], results[1]);
        exercise = EXERCISE_DAO.create(exercise);
        System.out.println("Exercise created: " + exercise);
    }

    private static void editExercise(Scanner scanner, Exercise exercise) {
        String introMessage = "Enter new values or press ENTER to leave them unchanged";
        String[] messages = {
            "Exercise Title: " + exercise.getTitle(),
            "Exercise Description: " + exercise.getDescription()
            };
        String[] results = {exercise.getTitle(), exercise.getDescription()};
        int counter = 0;
        System.out.println(introMessage);

        for (String message : messages) {
            System.out.println(message);
            String key = scanner.nextLine().trim();
            if (!"".equals(key)) {
                results[counter] = key;
            }
            counter++;
        }
        exercise.setTitle(results[0]);
        exercise.setDescription(results[1]);
        EXERCISE_DAO.update(exercise);
    }

    private static void deleteExercise(Exercise exercise) {
        EXERCISE_DAO.delete(exercise.getId());
        System.out.println("User deleted");
    }
}

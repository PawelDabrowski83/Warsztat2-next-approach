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
                            Optional<ExerciseEntity> optionalExercise = Optional.ofNullable(EXERCISE_DAO.read(exerciseId));
                            ExerciseEntity exerciseEntity = optionalExercise.orElseGet(ExerciseEntity::new);

                            if (exerciseEntity.getId() == 0) {
                                System.out.println("No such exercise or invalid id");
                            } else {
                                exerciseDetails(scanner, exerciseEntity);
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
        ExerciseEntity[] exerciseEntities = EXERCISE_DAO.findAll();

        for (ExerciseEntity exerciseEntity : exerciseEntities) {
            System.out.println("[ " + exerciseEntity.getId() + " ] " + exerciseEntity);
        }
        System.out.println(COMMAND_LINE);
    }

    private static void exerciseDetails(Scanner scanner, ExerciseEntity exerciseEntity) {
        System.out.println("Selected Exercise: " + exerciseEntity);
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
                    editExercise(scanner, exerciseEntity);
                    return;
                case "d":
                    System.out.println("Delete Exercise");
                    deleteExercise(exerciseEntity);
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

        ExerciseEntity exerciseEntity = new ExerciseEntity(results[0], results[1]);
        exerciseEntity = EXERCISE_DAO.create(exerciseEntity);
        System.out.println("Exercise created: " + exerciseEntity);
    }

    private static void editExercise(Scanner scanner, ExerciseEntity exerciseEntity) {
        String introMessage = "Enter new values or press ENTER to leave them unchanged";
        String[] messages = {
            "Exercise Title: " + exerciseEntity.getTitle(),
            "Exercise Description: " + exerciseEntity.getDescription()
            };
        String[] results = {exerciseEntity.getTitle(), exerciseEntity.getDescription()};
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
        exerciseEntity.setTitle(results[0]);
        exerciseEntity.setDescription(results[1]);
        EXERCISE_DAO.update(exerciseEntity);
    }

    private static void deleteExercise(ExerciseEntity exerciseEntity) {
        EXERCISE_DAO.delete(exerciseEntity.getId());
        System.out.println("Exercise deleted");
    }
}

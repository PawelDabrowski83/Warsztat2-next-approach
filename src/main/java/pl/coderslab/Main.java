package pl.coderslab;

import pl.coderslab.exercise.ExerciseEntity;
import pl.coderslab.exercise.ExerciseDao;
import pl.coderslab.exercise.ExerciseServiceConsole;
import pl.coderslab.main.MainService;
import pl.coderslab.solution.SolutionDto;
import pl.coderslab.solution.SolutionEntity;
import pl.coderslab.solution.SolutionAssignmentConsole;
import pl.coderslab.solution.SolutionDao;
import pl.coderslab.userGroup.UserGroupEntity;
import pl.coderslab.userGroup.UserGroupDao;
import pl.coderslab.userGroup.UserGroupServiceConsole;
import pl.coderslab.users.UserEntity;
import pl.coderslab.users.UserDao;
import pl.coderslab.users.UserServiceConsole;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private final static Random RANDOM = new Random();
    private final static int LIMITER = 1000;
    private final static String MENU_OPTIONS = "Choose your option: 1 - User management, 2 - UserGroup management," +
            "3 - Exercise management, 4 - Solutions management, X - exit";

    public static void main(String[] args) {

//        chooseService();

//        testUserGroupsConnection();
//        testUsers();
        SolutionDto[] dtos = MainService.getRecentSolutions(3);
        System.out.println("nothing");
    }

    private static void testUserGroupsConnection() {
        UserGroupDao userGroupDao = new UserGroupDao();
        UserGroupEntity[] userGroupEntities = userGroupDao.findAll();

        for (UserGroupEntity u : userGroupEntities) {
            System.out.println(u);
        }

    }

    private static void chooseService() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(MENU_OPTIONS);
            while (scanner.hasNextLine()) {
                String key = scanner.nextLine().trim().toLowerCase();

                switch (key) {
                    case "x":
                        System.out.println("Exit");
                        return;
                    default:
                        try {
                            int selectionNumber = Integer.parseInt(key);
                            switch (selectionNumber) {
                                case 1:
                                    UserServiceConsole.manage();
                                    break;
                                case 2:
                                    UserGroupServiceConsole.manage();
                                    break;
                                case 3:
                                    ExerciseServiceConsole.manage();
                                    break;
                                case 4:
                                    SolutionAssignmentConsole.manage();
                                    break;
                                default:
                                    System.out.println("Selection unrecognized");
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input");
                            System.out.println(MENU_OPTIONS);
                        }
                }
            }
        }
    }

    private static UserEntity safeReadUser (Optional<UserEntity> optionalUser)
    {
        return optionalUser.orElseGet(UserEntity::new);
    }

    private static void testUsers() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Robert");
        userEntity.setEmail("test." + RANDOM.nextInt(LIMITER) + "@wp.pl");
        userEntity.setPassword("lorem ipsum");
        UserDao userDao = new UserDao();
        userEntity = userDao.create(userEntity);
        System.out.println("id użytkownika: " + userEntity.getId());

        int id = 0;
        UserEntity userEntitySafe = safeReadUser(Optional.ofNullable(userDao.read(id)));
        System.out.println("safe: " + userEntitySafe);

        userEntitySafe.setName("Nowe imi222");
        userDao.update(userEntitySafe);
        UserEntity userEntity3 = safeReadUser(Optional.ofNullable(userDao.read(userEntitySafe.getId())));
        System.out.println("po zmianie nazwiska: " + userEntity3);

        UserEntity[] allUserEntities = userDao.findAll();
        System.out.println("");

    }

    private static void testUserGroups() {
        UserGroupDao userGroupDao = new UserGroupDao();
        UserGroupEntity userGroupEntity = new UserGroupEntity("Testowa" + RANDOM.nextInt(LIMITER));
        userGroupDao.create(userGroupEntity);

        int testUserGroupId = 0;
        Optional<UserGroupEntity> userGroupOptional = Optional.ofNullable(userGroupDao.read(testUserGroupId));
        userGroupEntity = userGroupOptional.orElseGet(UserGroupEntity::new);
//        userGroup = userGroupDao.read(testUserGroupId);
        System.out.println(userGroupEntity.getName());
        userGroupEntity.setName("Zmiana");
        userGroupDao.update(userGroupEntity);
//        userGroupDao.delete(testUserGroupId);
        UserGroupEntity[] userGroupEntities = userGroupDao.findAll();
        System.out.println("Lista" + Arrays.toString(userGroupEntities));
    }

    public static void testExercises() {
        ExerciseDao exerciseDao = new ExerciseDao();
        ExerciseEntity exerciseEntity = new ExerciseEntity("Title " + RANDOM.nextInt(LIMITER), "Description " + RANDOM.nextInt(LIMITER));
        exerciseDao.create(exerciseEntity);

        int testExerciseId = 0;
        Optional<ExerciseEntity> optionalExercise = Optional.ofNullable(exerciseDao.read(testExerciseId));
        exerciseEntity = optionalExercise.orElseGet(ExerciseEntity::new);

        System.out.println("GET tytuł: " + exerciseEntity.getTitle());

        ExerciseEntity[] exerciseEntities = exerciseDao.findAll();
        exerciseEntity = exerciseEntities[0];

        exerciseEntity.setDescription("Poprawiony opis");
        exerciseDao.update(exerciseEntity);

        optionalExercise = Optional.ofNullable(exerciseDao.read(exerciseEntity.getId()));
        exerciseEntity = optionalExercise.orElseGet(ExerciseEntity::new);

        System.out.println("Po aktualizacji: " + exerciseEntity.getDescription());

//        exerciseDao.delete(exercise.getId());
    }

    public static void testSolutions() {
        SolutionDao solutionDao = new SolutionDao();
        SolutionEntity solutionEntity = testSolutionRead(solutionDao);
        testSolutionUpdate(solutionDao, solutionEntity);
        SolutionEntity[] solutionEntities = solutionDao.findAll();
        solutionEntities = solutionDao.findAllByUserId(1);
        solutionEntities = solutionDao.findAllByExerciseId(0);
        UserDao userDao = new UserDao();
        UserEntity[] userEntities = userDao.findAllUserByUserGroupId(2);
        System.out.println(solutionEntities);
    }

    public static void testSolutionCreate(SolutionDao solutionDao) {

        SolutionEntity solutionEntity = new SolutionEntity(LocalDateTime.now(), LocalDateTime.now(), "Opis " + RANDOM.nextInt(LIMITER), 4, 1);
        solutionDao.create(solutionEntity);

    }

    public static SolutionEntity testSolutionRead(SolutionDao solutionDao) {

        int solutionId = 6;
        Optional<SolutionEntity> optionalSolution = Optional.ofNullable(solutionDao.read(solutionId));
        SolutionEntity solutionEntity = optionalSolution.orElseGet(SolutionEntity::new);

        System.out.println("Description+++ solution: " + solutionEntity.getCreated());
        return solutionEntity;
    }

    public static void testSolutionUpdate(SolutionDao solutionDao, SolutionEntity solutionEntity) {
        solutionEntity.setDescription("Nowy opis " + RANDOM.nextInt(LIMITER));
        solutionEntity.setUpdated(LocalDateTime.now());
        solutionEntity.getCreated();
        solutionDao.update(solutionEntity);
    }

}

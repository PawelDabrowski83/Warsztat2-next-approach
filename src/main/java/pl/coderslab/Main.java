package pl.coderslab;

import pl.coderslab.exercise.Exercise;
import pl.coderslab.exercise.ExerciseDao;
import pl.coderslab.exercise.ExerciseServiceConsole;
import pl.coderslab.solution.Solution;
import pl.coderslab.solution.SolutionDao;
import pl.coderslab.userGroup.UserGroup;
import pl.coderslab.userGroup.UserGroupDao;
import pl.coderslab.userGroup.UserGroupServiceConsole;
import pl.coderslab.users.User;
import pl.coderslab.users.UserDao;
import pl.coderslab.users.UserServiceConsole;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class Main {

    private final static Random RANDOM = new Random();
    private final static int LIMITER = 1000;

    public static void main(String[] args) {

//        UserManagement.manage();
//        UserServiceConsole.manage();
//        ExerciseServiceConsole.manage();
        UserGroupServiceConsole.manage();

    }

    private static User safeReadUser (Optional<User> optionalUser)
    {
        return optionalUser.orElseGet(User::new);
    }

    private static void testUsers() {
        User user = new User();
        user.setName("Robert");
        user.setEmail("test." + RANDOM.nextInt(LIMITER) + "@wp.pl");
        user.setPassword("lorem ipsum");
        UserDao userDao = new UserDao();
        user = userDao.create(user);
        System.out.println("id użytkownika: " + user.getId());

        int id = 0;
        User userSafe = safeReadUser(Optional.ofNullable(userDao.read(id)));
        System.out.println("safe: " + userSafe);

        userSafe.setName("Nowe imi222");
        userDao.update(userSafe);
        User user3 = safeReadUser(Optional.ofNullable(userDao.read(userSafe.getId())));
        System.out.println("po zmianie nazwiska: " + user3);

        User[] allUsers = userDao.findAll();
        System.out.println("");

    }

    private static void testUserGroups() {
        UserGroupDao userGroupDao = new UserGroupDao();
        UserGroup userGroup = new UserGroup("Testowa" + RANDOM.nextInt(LIMITER));
        userGroupDao.create(userGroup);

        int testUserGroupId = 0;
        Optional<UserGroup> userGroupOptional = Optional.ofNullable(userGroupDao.read(testUserGroupId));
        userGroup = userGroupOptional.orElseGet(UserGroup::new);
//        userGroup = userGroupDao.read(testUserGroupId);
        System.out.println(userGroup.getName());
        userGroup.setName("Zmiana");
        userGroupDao.update(userGroup);
//        userGroupDao.delete(testUserGroupId);
        UserGroup[] userGroups = userGroupDao.findAll();
        System.out.println("Lista" + Arrays.toString(userGroups));
    }

    public static void testExercises() {
        ExerciseDao exerciseDao = new ExerciseDao();
        Exercise exercise = new Exercise("Title " + RANDOM.nextInt(LIMITER), "Description " + RANDOM.nextInt(LIMITER));
        exerciseDao.create(exercise);

        int testExerciseId = 0;
        Optional<Exercise> optionalExercise = Optional.ofNullable(exerciseDao.read(testExerciseId));
        exercise = optionalExercise.orElseGet(Exercise::new);

        System.out.println("GET tytuł: " + exercise.getTitle());

        Exercise[] exercises = exerciseDao.findAll();
        exercise = exercises[0];

        exercise.setDescription("Poprawiony opis");
        exerciseDao.update(exercise);

        optionalExercise = Optional.ofNullable(exerciseDao.read(exercise.getId()));
        exercise = optionalExercise.orElseGet(Exercise::new);

        System.out.println("Po aktualizacji: " + exercise.getDescription());

//        exerciseDao.delete(exercise.getId());
    }

    public static void testSolutions() {
        SolutionDao solutionDao = new SolutionDao();
        Solution solution = testSolutionRead(solutionDao);
        testSolutionUpdate(solutionDao, solution);
        Solution[] solutions = solutionDao.findAll();
        solutions = solutionDao.findAllByUserId(1);
        solutions = solutionDao.findAllByExerciseId(0);
        UserDao userDao = new UserDao();
        User[] users = userDao.findAllUserByUserGroupId(2);
        System.out.println(solutions);
    }

    public static void testSolutionCreate(SolutionDao solutionDao) {

        Solution solution = new Solution(LocalDateTime.now(), LocalDateTime.now(), "Opis " + RANDOM.nextInt(LIMITER), 4, 1);
        solutionDao.create(solution);

    }

    public static Solution testSolutionRead(SolutionDao solutionDao) {

        int solutionId = 6;
        Optional<Solution> optionalSolution = Optional.ofNullable(solutionDao.read(solutionId));
        Solution solution = optionalSolution.orElseGet(Solution::new);

        System.out.println("Description+++ solution: " + solution.getCreated());
        return solution;
    }

    public static void testSolutionUpdate(SolutionDao solutionDao, Solution solution) {
        solution.setDescription("Nowy opis " + RANDOM.nextInt(LIMITER));
        solution.setUpdated(LocalDateTime.now());
        solution.getCreated();
        solutionDao.update(solution);
    }

}

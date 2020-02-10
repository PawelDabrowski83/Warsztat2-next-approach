package pl.coderslab;

import pl.coderslab.users.User;
import pl.coderslab.users.UserDao;

import java.sql.SQLOutput;
import java.util.Optional;
import java.util.Random;

public class Main {

    private final static Random RANDOM = new Random();
    private final static int LIMITER = 1000;

    public static void main(String[] args) {

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

    private static User safeReadUser (Optional<User> optionalUser) {
        return optionalUser.orElseGet(User::new);
    }
}

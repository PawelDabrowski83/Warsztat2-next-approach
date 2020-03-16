package pl.coderslab.users;

import pl.coderslab.userGroup.UserGroupDao;

import java.util.Optional;
import java.util.Scanner;

public class UserServiceConsole {

    private static final UserDao USER_DAO = new UserDao();
    private static final UserGroupDao USERGROUP_DAO = new UserGroupDao();
    private static final String COMMAND_LINE = "Wybierz wpis, aby modyfikować, N aby dodać nowy, X aby wyjść";
    private static final String USER_MENU = "D - kasuj, E - edytuj, N - stwórz nowego, X - anuluj";

    public static void manage() {

        findAllUsers();

        int userId = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while(scanner.hasNextLine()) {
                String key = scanner.nextLine().trim().toLowerCase();

                switch (key) {
                    case "n":
                        System.out.println("Nowy użytkownik");
                        UserEntity newUserEntity = createUser(scanner);
                        System.out.println("User created: " + newUserEntity);
                        findAllUsers();
                        break;
                    case "x":
                        System.out.println("Exit");
                        return;
                    default:
                        try {
                            userId = Integer.parseInt(key);
                            Optional<UserEntity> optionalUser = Optional.ofNullable(USER_DAO.read(userId));
                            UserEntity userEntity = optionalUser.orElseGet(UserEntity::new);
                            if (userEntity.getId() == 0) {
                                System.out.println("Nie ma takiego użytkownika. Naciśnij enter, aby kontynuować");
                            } else {
                                userSettings(scanner, userEntity);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Niepoprawny numer.");
                            findAllUsers();
                        }
                }
            }
        }
    }

    private static void findAllUsers() {
        UserEntity[] userEntities = USER_DAO.findAll();


        for (UserEntity u : userEntities) {
            System.out.println("[ " + u.getId() + " ] " + u);
        }
        System.out.println(COMMAND_LINE);
    }

    private static void userSettings(Scanner scanner, UserEntity userEntity) {
        System.out.println("Current user: " + userEntity);
        System.out.println(USER_MENU);
        while(scanner.hasNextLine()) {
            String key = scanner.nextLine().trim().toLowerCase();

            switch (key) {
                case "n":
                    System.out.println("stwórz nowego");
                    UserEntity newUserEntity = createUser(scanner);
                    System.out.println("User created: " + newUserEntity);
                    findAllUsers();
                    return;
                case "x":
                    System.out.println("Exit");
                    return;
                case "d":
                    System.out.println("Delete");
                    userEntity = deleteUser(userEntity);
                    System.out.println("User deleted");
                    findAllUsers();
                    return;
                case "e":
                    System.out.println("Edycja");
                    userEntity = editUser(scanner, userEntity);
                    System.out.println("User edited: " + userEntity);
                    findAllUsers();
                    return;
                default:
                    findAllUsers();
                    return;
            }
        }
    }

    private static UserEntity createUser(Scanner scanner) {
        String[] messages = {"Podaj nazwę użytkownika:", "Email:", "Hasło:"};
        String[] buffer = new String[messages.length];
        int counter = 0;
        while(counter < messages.length) {
            System.out.println(messages[counter]);
            buffer[counter] = scanner.nextLine().trim();
            counter++;
        }
        System.out.println("Tworzę nowego użytkownika");
        UserEntity userEntity = new UserEntity(buffer[0], buffer[1], buffer[2]);
        userEntity = USER_DAO.create(userEntity);
        return userEntity;
    }

    private static UserEntity editUser(Scanner scanner, UserEntity userEntity) {
        String[] messages = {"Podaj nazwę użytkownika: ", "Email: ", "Hasło: "};
        String[] buffer = new String[messages.length];
        buffer[0] = userEntity.getName();
        buffer[1] = userEntity.getEmail();
        buffer[2] = userEntity.getPassword();
        int counter = 0;
        while(counter < messages.length) {
            System.out.println("Wpisz nowe dane lub wciśnij ENTER, aby zostawić bez zmian");
            System.out.println(messages[counter] + buffer[counter]);
            String key = scanner.nextLine().trim();
            if(!"".equals(key)) {
                buffer[counter] = key;
            }
            counter++;
        }
        System.out.println("Edytuję użytkownika");
        userEntity.setName(buffer[0]);
        userEntity.setEmail(buffer[1]);
        userEntity.setPassword(buffer[2]);
        USER_DAO.update(userEntity);
        return userEntity;
    }

    private static UserEntity deleteUser(UserEntity userEntity) {
        USER_DAO.delete(userEntity.getId());
        return new UserEntity();

    }


}

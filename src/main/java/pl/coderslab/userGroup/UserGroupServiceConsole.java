package pl.coderslab.userGroup;

import java.util.Optional;
import java.util.Scanner;

public class UserGroupServiceConsole {

    private static final UserGroupDao USER_GROUP_DAO = new UserGroupDao();
    private static final String COMMAND_LINE = "Choose action: N - new group, X - exit, enter group ID for more details.";
    private static final String DETAILS_MENU = "Choose action: N - new group, E - edit, D - delete, X - exit";

    public static void manage() {

        System.out.println("Manage User Groups");
        findAllUserGroups();

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String key = scanner.nextLine().trim().toLowerCase();

                switch (key) {
                    case "n":
                        System.out.println("New Group");
                        newUserGroup(scanner);
                        break;
                    case "x":
                        System.out.println("Exit");
                        return;
                    default:
                        try {
                            int userGroupId = Integer.parseInt(key);
                            Optional<UserGroupEntity> optionalUserGroup = Optional.ofNullable(USER_GROUP_DAO.read(userGroupId));
                            UserGroupEntity userGroupEntity = optionalUserGroup.orElseGet(UserGroupEntity::new);

                            if (userGroupEntity.getId() == 0) {
                                System.out.println("Wrong id or Group does not exist");
                            } else {
                                userGroupDetails(scanner, userGroupEntity);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid id number");
                        }
                }
                findAllUserGroups();
            }
        }

    }

    private static void findAllUserGroups() {

        UserGroupEntity[] userGroupEntities = USER_GROUP_DAO.findAll();

        for (UserGroupEntity userGroupEntity : userGroupEntities) {
            System.out.println("[ " + userGroupEntity.getId() + " ] " + userGroupEntity);
        }

        System.out.println(COMMAND_LINE);
    }

    private static void userGroupDetails(Scanner scanner, UserGroupEntity userGroupEntity) {
        String activeGroup = "Active group: " + userGroupEntity;
        System.out.println(activeGroup);
        System.out.println(DETAILS_MENU);

        while(scanner.hasNextLine()) {
            String key = scanner.nextLine().trim().toLowerCase();

            switch (key) {
                case "n":
                    System.out.println("New Group");
                    newUserGroup(scanner);
                    return;
                case "e":
                    System.out.println("Edit Group");
                    editUserGroup(scanner, userGroupEntity);
                    return;
                case "x":
                    System.out.println("Exit");
                    return;
                case "d":
                    System.out.println("Delete group");
                    deleteUserGroup(scanner, userGroupEntity);
                    return;
            }
            System.out.println(DETAILS_MENU);
        }
    }

    private static void newUserGroup (Scanner scanner) {
        System.out.println("Enter your new group name");
        String key = scanner.nextLine().trim();

        UserGroupEntity userGroupEntity = new UserGroupEntity(key);
        USER_GROUP_DAO.create(userGroupEntity);
    }

    private static void editUserGroup (Scanner scanner, UserGroupEntity userGroupEntity) {
        System.out.println("Enter new name for group or press ENTER to leave it without change");
        System.out.println("Group name: " + userGroupEntity.getName());
        String key = scanner.nextLine().trim();
        if (!"".equals(key)) {
            userGroupEntity.setName(key);
            USER_GROUP_DAO.update(userGroupEntity);
            System.out.println("Group updated: " + userGroupEntity);
        }
    }

    private static void deleteUserGroup (Scanner scanner, UserGroupEntity userGroupEntity) {
        System.out.println("Are you sure you want to delete this Group?");
        System.out.println(userGroupEntity);
        System.out.println("Enter Y or T to confirm, anything else to abort");
        String key = scanner.nextLine().trim().toLowerCase();

        switch (key) {
            case "y":
            case "t":
                USER_GROUP_DAO.delete(userGroupEntity.getId());
                System.out.println("Group deleted");
                break;
            default:
                System.out.println("Group is NOT deleted");
        }
    }
}

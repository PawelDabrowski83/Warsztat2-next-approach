package pl.coderslab.users;

import pl.coderslab.userGroup.UserGroup;

import java.util.Comparator;

public class User implements Comparator<User> {

    private int id;
    private String name;
    private String email;
    private String password;
    private UserGroup userGroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    @Override
    public int compare(User o1, User o2) {
        return o1.email.compareToIgnoreCase(o2.email);
    }
}

package pl.coderslab.users;

import pl.coderslab.userGroup.UserGroup;

import java.util.Comparator;

public class User implements Comparable<User> {

    private int id;
    private String name;
    private String email;
    private String password;
    private UserGroup userGroup;
    private int solutions;

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

    public int getSolutions() {
        return solutions;
    }

    public void setSolutions(int solutions) {
        this.solutions = solutions;
    }

    @Override
    public int compareTo(User o) {
        return this.name.compareToIgnoreCase(o.name);
    }

}

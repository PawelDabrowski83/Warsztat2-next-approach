package pl.coderslab.userGroup;

import java.util.Comparator;

public class UserGroup implements Comparator<UserGroup> {

    private int id;
    private String name;

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

    @Override
    public int compare(UserGroup o1, UserGroup o2) {
        return o1.name.compareTo(o2.name);
    }
}

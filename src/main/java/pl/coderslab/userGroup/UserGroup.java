package pl.coderslab.userGroup;

public class UserGroup implements Comparable<UserGroup> {

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
    public int compareTo(UserGroup o) {
        return this.name.compareTo(o.name);
    }

}

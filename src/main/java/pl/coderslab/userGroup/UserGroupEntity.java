package pl.coderslab.userGroup;

public class UserGroupEntity {

    private int id;
    private String name;

    public UserGroupEntity(String name) {
        this.name = name;
    }

    public UserGroupEntity() {
    }

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
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

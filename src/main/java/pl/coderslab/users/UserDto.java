package pl.coderslab.users;

public class UserDto {

    private int id;
    private String name;
    private String email;
    private String password;
    private int userGroupId;
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

    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    public int getSolutions() {
        return solutions;
    }

    public void setSolutions(int solutions) {
        this.solutions = solutions;
    }
}

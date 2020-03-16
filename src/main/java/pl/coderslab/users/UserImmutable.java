package pl.coderslab.users;

public class UserImmutable {

    private final Long id;
    private final String name;
    private final String email;
    private final String password;

    private UserImmutable(Builder builder) {
        id = builder.id;
        name = builder.name;
        email = builder.email;
        password = builder.password;
    }

    public static class Builder {

        private final Long id;
        private final String name;
        private final String email;
        private final String password;

        public Builder(Long id, String name, String email, String password) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
        }

        public UserImmutable build() {
            return new UserImmutable(this);
        }
    }


}

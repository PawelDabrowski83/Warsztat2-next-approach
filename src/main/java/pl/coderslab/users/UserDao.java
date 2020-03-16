package pl.coderslab.users;

import pl.coderslab.commons.DbUtilOld;

import java.sql.*;
import java.util.Arrays;

public class UserDao {

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM users where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET name = ?, email = ?, password = ? where id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY =
            "SELECT * FROM users";
    private static final String FIND_ALL_USERS_BY_USERGROUPID =
            "SELECT * FROM users WHERE usergroup_id = ? order by id";



    public UserEntity create(UserEntity userEntity) {
        try (Connection conn = DbUtilOld.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userEntity.getName());
            statement.setString(2, userEntity.getEmail());
            statement.setString(3, userEntity.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                userEntity.setId(resultSet.getInt(1));
            }
            return userEntity;
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            System.out.println("Zduplikowane wartości unikalne");
            return new UserEntity();
        } catch (SQLException e) {
            e.printStackTrace();
            return new UserEntity();
        }
    }



    public UserEntity read(int userId) {
        try (Connection conn = DbUtilOld.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(resultSet.getInt("id"));
                userEntity.setName(resultSet.getString("name"));
                userEntity.setEmail(resultSet.getString("email"));
                userEntity.setPassword(resultSet.getString("password"));
                return userEntity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(UserEntity userEntity) {
        try (Connection conn = DbUtilOld.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, userEntity.getName());
            statement.setString(2, userEntity.getEmail());
            statement.setString(3, userEntity.getPassword());
            statement.setInt(4, userEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DbUtilOld.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private UserEntity[] addToArray(UserEntity u, UserEntity[] userEntities) {
        UserEntity[] tmpUserEntities = Arrays.copyOf(userEntities, userEntities.length + 1);
        tmpUserEntities[userEntities.length] = u;
        return tmpUserEntities;
    }

    public UserEntity[] findAll() {
        try (Connection conn = DbUtilOld.getConnection()) {
            UserEntity[] userEntities = new UserEntity[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userEntities = addToArray(getUserFromResultSet(resultSet), userEntities);
            }
            return userEntities;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

    private UserEntity getUserFromResultSet(ResultSet resultSet) throws SQLException {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(resultSet.getInt("id"));
        userEntity.setName(resultSet.getString("name"));
        userEntity.setEmail(resultSet.getString("email"));
        userEntity.setPassword(resultSet.getString("password"));
        return userEntity;
    }

    public UserEntity[] findAllUserByUserGroupId(int userGroupId) {
        try (Connection connection = DbUtilOld.getConnection()) {
            UserEntity[] userEntities = new UserEntity[0];
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_BY_USERGROUPID);
            statement.setInt(1, userGroupId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userEntities = addToArray(getUserFromResultSet(resultSet), userEntities);
            }
            return userEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return new UserEntity[0];
        }

    }






}

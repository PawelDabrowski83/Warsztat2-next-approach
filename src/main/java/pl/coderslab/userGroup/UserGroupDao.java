package pl.coderslab.userGroup;

import pl.coderslab.DbUtil;
import pl.coderslab.DbUtilOld;

import java.sql.*;
import java.util.Arrays;

public class UserGroupDao {

        private static final String CREATE_USERGROUP_QUERY =
                "INSERT INTO user_group(name) VALUE (?)";
        private static final String READ_USERGROUP_QUERY =
                "SELECT * FROM user_group where id = ?";
        private static final String UPDATE_USERGROUP_QUERY =
                "UPDATE user_group SET name = ? where id = ?";
        private static final String DELETE_USERGROUP_QUERY =
                "DELETE FROM user_group WHERE id = ?";
        private static final String FIND_ALL_USERGROUPS_QUERY =
                "SELECT * FROM user_group order by id";



        public UserGroup create(UserGroup userGroup) {
            try (Connection conn = DbUtilOld.getConnection()) {
                PreparedStatement statement =
                        conn.prepareStatement(CREATE_USERGROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, userGroup.getName());
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    userGroup.setId(resultSet.getInt(1));
                }
                return userGroup;
            } catch (SQLException e) {
                e.printStackTrace();
                return new UserGroup();
            }
        }

        public UserGroup read(int userGroupId) {
            try (Connection conn = DbUtilOld.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(READ_USERGROUP_QUERY);
                statement.setInt(1, userGroupId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    UserGroup userGroup = new UserGroup();
                    userGroup.setId(resultSet.getInt("id"));
                    userGroup.setName(resultSet.getString("name"));
                    return userGroup;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new UserGroup();
        }

        public void update(UserGroup userGroup) {
            try (Connection conn = DbUtilOld.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(UPDATE_USERGROUP_QUERY);
                statement.setString(1, userGroup.getName());
                statement.setInt(2, userGroup.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void delete(int userGroupId) {
            try (Connection conn = DbUtilOld.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(DELETE_USERGROUP_QUERY);
                statement.setInt(1, userGroupId);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private UserGroup[] addToArray(UserGroup u, UserGroup[] userGroups) {
            UserGroup[] tmpUserGroups = Arrays.copyOf(userGroups, userGroups.length + 1);
            tmpUserGroups[userGroups.length] = u;
            return tmpUserGroups;
        }

        public UserGroup[] findAll() {
            try (Connection conn = DbUtilOld.getConnection()) {
                UserGroup[] userGroups = new UserGroup[0];
                PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERGROUPS_QUERY);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    UserGroup userGroup = new UserGroup();
                    userGroup.setId(resultSet.getInt("id"));
                    userGroup.setName(resultSet.getString("name"));
                    userGroups = addToArray(userGroup, userGroups);
                }
                return userGroups;
            } catch (SQLException e) {
                e.printStackTrace();
                return new UserGroup[0];
            }}
}

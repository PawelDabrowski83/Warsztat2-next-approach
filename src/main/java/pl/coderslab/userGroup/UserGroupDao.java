package pl.coderslab.userGroup;

import pl.coderslab.commons.DbUtilOld;

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



        public UserGroupEntity create(UserGroupEntity userGroupEntity) {
            try (Connection conn = DbUtilOld.getConnection()) {
                PreparedStatement statement =
                        conn.prepareStatement(CREATE_USERGROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, userGroupEntity.getName());
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    userGroupEntity.setId(resultSet.getInt(1));
                }
                return userGroupEntity;
            } catch (SQLException e) {
                e.printStackTrace();
                return new UserGroupEntity();
            }
        }

        public UserGroupEntity read(int userGroupId) {
            try (Connection conn = DbUtilOld.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(READ_USERGROUP_QUERY);
                statement.setInt(1, userGroupId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    UserGroupEntity userGroupEntity = new UserGroupEntity();
                    userGroupEntity.setId(resultSet.getInt("id"));
                    userGroupEntity.setName(resultSet.getString("name"));
                    return userGroupEntity;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new UserGroupEntity();
        }

        public void update(UserGroupEntity userGroupEntity) {
            try (Connection conn = DbUtilOld.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(UPDATE_USERGROUP_QUERY);
                statement.setString(1, userGroupEntity.getName());
                statement.setInt(2, userGroupEntity.getId());
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

        private UserGroupEntity[] addToArray(UserGroupEntity u, UserGroupEntity[] userGroupEntities) {
            UserGroupEntity[] tmpUserGroupEntities = Arrays.copyOf(userGroupEntities, userGroupEntities.length + 1);
            tmpUserGroupEntities[userGroupEntities.length] = u;
            return tmpUserGroupEntities;
        }

        public UserGroupEntity[] findAll() {
            try (Connection conn = DbUtilOld.getConnection()) {
                UserGroupEntity[] userGroupEntities = new UserGroupEntity[0];
                PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERGROUPS_QUERY);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    UserGroupEntity userGroupEntity = new UserGroupEntity();
                    userGroupEntity.setId(resultSet.getInt("id"));
                    userGroupEntity.setName(resultSet.getString("name"));
                    userGroupEntities = addToArray(userGroupEntity, userGroupEntities);
                }
                return userGroupEntities;
            } catch (SQLException e) {
                e.printStackTrace();
                return new UserGroupEntity[0];
            }}
}

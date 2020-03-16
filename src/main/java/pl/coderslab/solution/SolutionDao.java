package pl.coderslab.solution;

import pl.coderslab.commons.DbUtil;
import pl.coderslab.commons.DbUtilOld;

import java.sql.*;
import java.util.Arrays;

public class SolutionDao {

        private static final String CREATE_SOLUTION_QUERY =
                "INSERT INTO solution(create_date, description, exercise_id, users_id) VALUES (?, ?, ?, ?)";
        private static final String READ_SOLUTION_QUERY =
                "SELECT * FROM solution where id = ?";
        private static final String UPDATE_SOLUTION_QUERY =
                "UPDATE solution SET create_date = ?, update_date = ?, description = ?, exercise_id = ?, users_id = ? where id = ?";
        private static final String DELETE_SOLUTION_QUERY =
                "DELETE FROM solution WHERE id = ?";
        private static final String FIND_ALL_SOLUTIONS_QUERY =
                "SELECT * FROM solution";
        private static final String FIND_SOLUTIONS_BY_USERID =
                "SELECT * from solution where users_id = ?";
        private static final String FIND_ALL_BY_EXERCISEID =
                "SELECT * from solution WHERE exercise_id = ? order by id";
        private static final String FIND_RECENT_SOLUTIONS =
                "SELECT * FROM solution ORDER BY create_date DESC LIMIT ?";
        private static final String COUNT_SOLUTIONS_BY_USERID =
                "SELECT count(*) AS Number from solution where users_id = ?";



        public SolutionEntity create(SolutionEntity solutionEntity) {
            try (Connection conn = DbUtilOld.getConnection()) {
                PreparedStatement statement =
                        conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, solutionEntity.getCreated().toString());
                statement.setString(2, solutionEntity.getDescription());
                statement.setInt(3, solutionEntity.getExerciseId());
                statement.setInt(4, solutionEntity.getUsersId());
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    solutionEntity.setId(resultSet.getInt((1)));
                }
                return solutionEntity;
            } catch (SQLException e) {
                e.printStackTrace();
                return new SolutionEntity();
            }
        }

        public SolutionEntity read(int solutionId) {
            try (Connection conn = DbUtilOld.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
                statement.setInt(1, solutionId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return getSolutionFromResultSet(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Perfectly fine");
            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("Nullpointer");
            }
            return new SolutionEntity();
        }

        public void update(SolutionEntity solutionEntity) {
            try (Connection conn = DbUtilOld.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
                statement.setString(1, solutionEntity.getCreated().toString());
                statement.setString(2, solutionEntity.getUpdated().toString());
                statement.setString(3, solutionEntity.getDescription());
                statement.setInt(4, solutionEntity.getExerciseId());
                statement.setInt(5, solutionEntity.getUsersId());
                statement.setInt(6, solutionEntity.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void delete(int solutionId) {
            try (Connection conn = DbUtilOld.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
                statement.setInt(1, solutionId);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private SolutionEntity[] addToArray(SolutionEntity s, SolutionEntity[] solutionEntities) {
            SolutionEntity[] tmpSolutionEntities = Arrays.copyOf(solutionEntities, solutionEntities.length + 1);
            tmpSolutionEntities[solutionEntities.length] = s;
            return tmpSolutionEntities;
        }

        public SolutionEntity[] findAll() {
            try (Connection conn = DbUtilOld.getConnection()) {
                SolutionEntity[] solutionEntities = new SolutionEntity[0];
                PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    solutionEntities = addToArray(getSolutionFromResultSet(resultSet), solutionEntities);
                }
                return solutionEntities;
            } catch (SQLException e) {
                e.printStackTrace();
                return new SolutionEntity[0];
            }
        }

        private SolutionEntity getSolutionFromResultSet (ResultSet resultSet) throws SQLException {
            SolutionEntity solutionEntity = new SolutionEntity();
            solutionEntity.setId(resultSet.getInt(1));
            try {
                solutionEntity.setCreated(resultSet.getTimestamp("create_date").toLocalDateTime());
            } catch (NullPointerException e) {
                solutionEntity.setCreated(null);
            }
            try {
                solutionEntity.setUpdated(resultSet.getTimestamp("update_date").toLocalDateTime());
            } catch (NullPointerException e) {
                solutionEntity.setUpdated(null);
            }
            solutionEntity.setDescription(resultSet.getString("description"));
            solutionEntity.setExerciseId(resultSet.getInt("exercise_id"));
            solutionEntity.setUsersId(resultSet.getInt("users_id"));
            return solutionEntity;
        }

        public SolutionEntity[] findAllByUserId(int userId) {
            try (Connection connection = DbUtilOld.getConnection()) {
                SolutionEntity[] solutionEntities = new SolutionEntity[0];
                PreparedStatement statement = connection.prepareStatement(FIND_SOLUTIONS_BY_USERID);
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    solutionEntities = addToArray(getSolutionFromResultSet(resultSet), solutionEntities);
                }
                return solutionEntities;
            } catch (SQLException e) {
                e.printStackTrace();
                return new SolutionEntity[0];
            }
        }

        public int countByUserId (int userId) {
//            try (Connection connection = DbUtilOld.getConnection()) {
//                PreparedStatement statement = connection.prepareStatement(COUNT_SOLUTIONS_BY_USERID);
//                statement.setInt(1, userId);
//                ResultSet resultSet = statement.executeQuery();
//                return (int) resultSet.getInt(1);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return -1;
//            }
            SolutionEntity[] entities = findAllByUserId(userId);
            return entities.length;
        }

        public SolutionEntity[] findAllByExerciseId(int exerciseId) {
            try (Connection connection = DbUtilOld.getConnection()) {
                SolutionEntity[] solutionEntities = new SolutionEntity[0];
                PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_EXERCISEID);
                statement.setInt(1, exerciseId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    solutionEntities = addToArray(getSolutionFromResultSet(resultSet), solutionEntities);
                }
                return solutionEntities;
            } catch (SQLException e) {
                e.printStackTrace();
                return new SolutionEntity[0];
            }
        }

        public SolutionEntity[] findRecentSolution(int limit) {
            try (Connection connection = DbUtilOld.getConnection()) {
                SolutionEntity[] solutionEntities = new SolutionEntity[0];
                PreparedStatement statement = connection.prepareStatement(FIND_RECENT_SOLUTIONS);
                statement.setInt(1, limit);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    solutionEntities = addToArray(getSolutionFromResultSet(resultSet), solutionEntities);
                }
                return solutionEntities;
            } catch (SQLException e) {
                e.printStackTrace();
                return new SolutionEntity[0];
            }
        }

}

package pl.coderslab.solution;

import pl.coderslab.DbUtil;

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



        public Solution create(Solution solution) {
            try (Connection conn = DbUtil.getConnection()) {
                PreparedStatement statement =
                        conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, solution.getCreated().toString());
                statement.setString(2, solution.getDescription());
                statement.setInt(3, solution.getExerciseId());
                statement.setInt(4, solution.getUsersId());
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    solution.setId(resultSet.getInt((1)));
                }
                return solution;
            } catch (SQLException e) {
                e.printStackTrace();
                return new Solution();
            }
        }

        public Solution read(int solutionId) {
            try (Connection conn = DbUtil.getConnection()) {
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
            return new Solution();
        }

        public void update(Solution solution) {
            try (Connection conn = DbUtil.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
                statement.setString(1, solution.getCreated().toString());
                statement.setString(2, solution.getUpdated().toString());
                statement.setString(3, solution.getDescription());
                statement.setInt(4, solution.getExerciseId());
                statement.setInt(5, solution.getUsersId());
                statement.setInt(6, solution.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void delete(int solutionId) {
            try (Connection conn = DbUtil.getConnection()) {
                PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
                statement.setInt(1, solutionId);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private Solution[] addToArray(Solution s, Solution[] solutions) {
            Solution[] tmpSolutions = Arrays.copyOf(solutions, solutions.length + 1);
            tmpSolutions[solutions.length] = s;
            return tmpSolutions;
        }

        public Solution[] findAll() {
            try (Connection conn = DbUtil.getConnection()) {
                Solution[] solutions = new Solution[0];
                PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    solutions = addToArray(getSolutionFromResultSet(resultSet), solutions);
                }
                return solutions;
            } catch (SQLException e) {
                e.printStackTrace();
                return new Solution[0];
            }
        }

        private Solution getSolutionFromResultSet (ResultSet resultSet) throws SQLException {
            Solution solution = new Solution();
            solution.setId(resultSet.getInt(1));
            solution.setCreated(resultSet.getTimestamp("create_date").toLocalDateTime());
            try {
                solution.setUpdated(resultSet.getTimestamp("update_date").toLocalDateTime());
            } catch (NullPointerException e) {
                solution.setUpdated(null);
            }
            solution.setDescription(resultSet.getString("description"));
            solution.setExerciseId(resultSet.getInt("exercise_id"));
            solution.setUsersId(resultSet.getInt("users_id"));
            return solution;
        }

        public Solution[] findAllByUserId(int userId) {
            try (Connection connection = DbUtil.getConnection()) {
                Solution[] solutions = new Solution[0];
                PreparedStatement statement = connection.prepareStatement(FIND_SOLUTIONS_BY_USERID);
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    solutions = addToArray(getSolutionFromResultSet(resultSet), solutions);
                }
                return solutions;
            } catch (SQLException e) {
                e.printStackTrace();
                return new Solution[0];
            }
        }

        public Solution[] findAllByExerciseId(int exerciseId) {
            try (Connection connection = DbUtil.getConnection()) {
                Solution[] solutions = new Solution[0];
                PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_EXERCISEID);
                statement.setInt(1, exerciseId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    solutions = addToArray(getSolutionFromResultSet(resultSet), solutions);
                }
                return solutions;
            } catch (SQLException e) {
                e.printStackTrace();
                return new Solution[0];
            }
        }

}

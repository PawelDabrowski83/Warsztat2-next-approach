package pl.coderslab.exercise;

import pl.coderslab.commons.DbUtilOld;
import java.sql.*;
import java.util.Arrays;

public class ExerciseDao {


    private static final String CREATE_EXERCISE_QUERY =
            "INSERT INTO exercise(title, description) VALUES (?, ?)";
    private static final String READ_EXERCISE_QUERY =
            "SELECT * FROM exercise where id = ?";
    private static final String UPDATE_EXERCISE_QUERY =
            "UPDATE exercise SET title = ?, description = ? where id = ?";
    private static final String DELETE_EXERCISE_QUERY =
            "DELETE FROM exercise WHERE id = ?";
    private static final String FIND_ALL_EXERCISES_QUERY =
            "SELECT * FROM exercise order by id";


    public ExerciseEntity create(ExerciseEntity exerciseEntity) {
        try (Connection conn = DbUtilOld.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, exerciseEntity.getTitle());
            statement.setString(2, exerciseEntity.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                exerciseEntity.setId(resultSet.getInt(1));
            }
            return exerciseEntity;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ExerciseEntity();
        }
    }


    public ExerciseEntity read(int exerciseId) {
        try (Connection conn = DbUtilOld.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_EXERCISE_QUERY);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ExerciseEntity exerciseEntity = new ExerciseEntity();
                exerciseEntity.setId(resultSet.getInt("id"));
                exerciseEntity.setTitle(resultSet.getString("title"));
                exerciseEntity.setDescription(resultSet.getString("description"));
                return exerciseEntity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ExerciseEntity();
    }

    public void update(ExerciseEntity exerciseEntity) {
        try (Connection conn = DbUtilOld.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_EXERCISE_QUERY);
            statement.setString(1, exerciseEntity.getTitle());
            statement.setString(2, exerciseEntity.getDescription());
            statement.setInt(3, exerciseEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int exerciseId) {
        try (Connection conn = DbUtilOld.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_EXERCISE_QUERY);
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ExerciseEntity[] addToArray(ExerciseEntity e, ExerciseEntity[] exerciseEntities) {
        ExerciseEntity[] tmpExerciseEntities = Arrays.copyOf(exerciseEntities, exerciseEntities.length + 1);
        tmpExerciseEntities[exerciseEntities.length] = e;
        return tmpExerciseEntities;
    }

    public ExerciseEntity[] findAll() {
        try (Connection conn = DbUtilOld.getConnection()) {
            ExerciseEntity[] exerciseEntities = new ExerciseEntity[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISES_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ExerciseEntity exerciseEntity = new ExerciseEntity();
                exerciseEntity.setId(resultSet.getInt("id"));
                exerciseEntity.setTitle(resultSet.getString("title"));
                exerciseEntity.setDescription(resultSet.getString("description"));
                exerciseEntities = addToArray(exerciseEntity, exerciseEntities);
            }
            return exerciseEntities;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ExerciseEntity[0];
        }
    }
}

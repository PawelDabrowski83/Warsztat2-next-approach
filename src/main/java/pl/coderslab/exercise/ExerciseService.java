package pl.coderslab.exercise;

import pl.coderslab.exercise.ExerciseDao;
import pl.coderslab.exercise.ExerciseDto;
import pl.coderslab.exercise.ExerciseEntity;
import pl.coderslab.exercise.ExerciseMapper;

import java.util.Arrays;

public class ExerciseService {

    private final static ExerciseDao EXERCISE_DAO = new ExerciseDao();

    public static ExerciseDto[] findAll() {
        ExerciseEntity[] entities = EXERCISE_DAO.findAll();
        return Arrays.stream(entities)
                .map(ExerciseMapper::mapEntityToExercise)
                .map(ExerciseMapper::mapExerciseToDto)
                .toArray(ExerciseDto[]::new);
    }

    public static void createExercise (ExerciseDto dto) {
        EXERCISE_DAO.create(
                ExerciseMapper.mapExerciseToEntity(
                        ExerciseMapper.mapDtoToExercise(
                                dto)));
    }

    public static ExerciseDto readExercise (int exerciseId) {
        return ExerciseMapper.mapExerciseToDto(
                ExerciseMapper.mapEntityToExercise(
                        EXERCISE_DAO.read(
                                exerciseId)));
    }

    public static void editExercise (ExerciseDto dto) {
        EXERCISE_DAO.update(
                ExerciseMapper.mapExerciseToEntity(
                        ExerciseMapper.mapDtoToExercise(
                                dto)));
    }

    public static void deleteExercise (int exerciseId) {
        EXERCISE_DAO.delete(exerciseId);
    }
}

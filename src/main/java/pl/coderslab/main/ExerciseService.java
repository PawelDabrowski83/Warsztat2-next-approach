package pl.coderslab.main;

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
}

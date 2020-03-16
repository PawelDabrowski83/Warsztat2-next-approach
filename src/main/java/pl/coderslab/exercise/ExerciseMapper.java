package pl.coderslab.exercise;

public class ExerciseMapper {

    public static Exercise mapDtoToExercise (ExerciseDto dto) {
        Exercise exercise = new Exercise();
        exercise.setId(dto.getId());
        exercise.setTitle(dto.getTitle());
        exercise.setDescription(dto.getDescription());
        return exercise;
    }

    public static ExerciseDto mapExerciseToDto (Exercise exercise) {
        ExerciseDto dto = new ExerciseDto();
        dto.setId(exercise.getId());
        dto.setTitle(exercise.getTitle());
        dto.setDescription(exercise.getDescription());
        return dto;
    }

    public static Exercise mapEntityToExercise (ExerciseEntity entity) {
        Exercise exercise = new Exercise();
        exercise.setId(entity.getId());
        exercise.setTitle(entity.getTitle());
        exercise.setDescription(entity.getDescription());
        return exercise;
    }

    public static ExerciseEntity mapExerciseToEntity (Exercise exercise) {
        ExerciseEntity entity = new ExerciseEntity();
        entity.setId(exercise.getId());
        entity.setTitle(exercise.getTitle());
        entity.setDescription(exercise.getDescription());
        return entity;
    }
}

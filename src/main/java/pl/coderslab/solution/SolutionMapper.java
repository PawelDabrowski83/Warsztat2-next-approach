package pl.coderslab.solution;

import pl.coderslab.exercise.ExerciseDao;
import pl.coderslab.exercise.ExerciseMapper;
import pl.coderslab.users.UserDao;
import pl.coderslab.users.UserMapper;

public class SolutionMapper {

    private final static UserDao USER_DAO = new UserDao();
    private final static UserMapper USER_MAPPER = new UserMapper();
    private final static ExerciseDao EXERCISE_DAO = new ExerciseDao();
    private final static ExerciseMapper EXERCISE_MAPPER = new ExerciseMapper();

    public Solution mapDtoToSolution (SolutionDto dto) {
        Solution solution = new Solution();
        solution.setId(dto.getId());
        solution.setCreated(dto.getCreated());
        solution.setUpdated(dto.getUpdated());
        solution.setDescription(dto.getDescription());
        solution.setUser(
                USER_MAPPER.mapEntityToUser(
                        USER_DAO.read(
                                dto.getUsersId())));
        solution.setExercise(
                EXERCISE_MAPPER.mapEntityToExercise(
                        EXERCISE_DAO.read(
                                dto.getExerciseId())));
        return solution;
    }

    public SolutionDto mapSolutionToDto (Solution solution) {
        SolutionDto dto = new SolutionDto();
        dto.setId(solution.getId());
        dto.setCreated(solution.getCreated());
        dto.setUpdated(solution.getUpdated());
        dto.setDescription(solution.getDescription());
        dto.setUsersId(solution.getUser().getId());
        dto.setExerciseId(solution.getExercise().getId());
        return dto;
    }

    public Solution mapEntityToSolution (SolutionEntity entity) {
        Solution solution = new Solution();
        solution.setId(entity.getId());
        solution.setCreated(entity.getCreated());
        solution.setUpdated(entity.getUpdated());
        solution.setDescription(entity.getDescription());
        solution.setUser(
                USER_MAPPER.mapEntityToUser(
                        USER_DAO.read(
                                entity.getUsersId())));
        solution.setExercise(
                EXERCISE_MAPPER.mapEntityToExercise(
                        EXERCISE_DAO.read(
                                entity.getExerciseId())));
        return solution;
    }

    public SolutionEntity mapSolutionToEntity (Solution solution) {
        SolutionEntity entity = new SolutionEntity();
        entity.setId(solution.getId());
        entity.setCreated(solution.getCreated());
        entity.setUpdated(solution.getUpdated());
        entity.setDescription(solution.getDescription());
        entity.setUsersId(solution.getUser().getId());
        entity.setExerciseId(solution.getExercise().getId());
        return entity;
    }
}

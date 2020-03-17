package pl.coderslab.solution;

import pl.coderslab.solution.SolutionDao;
import pl.coderslab.solution.SolutionDto;
import pl.coderslab.solution.SolutionEntity;
import pl.coderslab.solution.SolutionMapper;

import java.util.Arrays;
import java.util.Optional;

public class SolutionService {

    private final static SolutionDao SOLUTION_DAO = new SolutionDao();

    public static SolutionDto getSolutionById(int id) {
        Optional<SolutionEntity> optionalSolutionEntity = Optional.ofNullable(SOLUTION_DAO.read(id));
        SolutionEntity entity = optionalSolutionEntity.orElseGet(SolutionEntity::new);
        return SolutionMapper.mapSolutionToDto(SolutionMapper.mapEntityToSolution(entity));
    }

    public static SolutionDto[] findSolutionsByUserId (int id) {
        SolutionEntity[] entities = SOLUTION_DAO.findAllByUserId(id);
        return SolutionMapper.mapSolutionToDtoArray(
                SolutionMapper.mapEntityToSolutionArray(
                        entities));
    }

    public static SolutionDto[] findAll() {
        SolutionEntity[] entities = SOLUTION_DAO.findAll();
        return Arrays.stream(entities)
                .map(SolutionMapper::mapEntityToSolution)
                .map(SolutionMapper::mapSolutionToDto)
                .toArray(SolutionDto[]::new);
    }

    public static SolutionDto[] getRecentSolutions(int limit) {
        SolutionEntity[] entities = SOLUTION_DAO.findRecentSolution(limit);
        return SolutionMapper.mapSolutionToDtoArray(SolutionMapper.mapEntityToSolutionArray(entities));
    }

    public static void createSolution (SolutionDto dto) {
        SOLUTION_DAO.create(
                SolutionMapper.mapSolutionToEntity(
                        SolutionMapper.mapDtoToSolution(
                                dto)));
    }

    public static SolutionDto findSolution (int solutionId) {
        return SolutionMapper.mapSolutionToDto(
                SolutionMapper.mapEntityToSolution(
                        SOLUTION_DAO.read(
                                solutionId)));
    }

    public static void editSolution (SolutionDto dto) {
        SOLUTION_DAO.update(
                SolutionMapper.mapSolutionToEntity(
                        SolutionMapper.mapDtoToSolution(
                                dto)));
    }

    public static void deleteSolution (int solutionId) {
        SOLUTION_DAO.delete(solutionId);
    }
}

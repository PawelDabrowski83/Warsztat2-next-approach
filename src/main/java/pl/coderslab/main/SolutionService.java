package pl.coderslab.main;

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
}

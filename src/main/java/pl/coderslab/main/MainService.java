package pl.coderslab.main;

import pl.coderslab.solution.*;

public class MainService {

    private final static SolutionDao SOLUTION_DAO = new SolutionDao();

    public static SolutionDto[] getRecentSolutions(int limit) {
        SolutionEntity[] entities = SOLUTION_DAO.findRecentSolution(limit);
        return SolutionMapper.mapSolutionToDtoArray(SolutionMapper.mapEntityToSolutionArray(entities));
    }
}

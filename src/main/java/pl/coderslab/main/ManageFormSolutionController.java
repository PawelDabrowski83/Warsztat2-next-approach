package pl.coderslab.main;

import pl.coderslab.exercise.ExerciseDto;
import pl.coderslab.exercise.ExerciseService;
import pl.coderslab.solution.SolutionDto;
import pl.coderslab.solution.SolutionService;
import pl.coderslab.users.UserDto;
import pl.coderslab.users.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@WebServlet(name = "ManageFormSolutionController", urlPatterns = "/manageFormSolutions")
public class ManageFormSolutionController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String idAsString = request.getParameter("id");
        SolutionDto dto = new SolutionDto();

        switch (action) {
            case "edit":
                dto = getSolutionByIdAsString(idAsString);
                request.setAttribute("solution", dto);
                request.setAttribute("action", action);
                break;
            case "delete":
                dto = getSolutionByIdAsString(idAsString);
                SolutionService.deleteSolution(dto.getId());
                response.sendRedirect("/manageSolutions");
                return;
            default:
        }

        ExerciseDto[] exerciseDtos = ExerciseService.findAll();
        UserDto[] userDtos = UserService.findAll();

        request.setAttribute("exercises", exerciseDtos);
        request.setAttribute("users", userDtos);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/manageFormSolutions.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String solutionIdAsString = request.getParameter("id");
        String description = request.getParameter("description");
        String exerciseIdAsString = request.getParameter("exerciseId");
        String userIdAsString = request.getParameter("userId");
        String createdAsString = request.getParameter("created");
        String updatedAsString = request.getParameter("updated");
        int exerciseId = -1;
        int userId = -1;
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime updated = LocalDateTime.now();
        try {
            exerciseId = Integer.parseInt(exerciseIdAsString);
            userId = Integer.parseInt(userIdAsString);
        } catch (NumberFormatException e) {
            System.out.println("invalid id with: " + exerciseIdAsString + " and/or " + userIdAsString);
            e.printStackTrace();
        }
        try {
            created = LocalDateTime.parse(createdAsString);
            updated = LocalDateTime.parse(updatedAsString);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            System.out.println("invalid time with: " + createdAsString + " and/or " + updatedAsString);
        }

        SolutionDto dto = new SolutionDto();
        dto.setDescription(description);
        dto.setCreated(created);
        dto.setExerciseId(exerciseId);
        dto.setUsersId(userId);

        if ("edit".equals(action)) {
            dto.setId(getSolutionByIdAsString(solutionIdAsString).getId());
            dto.setUpdated(updated);
            SolutionService.editSolution(dto);
        } else {
            SolutionService.createSolution(dto);
        }

        response.sendRedirect("/manageSolutions");
    }

    private static SolutionDto getSolutionByIdAsString (String idAsString) {
        int id = -1;
        try {
            id = Integer.parseInt(idAsString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Invalid id for: " + idAsString);
        }
        return SolutionService.getSolutionById(id);
    }

}

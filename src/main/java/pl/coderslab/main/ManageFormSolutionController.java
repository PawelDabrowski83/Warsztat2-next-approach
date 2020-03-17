package pl.coderslab.main;

import pl.coderslab.exercise.ExerciseDto;
import pl.coderslab.exercise.ExerciseService;
import pl.coderslab.solution.SolutionDto;
import pl.coderslab.solution.SolutionService;
import pl.coderslab.userGroup.UserGroupDto;
import pl.coderslab.userGroup.UserGroupService;
import pl.coderslab.users.UserDto;
import pl.coderslab.users.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

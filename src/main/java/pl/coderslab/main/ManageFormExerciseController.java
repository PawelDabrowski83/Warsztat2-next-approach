package pl.coderslab.main;

import pl.coderslab.exercise.ExerciseDto;
import pl.coderslab.exercise.ExerciseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ManageFormExerciseController", urlPatterns = "/manageFormExercises")
public class ManageFormExerciseController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String idAsString = request.getParameter("id");
        ExerciseDto dto = new ExerciseDto();

        switch (action) {
            case "edit":
                dto = getExerciseByIdAsString(idAsString);
                request.setAttribute("exercise", dto);
                request.setAttribute("action", action);
                break;
            case "delete":
                ExerciseService.deleteExercise(getExerciseByIdAsString(idAsString).getId());
                response.sendRedirect("/manageExercises");
                return;
            default:
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/manageFormExercises.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idAsString = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String action = request.getParameter("action");
        ExerciseDto dto = new ExerciseDto();
        dto.setTitle(title);
        dto.setDescription(description);

        if ("edit".equals(action)) {
            int id = -1;
            try {
                id = Integer.parseInt(idAsString);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Invalid id with: " + idAsString);
            }
            dto.setId(id);
            ExerciseService.editExercise(dto);
        } else {
            ExerciseService.createExercise(dto);
        }

        response.sendRedirect("/manageExercises");
    }

    private static ExerciseDto getExerciseByIdAsString (String idAsString) {
        int id = -1;
        try {
            id = Integer.parseInt(idAsString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Invalid id with: " + idAsString);
        }
        return ExerciseService.readExercise(id);
    }

}

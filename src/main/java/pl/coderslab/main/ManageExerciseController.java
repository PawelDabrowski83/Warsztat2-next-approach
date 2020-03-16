package pl.coderslab.main;

import pl.coderslab.exercise.ExerciseDto;
import pl.coderslab.exercise.ExerciseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ManageExerciseController", urlPatterns = "/manageExercises")
public class ManageExerciseController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ExerciseDto[] dtos = ExerciseService.findAll();
        request.setAttribute("exercises", dtos);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/manageExercises.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

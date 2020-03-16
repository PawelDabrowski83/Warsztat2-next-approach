package pl.coderslab.main;

import pl.coderslab.users.UserDto;
import pl.coderslab.users.UserService;

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
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/manageFormExercises.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        response.sendRedirect("/manageExercises");
    }

}

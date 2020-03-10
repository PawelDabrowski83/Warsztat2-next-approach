package pl.coderslab.main;

import pl.coderslab.users.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ManageUsersController", urlPatterns = "/manageUsers")
public class ManageUsersController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserDto[] dtos = UsersService.findAll();
        request.setAttribute("users", dtos);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/manageUsers.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

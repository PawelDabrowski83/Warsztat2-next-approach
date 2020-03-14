package pl.coderslab.main;

import pl.coderslab.users.UserDto;
import pl.coderslab.users.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ManageFormUsersController", urlPatterns = "/manageFormUsers")
public class ManageFormUsersController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String param = request.getParameter("action");
        UserDto dto;
        String userIdAsString = request.getParameter("id");

        switch (param) {
            case "new":
                break;
            case "edit":
                dto = getUserById(userIdAsString);
                request.setAttribute("user", dto);
                request.setAttribute("action", "edit");
                break;
            case "delete":
                dto = getUserById(userIdAsString);
                UserService.deleteUser(dto);
                response.sendRedirect("/manageUsers");
                return;
            default:
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/manageFormUsers.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String param = request.getParameter("action");
        UserDto dto = new UserDto();
        dto.setName(name);
        dto.setEmail(email);
        dto.setPassword(password);

        if ("edit".equals(param)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dto.setId(id);
            UserService.editUser(dto);
        } else {
            UserService.createUser(dto);
        }
        response.sendRedirect("/manageUsers");
    }

    private UserDto getUserById (String userIdAsString) {
        try {
            int userId = Integer.parseInt(userIdAsString);
            return UserService.findUserById(userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("pushUserById fail with id: " + userIdAsString);
            return new UserDto();
        }
    }
}

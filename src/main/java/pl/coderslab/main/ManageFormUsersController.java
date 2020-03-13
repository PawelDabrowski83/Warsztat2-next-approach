package pl.coderslab.main;

import pl.coderslab.users.UserDto;
import pl.coderslab.users.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ManageFormUsersController", urlPatterns = "/manageFormUsers")
public class ManageFormUsersController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String param = request.getParameter("action");
        switch (param) {
            case "new":
                break;
            case "edit":
                String userIdAsString = request.getParameter("id");
                UserDto dto = new UserDto();
                try {
                    int userId = Integer.parseInt(userIdAsString);
                    Optional<UserDto> optionalUserDto = Optional.of(UsersService.findUserById(userId));
                    dto = optionalUserDto.orElseGet(UserDto::new);
                } catch (NumberFormatException e) {
                    e.printStackTrace();

                }
                break;
            case "delete":
                break;
            default:
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/manageFormUsers.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDto dto = new UserDto();
        dto.setName(name);
        dto.setEmail(email);
        dto.setPassword(password);
        UsersService.createUser(dto);
        response.sendRedirect("/manageUsers");
    }
}

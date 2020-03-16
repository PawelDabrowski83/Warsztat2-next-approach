package pl.coderslab.userGroup;

import pl.coderslab.users.UserDto;
import pl.coderslab.users.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GroupController", urlPatterns = "/group")
public class GroupController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String paramAsString = request.getParameter("id");
        UserGroupDto dto = new UserGroupDto();
        UserDto[] userDtos = new UserDto[0];
        try {
            int id = Integer.parseInt(paramAsString);
            dto = GroupService.findGroupById(id);
            userDtos = UserService.findUsersByGroupId(id);
        } catch (NumberFormatException e) {
            System.out.println("invalid id");
        }
        request.setAttribute("group", dto);
        request.setAttribute("users", userDtos);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/group.jsp").forward(request, response);

    }
}

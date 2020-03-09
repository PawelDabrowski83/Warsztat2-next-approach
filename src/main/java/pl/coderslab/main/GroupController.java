package pl.coderslab.main;

import pl.coderslab.userGroup.UserGroupDto;

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
        try {
            int id = Integer.parseInt(paramAsString);
            dto = GroupService.findGroupById(id);
        } catch (NumberFormatException e) {
            System.out.println("invalid id");
        }
        request.setAttribute("group", dto);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/groups.jsp").forward(request, response);

    }
}

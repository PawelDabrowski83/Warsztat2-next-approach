package pl.coderslab.main;

import pl.coderslab.userGroup.UserGroupDto;
import pl.coderslab.users.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ManageUserGroupController", urlPatterns = "/manageUserGroups")
public class ManageUserGroupController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserGroupDto[] dtos = UserGroupService.findAll();
        request.setAttribute("userGroups", dtos);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/manageUserGroups.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

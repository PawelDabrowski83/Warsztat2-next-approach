package pl.coderslab.main;

import pl.coderslab.userGroup.UserGroupDto;
import pl.coderslab.userGroup.UserGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ManageFormUserGroupController", urlPatterns = "/manageFormUserGroups")
public class ManageFormUserGroupController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String idAsString = request.getParameter("id");


        switch (action) {
            case "edit":
                UserGroupDto dto = getUserGroupByStringId(idAsString);
                request.setAttribute("userGroup", dto);
                request.setAttribute("action", action);
                break;
            case "delete":
                UserGroupService.deleteUserGroup(getUserGroupByStringId(idAsString));
                response.sendRedirect("/manageUserGroups");
                return;
            default:
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/manageFormUserGroups.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idAsString = request.getParameter("id");
        String name = request.getParameter("name");
        String action = request.getParameter("action");
        UserGroupDto dto = new UserGroupDto();
        dto.setName(name);

        if ("edit".equals(action)) {
            try {
                int id = Integer.parseInt(idAsString);
                dto.setId(id);
                UserGroupService.editUserGroup(dto);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Invalid id format: " + idAsString);
            }
        } else {
            UserGroupService.createUserGroup(dto);
        }

        response.sendRedirect("/manageUserGroups");
    }

    private UserGroupDto getUserGroupByStringId (String idAsString) {
        int id = -1;
        try {
            id = Integer.parseInt(idAsString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("niepoprawny format id" + idAsString);
        }
        return UserGroupService.readUserGroup(id);
    }
}

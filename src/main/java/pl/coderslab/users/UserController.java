package pl.coderslab.users;

import pl.coderslab.solution.SolutionService;
import pl.coderslab.solution.SolutionDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserController", urlPatterns = "/user")
public class UserController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String param = request.getParameter("id");
        UserDto dto = new UserDto();
        SolutionDto[] solutionDtos = new SolutionDto[0];

        try {
            int paramInt = Integer.parseInt(param);
            dto = UserService.findUserById(paramInt);
            solutionDtos = SolutionService.findSolutionsByUserId(paramInt);
        } catch (NumberFormatException e) {
            System.out.println("invalid id");
        }

        request.setAttribute("user", dto);
        request.setAttribute("solutions", solutionDtos);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
}

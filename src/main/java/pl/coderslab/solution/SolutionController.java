package pl.coderslab.solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SolutionController", urlPatterns = "/solution")
public class SolutionController extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            String param = request.getParameter("id");
            SolutionDto dto = new SolutionDto();

            try {
                int paramInt = Integer.parseInt(param);
                dto = SolutionService.getSolutionById(paramInt);
            } catch (NumberFormatException e) {
                System.out.println("invalid id");
            }

            request.setAttribute("solution", dto);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/solution.jsp").forward(request, response);

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);

        }

}


package pl.coderslab.main;

import pl.coderslab.solution.SolutionDto;
import pl.coderslab.solution.SolutionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ManageSolutionController", urlPatterns = "/manageSolutions")
public class ManageSolutionsController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        SolutionDto[] dtos = SolutionService.findAll();
        request.setAttribute("solutions", dtos);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/manageSolutions.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

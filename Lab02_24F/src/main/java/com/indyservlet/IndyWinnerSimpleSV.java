package com.indyservlet;

import com.indyDAO.ConcreteIndyWinnerDAO;
import com.indyDTO.IndyWinnerDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet that handles displaying Indi 500 winners in an HTML table
 * with pagination functionality.
 */
@WebServlet(urlPatterns = {"/IndyWinnerSimpleSV"})
public class IndyWinnerSimpleSV extends HttpServlet {

    private final ConcreteIndyWinnerDAO dao = new ConcreteIndyWinnerDAO();

    /**
     * Handles GET requests to display the winners table with pagination.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException If a servlet error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        int page = 1; // Default to page 1
        int limit = 10; // Number of entries per page

        // Parse the "page" parameter if provided
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
                if (page < 1) {
                    page = 1;
                }
            } catch (NumberFormatException e) {
                page = 1; // Reset to page 1 if the parameter is invalid
            }
        }

        int offset = (page - 1) * limit;

        // Fetch the list of winners for the current page
        List<IndyWinnerDTO> winners = dao.getAllWinners(offset, limit);

        // Build the HTML response
        StringBuilder buffer = new StringBuilder();
        buffer.append("<html><body>");
        buffer.append("<title>Indianapolis 500 Winners</title>");
        buffer.append("<center><h1>Indianapolis 500 Winners</h1>");

        // Generate table only if there are winners
        if (!winners.isEmpty()) {
            buffer.append("<table border='1'><tr><th>Year</th><th>Driver</th><th>Average Speed</th><th>Country</th></tr>");
            for (IndyWinnerDTO winner : winners) {
                buffer.append("<tr><td>").append(winner.getYear()).append("</td><td>")
                      .append(winner.getDriver()).append("</td><td>")
                      .append(winner.getAverageSpeed()).append("</td><td>")
                      .append(winner.getCountry()).append("</td></tr>");
            }
            buffer.append("</table>");
        } else {
            buffer.append("<p>No winners available.</p>");
        }

        // Add pagination links
        buffer.append("<div>");
        if (page > 1) {
            buffer.append("<a href='/Lab02_24F/IndyWinnerSimpleSV?page=").append(page - 1).append("'>Previous</a> ");
        }
        if (page < 12) {
        	buffer.append("<a href='/Lab02_24F/IndyWinnerSimpleSV?page=").append(page + 1).append("'>Next</a>");
        }
        
        buffer.append("</div>");

        buffer.append("</body></html>");

        // Write the response
        response.getWriter().write(buffer.toString());
    }
}

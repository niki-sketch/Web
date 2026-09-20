package lab.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MedicineList")
public class MedicineList extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public MedicineList() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String lang = request.getParameter("lang");
        Locale locale = "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : new Locale("ru", "RU");
        ResourceBundle res = ResourceBundle.getBundle("Medicine", locale);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out.println("<html>");
            out.println("<head><title>" + res.getString("title") + "</title></head>");
            out.println("<body>");
            out.println("<h1>" + res.getString("title") + "</h1>");
            out.println("<table border='1'>");
            out.println("<tr><td><b>" + res.getString("name") + "</b></td>"
                    + "<td><b>" + res.getString("form") + "</b></td>"
                    + "<td><b>" + res.getString("available") + "</b></td></tr>");
            out.println("<tr><td>Парацетамол</td><td>Таблетки 500мг</td><td>Да</td></tr>");
            out.println("<tr><td>Ибупрофен</td><td>Таблетки 400мг</td><td>Да</td></tr>");
            out.println("<tr><td>Амоксициллин</td><td>Капсулы 250мг</td><td>Нет</td></tr>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
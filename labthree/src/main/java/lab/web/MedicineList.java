package lab.web;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MedicineList.
 * Выводит список лекарств из аптеки, название которой передаётся
 * в динамическом параметре запроса "pharmacy".
 */
@WebServlet("/MedicineList")
public class MedicineList extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public MedicineList() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String pharmacy = request.getParameter("pharmacy");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            out.println("<html>");
            out.println("<head><title>Список лекарств</title></head>");
            out.println("<body>");
            out.println("<h1>Список лекарств аптеки " + (pharmacy != null ? pharmacy : "не указана") + "</h1>");
            out.println("<table border='1'>");
            out.println("<tr><td><b>Название</b></td><td><b>Форма выпуска</b></td><td><b>В наличии</b></td></tr>");
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
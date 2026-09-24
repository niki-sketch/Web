package lab.web;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Модульный тест для сервлета MedicineList с использованием
 * фреймворка Mockito. Вместо реального Tomcat используются
 * mock-объекты HttpServletRequest и HttpServletResponse.
 */
public class MedicineListTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    /**
     * Инициализация mock-объектов перед каждым тестом.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Проверяет, что при передаче параметра "pharmacy" сервлет
     * корректно формирует HTML-страницу со списком лекарств,
     * включающую название аптеки и все три лекарства.
     */
    @Test
    public void testDoGet_withPharmacyParameter() throws Exception {
        when(request.getParameter("pharmacy")).thenReturn("Ригла");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);

        MedicineList servlet = new MedicineList();
        servlet.doGet(request, response);
        pw.flush();

        String result = sw.getBuffer().toString();

        assertTrue(result.contains("Ригла"));
        assertTrue(result.contains("Парацетамол"));
        assertTrue(result.contains("Ибупрофен"));
        assertTrue(result.contains("Амоксициллин"));

        verify(request, times(1)).getParameter("pharmacy");
        verify(response).setContentType("text/html;charset=UTF-8");
    }

    /**
     * Проверяет поведение сервлета, когда параметр "pharmacy"
     * не передан — заголовок должен содержать текст
     * "без имени" вместо конкретного названия аптеки.
     */
    @Test
    public void testDoGet_withoutPharmacyParameter() throws Exception {
        when(request.getParameter("pharmacy")).thenReturn(null);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);

        MedicineList servlet = new MedicineList();
        servlet.doGet(request, response);
        pw.flush();

        String result = sw.getBuffer().toString();

        assertTrue(result.contains("не указана"));
        verify(request, times(1)).getParameter("pharmacy");
    }

    /**
     * Проверяет, что doPost вызывает ту же логику, что и doGet
     * (оба метода делегируют работу в processRequest).
     */
    @Test
    public void testDoPost_callsProcessRequest() throws Exception {
        when(request.getParameter("pharmacy")).thenReturn("36.6");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);

        MedicineList servlet = new MedicineList();
        servlet.doPost(request, response);
        pw.flush();

        String result = sw.getBuffer().toString();
        assertTrue(result.contains("36.6"));
    }
}
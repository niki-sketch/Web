package com.labs.server;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.labs.client.MedicineService;
import com.labs.shared.Medicine;

/**
 * Серверная реализация web-сервиса MedicineService.
 * Наследует RemoteServiceServlet, который обеспечивает
 * десериализацию входящих запросов и сериализацию ответов клиенту.
 */
public class MedicineServiceImpl extends RemoteServiceServlet implements MedicineService {

    private static final long serialVersionUID = 1L;

    /**
     * Возвращает список лекарств для указанной аптеки.
     * В данном учебном примере список одинаков для любой аптеки,
     * но название аптеки обязательно должно быть передано.
     *
     * @param pharmacy название аптеки
     * @return список лекарств
     * @throws IllegalArgumentException если название аптеки не задано
     */
    @Override
    public List<Medicine> getMedicineList(String pharmacy) throws IllegalArgumentException {
        if (pharmacy == null || pharmacy.trim().isEmpty()) {
            throw new IllegalArgumentException("Не указано название аптеки");
        }

        List<Medicine> result = new ArrayList<Medicine>();
        result.add(new Medicine("Парацетамол", "Таблетки 500мг", true));
        result.add(new Medicine("Ибупрофен", "Таблетки 400мг", true));
        result.add(new Medicine("Амоксициллин", "Капсулы 250мг", false));
        return result;
    }
}
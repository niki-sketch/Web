package com.labs.client;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.labs.shared.Medicine;

/**
 * Асинхронная версия интерфейса MedicineService.
 * GWT требует такой интерфейс для всех вызовов сервера с клиента,
 * так как обращение к серверу выполняется асинхронно
 * (без блокировки пользовательского интерфейса).
 */
public interface MedicineServiceAsync {

    /**
     * Асинхронно запрашивает список лекарств для указанной аптеки.
     *
     * @param pharmacy название аптеки
     * @param callback объект обратного вызова, в который придёт результат
     */
    void getMedicineList(String pharmacy, AsyncCallback<List<Medicine>> callback);
}
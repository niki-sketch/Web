package com.labs.client;

import java.util.List;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.labs.shared.Medicine;

/**
 * Интерфейс web-сервиса, поставляющего клиенту список лекарств
 * по названию аптеки. Используется механизм GWT RPC.
 */
@RemoteServiceRelativePath("medicine")
public interface MedicineService extends RemoteService {

    /**
     * Возвращает список лекарств для указанной аптеки.
     *
     * @param pharmacy название аптеки
     * @return список лекарств
     * @throws IllegalArgumentException если название аптеки не задано
     */
    List<Medicine> getMedicineList(String pharmacy) throws IllegalArgumentException;
}
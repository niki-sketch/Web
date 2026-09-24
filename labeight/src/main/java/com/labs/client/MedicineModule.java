package com.labs.client;

import java.util.List;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.labs.shared.Medicine;

/**
 * Точка входа GWT-приложения. Метод onModuleLoad() вызывается
 * автоматически при загрузке HTML-страницы, ссылающейся на модуль.
 *
 * <p>Приложение отображает поле ввода названия аптеки и кнопку
 * "Показать". При нажатии кнопки клиент асинхронно запрашивает
 * у сервера список лекарств и выводит его в виде таблицы.</p>
 */
public class MedicineModule implements EntryPoint {

    /** Сообщение, отображаемое при ошибке связи с сервером. */
    private static final String SERVER_ERROR =
            "Произошла ошибка при обращении к серверу. Проверьте соединение.";

    /** Асинхронный клиент web-сервиса. */
    private final MedicineServiceAsync medicineService = GWT.create(MedicineService.class);

    /**
     * Точка входа приложения. Строит пользовательский интерфейс
     * и добавляет его в корневую панель HTML-страницы.
     */
    @Override
    public void onModuleLoad() {

        final TextBox pharmacyBox = new TextBox();
        pharmacyBox.setWidth("200px");

        Button showButton = new Button("Показать лекарства");
        final Label errorLabel = new Label();
        errorLabel.setStyleName("errorLabel");

        final FlexTable resultTable = new FlexTable();
        resultTable.setBorderWidth(1);
        resultTable.setText(0, 0, "Название");
        resultTable.setText(0, 1, "Форма выпуска");
        resultTable.setText(0, 2, "В наличии");

        VerticalPanel panel = new VerticalPanel();
        panel.add(new Label("Введите название аптеки:"));
        panel.add(pharmacyBox);
        panel.add(showButton);
        panel.add(errorLabel);
        panel.add(resultTable);

        RootPanel.get("medicineContainer").add(panel);

        // Обработчик нажатия кнопки
        showButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                errorLabel.setText("");
                String pharmacy = pharmacyBox.getText();

                medicineService.getMedicineList(pharmacy, new AsyncCallback<List<Medicine>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        errorLabel.setText(SERVER_ERROR + " (" + caught.getMessage() + ")");
                    }

                    @Override
                    public void onSuccess(List<Medicine> result) {
                        int rowCount = resultTable.getRowCount();
                        for (int i = rowCount - 1; i > 0; i--) {
                            resultTable.removeRow(i);
                        }
                        for (int i = 0; i < result.size(); i++) {
                            Medicine m = result.get(i);
                            resultTable.setText(i + 1, 0, m.getName());
                            resultTable.setText(i + 1, 1, m.getForm());
                            resultTable.setText(i + 1, 2, m.isAvailable() ? "Да" : "Нет");
                        }
                    }
                });
            }
        });
    }
}
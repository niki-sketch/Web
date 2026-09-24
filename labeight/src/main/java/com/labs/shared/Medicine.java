package com.labs.shared;

import java.io.Serializable;

/**
 * Класс, описывающий одно лекарство.
 * Используется совместно клиентом и сервером через GWT RPC,
 * поэтому должен реализовывать интерфейс Serializable.
 */
public class Medicine implements Serializable {

    private String name;
    private String form;
    private boolean available;

    /**
     * Пустой конструктор обязателен для GWT-сериализации.
     */
    public Medicine() {
    }

    public Medicine(String name, String form, boolean available) {
        this.name = name;
        this.form = form;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public String getForm() {
        return form;
    }

    public boolean isAvailable() {
        return available;
    }
}
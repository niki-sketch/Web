package lab.web;


public class MedicineUtils {

    /**
     * Проверяет, задано ли название аптеки в параметрах запроса.
     *
     * @param pharmacy название аптеки, полученное из запроса
     * @return {@code true}, если название задано и не пустое,
     *         иначе {@code false}
     */
    public static boolean isPharmacyValid(String pharmacy) {
        return pharmacy != null && !pharmacy.trim().isEmpty();
    }

    /**
     * Форматирует название аптеки для отображения на странице:
     * убирает лишние пробелы и делает первую букву заглавной.
     *
     * @param pharmacy исходное название аптеки
     * @return отформатированное название, либо строка
     *         "не указана", если название пустое или отсутствует
     */
    public static String formatPharmacyName(String pharmacy) {
        if (!isPharmacyValid(pharmacy)) {
            return "не указана";
        }
        String trimmed = pharmacy.trim();
        return Character.toUpperCase(trimmed.charAt(0)) + trimmed.substring(1);
    }
}
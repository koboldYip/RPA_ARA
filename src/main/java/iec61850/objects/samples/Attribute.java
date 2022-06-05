package iec61850.objects.samples;

/**
 * Обертка для атрибутов
 * для любых типов данных
 */
public class Attribute<T> {

    /**
     * Поле значения
     */
    private T value;

    /**
     * Конструктор создания нового атрибута
     *
     * @param value значение создаваемого атрибута
     */
    public Attribute(T value) {
        this.value = value;
    }

    /**
     * Функция получения значения атрибута
     *
     * @return value - значение атрибута
     */
    public T getValue() {
        return value;
    }

    /**
     * Фунция определения значения атрибута
     *
     * @param value - значение атрибута
     */
    public void setValue(T value) {
        this.value = value;
    }
}

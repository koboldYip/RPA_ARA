/**
 * Обертка для аналоговых значений типа float
 * Значение хранится в обертке Attribute
 *
 * @see Attribute
 */
public class AnalogueValue {

    /**
     * Поле оборачиваемого аналогового значения
     */
    private Attribute<Float> f = new Attribute<>(0f);

    /**
     * Функция получения отрибута
     *
     * @return Attribute
     */
    public Attribute<Float> getF() {
        return f;
    }

    /**
     * Функция определения атрибута
     *
     * @param f - аналоговое значение
     */
    public void setF(Attribute<Float> f) {
        this.f = f;
    }
}

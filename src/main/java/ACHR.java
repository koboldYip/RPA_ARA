public class ACHR extends LN{
    /*Частоты*/
    private double frequencyA = 0;
    private double frequencyB = 0;
    private double frequencyC = 0;
    /*Счетчики времени*/
    private static int time_ACHR = 0; //Счетчик времени в целом
    private static int timeA_ACHR = 0; //Счетчики времени для АЧР
    private static int timeB_ACHR = 0;
    private static int timeC_ACHR = 0;
    private boolean stepACHR = false; // Срабатывание АЧР
    /*Значение частоты в предыдущую секунду*/
    private double lastFrequencyA = 50.0;
    private double lastFrequencyB = 50.0;
    private double lastFrequencyC = 50.0;
    /*Уставка по скорости изменения частоты*/
    private double frequencyStepSetPoint = 0.0;
    /*Частота дискретизации*/
    private double samplingFrequency = 0;

    public void process(Double frequencyA, Double frequencyB, Double frequencyC, Double frequencySetPointACHR, Double timeSetPointACHR, Double samplingFrequency) {
        /*Проверка срабатывания ступени АЧР*/
        if (frequencyA <= frequencySetPointACHR) {
            timeA_ACHR++;
        } else {
            timeA_ACHR = 0;
        }
        if (frequencyB <= frequencySetPointACHR) {
            timeB_ACHR++;
        } else {
            timeB_ACHR = 0;
        }
        if (frequencyC <= frequencySetPointACHR) {
            timeC_ACHR++;
        } else {
            timeC_ACHR = 0;
        }
        /*Если уставка по времени АЧР превышена, отправляй команду на отключение*/
        if ((timeA_ACHR > timeSetPointACHR * 50 * samplingFrequency) || (timeB_ACHR > timeSetPointACHR * 50 * samplingFrequency)
                || (timeC_ACHR > timeSetPointACHR * 50 * samplingFrequency)) {
            stepACHR = true;
        }

        /*Блокировка по скорости изменения частоты (если скорость изменения частоты превышает 1 Гц/с,
        то срабатывает блокировка*/
        if ((samplingFrequency * 50) == 1.0){
            if ((Math.abs((lastFrequencyA - frequencyA)) >= 1) || (Math.abs((lastFrequencyB - frequencyB)) >= 01) ||
                    (Math.abs((lastFrequencyC - frequencyC)) >= 1)){
                stepACHR = false;
            }
            lastFrequencyA = frequencyA;
            lastFrequencyB = frequencyB;
            lastFrequencyC = frequencyC;
            time_ACHR = 0;
        }

        time_ACHR++;
    }

    public boolean isStepACHR() {
        return stepACHR;
    }

    public void setStepACHR(boolean stepACHR) {
        this.stepACHR = stepACHR;
    }

    public double getFrequencyStepSetPoint() {
        return frequencyStepSetPoint;
    }

    public void setFrequencyStepSetPoint(double frequencyStepSetPoint) {
        this.frequencyStepSetPoint = frequencyStepSetPoint;
    }

    public double getFrequencyA() {
        return frequencyA;
    }

    public void setFrequencyA(double frequencyA) {
        this.frequencyA = frequencyA;
    }

    public double getFrequencyB() {
        return frequencyB;
    }

    public void setFrequencyB(double frequencyB) {
        this.frequencyB = frequencyB;
    }

    public double getFrequencyC() {
        return frequencyC;
    }

    public void setFrequencyC(double frequencyC) {
        this.frequencyC = frequencyC;
    }

    public double getSamplingFrequency() {
        return samplingFrequency;
    }

    public void setSamplingFrequency(double samplingFrequency) {
        this.samplingFrequency = samplingFrequency;
    }

}

public class CHAPV extends LN {
    /*Счетчики времени*/
    private int timeA = 0;
    private int timeB = 0;
    private int timeC = 0;
    private boolean stepCHAPV = false; // Срабатывание ЧАПВ

    /*Частота дискретизации*/
    private double samplingFrequency = 200;

    public void process(Double frequencyA, Double frequencyB, Double frequencyC, Boolean stepACHR, Double frequencySetPointCHAPV, Double timeSetPointCHAPV) {
        /*Если сработало АЧР, осуществляем проверку условий срабатывания ЧАПВ*/
        if (stepACHR) {
            /*Срабатывание ЧАПВ*/
            if (frequencyA >= frequencySetPointCHAPV) {
                timeA++;
            } else {
                timeA = 0;
            }
            if (frequencyB >= frequencySetPointCHAPV) {
                timeB++;
            } else {
                timeB = 0;
            }
            if (frequencyC >= frequencySetPointCHAPV) {
                timeC++;
            } else {
                timeC = 0;
            }
            /*Если уставка по времени ЧАПВ превышена, отправляй команду на отключение*/
            if ((timeA > timeSetPointCHAPV * 50 * samplingFrequency) || (timeB > timeSetPointCHAPV * 50 * samplingFrequency)
                    || (timeC > timeSetPointCHAPV * 50 * samplingFrequency)) {
                stepCHAPV = true;
            }
        }
    }

    public boolean isStepCHAPV() {
        return stepCHAPV;
    }

    public void setStepCHAPV(boolean stepCHAPV) {
        this.stepCHAPV = stepCHAPV;
    }

    public double getSamplingFrequency() {
        return samplingFrequency;
    }

    public void setSamplingFrequency(double samplingFrequency) {
        this.samplingFrequency = samplingFrequency;
    }

}



package iec61850.nodes.algo;

import iec61850.nodes.common.LN;
import iec61850.objects.samples.Attribute;
import lombok.Data;

@Data
public class ACHR extends LN {

    private Attribute<Float> frequencyA = new Attribute<>(0f);
    private Attribute<Float> frequencyB = new Attribute<>(0f);
    private Attribute<Float> frequencyC = new Attribute<>(0f);

    private Attribute<Float> frequencySetPointACHR = new Attribute<>(0f);

    private int time_ACHR = 0;
    private int timeA_ACHR = 0;
    private int timeB_ACHR = 0;
    private int timeC_ACHR = 0;

    private int port;

    private Attribute<Boolean> stepACHR = new Attribute<>(false);
    private Attribute<Boolean> stepCHAPV = new Attribute<>(false);

    private Attribute<Float> lastFrequencyA = new Attribute<>(50f);
    private Attribute<Float> lastFrequencyB = new Attribute<>(50f);
    private Attribute<Float> lastFrequencyC = new Attribute<>(50f);

    private Attribute<Float> frequencyStepSetPoint = new Attribute<>(0f);

    private Attribute<Float> timeSetPointACHR = new Attribute<>(0f);

    private Attribute<Float> samplingFrequency = new Attribute<>(0f);

    public void process() {

        timeA_ACHR = frequencyA.getValue() <= frequencySetPointACHR.getValue() ? timeA_ACHR + 1 : 0;
        timeB_ACHR = frequencyB.getValue() <= frequencySetPointACHR.getValue() ? timeB_ACHR + 1 : 0;
        timeC_ACHR = frequencyC.getValue() <= frequencySetPointACHR.getValue() ? timeC_ACHR + 1 : 0;

        stepACHR.setValue((timeA_ACHR > timeSetPointACHR.getValue() * 50 * samplingFrequency.getValue()) ||
                (timeB_ACHR > timeSetPointACHR.getValue() * 50 * samplingFrequency.getValue()) ||
                (timeC_ACHR > timeSetPointACHR.getValue() * 50 * samplingFrequency.getValue()));

        if ((samplingFrequency.getValue() * 50) == 1.0) {
            if ((Math.abs((lastFrequencyA.getValue() - frequencyA.getValue())) >= 1) ||
                    (Math.abs((lastFrequencyB.getValue() - frequencyB.getValue())) >= 1) ||
                    (Math.abs((lastFrequencyC.getValue() - frequencyC.getValue())) >= 1)) {
                stepACHR.setValue(false);
            }
            lastFrequencyA = frequencyA;
            lastFrequencyB = frequencyB;
            lastFrequencyC = frequencyC;
            time_ACHR = 0;
        }

        if (stepCHAPV.getValue()) stepACHR.setValue(false);

        time_ACHR++;
    }
}

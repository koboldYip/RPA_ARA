package iec61850.nodes.algo;

import iec61850.nodes.common.LN;
import iec61850.objects.samples.Attribute;
import lombok.Data;

@Data
public class CHAPV extends LN {

    private Attribute<Float> frequencyA = new Attribute<>(0f);
    private Attribute<Float> frequencyB = new Attribute<>(0f);
    private Attribute<Float> frequencyC = new Attribute<>(0f);

    private int timeA = 0;
    private int timeB = 0;
    private int timeC = 0;

    private Attribute<Boolean> stepCHAPV = new Attribute<>(false);
    private Attribute<Boolean> stepACHR = new Attribute<>(false);

    private Attribute<Float> timeSetPointCHAPV = new Attribute<>(0.03f);

    private Attribute<Float> frequencySetPointCHAPV = new Attribute<>(49f);

    private double samplingFrequency = 80;

    public void process() {

        if (stepACHR.getValue()) {

            if (frequencyA.getValue() >= frequencySetPointCHAPV.getValue()) {
                timeA++;
            } else {
                timeA = 0;
            }
            if (frequencyB.getValue() >= frequencySetPointCHAPV.getValue()) {
                timeB++;
            } else {
                timeB = 0;
            }
            if (frequencyC.getValue() >= frequencySetPointCHAPV.getValue()) {
                timeC++;
            } else {
                timeC = 0;
            }

            if ((timeA > timeSetPointCHAPV.getValue() * 50 * samplingFrequency) ||
                    (timeB > timeSetPointCHAPV.getValue() * 50 * samplingFrequency) ||
                    (timeC > timeSetPointCHAPV.getValue() * 50 * samplingFrequency)) {
                stepCHAPV.setValue(true);
            }
        }
    }
}



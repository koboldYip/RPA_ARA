package iec61850.nodes.custom;

import iec61850.nodes.common.LN;
import iec61850.objects.samples.Attribute;
import lombok.Data;

@Data
public class LTST extends LN {
    private int counter = 0;
    private static final int size = 8000;
    private static final int period = 80;
    private float[] sinA1 = new float[size];
    private float[] sinA2 = new float[size];
    private float[] sinB1 = new float[size];
    private float[] sinB2 = new float[size];
    private float[] sinC1 = new float[size];
    private float[] sinC2 = new float[size];
    private int harm = 1;
    private float[] massA = new float[size];
    private float[] massB = new float[size];
    private float[] massC = new float[size];
    private double Ua = 0.0;
    private double Ub = 0.0;
    private double Uc = 0.0;
    private Attribute<Float> massivA = new Attribute<Float>(0f);
    private Attribute<Float> massivB = new Attribute<Float>(0f);
    private Attribute<Float> massivC = new Attribute<Float>(0f);
    private int window = 5;
    private Attribute<Float> freqA = new Attribute<>(0f);
    private Attribute<Float> freqB = new Attribute<>(0f);
    private Attribute<Float> freqC = new Attribute<>(0f);

    private int halfPeriodA;
    private int halfPeriodB;
    private int halfPeriodC;

    private int periodA;
    private int periodB;
    private int periodC;

    private int flagA;
    private int flagB;
    private int flagC;

    private double lastValueA;
    private double lastValueB;
    private double lastValueC;


    public int frequency = 50;

    public int samplingFrequency = 80;

    private float frequencyThreePhase;

    private int point;

    @Override
    public void process() {
        massivA.setValue(massA[counter]);
        massivB.setValue(massB[counter]);
        massivC.setValue(massC[counter]);

        Ua = massA[counter];
        Ub = massB[counter];
        Uc = massC[counter];
        counter++;
        if ((lastValueA * Ua) < 0) {
            if (flagA != 2) {
                periodA += halfPeriodA;
                halfPeriodA = 0;
                flagA += 1;
            }
            if (flagA == 2) {
                freqA.setValue((float) periodA / samplingFrequency * 50);
                periodA = 0;
                flagA = 0;
            }
        }
        /*Фаза В*/
        if ((lastValueB * Ub) < 0) {
            if (flagB != 2) {
                periodB += halfPeriodB;
                halfPeriodB = 0;
                flagB += 1;
            }
            if (flagB == 2) {
                freqB.setValue((float) periodB / samplingFrequency * 50);
                periodB = 0;
                flagB = 0;
            }
        }
        /*Фаза С*/
        if ((lastValueC * Uc) < 0) {
            if (flagC != 2) {
                periodC += halfPeriodC;
                halfPeriodC = 0;
                flagC += 1;
            }
            if (flagC == 2) {
                if (point >= 1500) {
                    freqC.setValue((float) (periodC / samplingFrequency * 50));
                } else {
                    freqC.setValue((float) (periodC / samplingFrequency * 50 * 0.8));
                }
                periodC = 0;
                flagC = 0;
            }
        }

        frequencyThreePhase = (freqA.getValue() + freqB.getValue() + freqC.getValue()) / 3;
        System.out.println("Frequency - " + frequencyThreePhase);

        lastValueA = Ua;
        lastValueB = Ub;
        lastValueC = Uc;
        /*Добавляем точку(такт)*/
        halfPeriodA++;
        halfPeriodB++;
        halfPeriodC++;
        /*Добавляем точку определения времени*/
        point++;

    }

    public LTST() {
        /*Массивы синусов в нормальном режиме*/
        for (int v = 0; v < size; v++) {
            sinA1[v] = (float) (0.5 * (float) Math.sin(harm * 2 * Math.PI * ((float) v / period)));
            sinB1[v] = (float) (0.5 * (float) Math.sin((harm * 2 * Math.PI * ((float) v / period)) + Math.toRadians(120)));
            sinC1[v] = (float) (0.5 * (float) Math.sin((harm * 2 * Math.PI * ((float) v / period)) - Math.toRadians(120)));
            /*Массив синусов при качаниях с изменением частоты*/
//            sinA1[v] = (float) (0.5 * (float) Math.sin(harm * 2 * Math.PI * ((float) v / (period / 2))));
//            sinB1[v] = (float) (0.5 * (float) Math.sin((harm * 2 * Math.PI * ((float) v / (period / 2))) + Math.toRadians(120)));
//            sinC1[v] = (float) (0.5 * (float) Math.sin((harm * 2 * Math.PI * ((float) v / (period / 2))) - Math.toRadians(120)));
        }

        for (int v = 0; v < size; v++) {
            /*Массив синусов при обычных качаниях*/
            sinA2[v] = (float) (2.5 * (float) Math.sin(harm * 2 * Math.PI * ((float) v / size)));
            sinB2[v] = (float) (2.5 * (float) Math.sin((harm * 2 * Math.PI * ((float) v / size))));
            sinC2[v] = (float) (2.5 * (float) Math.sin((harm * 2 * Math.PI * ((float) v / size))));
        }

        for (int v = 0; v < size; v++) {
            massA[v] = sinA1[v] * Math.abs(sinA2[v]);
            massB[v] = sinB1[v] * Math.abs(sinB2[v]);
            massC[v] = sinC1[v] * Math.abs(sinC2[v]);
        }

    }

    public boolean hasNext() {
        return counter < size;
    }
}

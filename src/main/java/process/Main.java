package process;

import iec61850.nodes.algo.ACHR;
import iec61850.nodes.algo.CHAPV;
import iec61850.nodes.custom.UDPBinder;
import iec61850.nodes.gui.NHMI;
import iec61850.nodes.gui.NHMISignal;


public class Main {


    public static int modellingTime = 30;

    public static int frequency = 50;

    public static int samplingFrequency = 80;

    public static int iteration = 0;

    public static void main(String[] args) {

        UDPBinder udpBinder = new UDPBinder();

        ACHR achr1 = new ACHR();
        ACHR achr2 = new ACHR();
        ACHR achr3 = new ACHR();
        ACHR achr4 = new ACHR();
        ACHR achr5 = new ACHR();
        ACHR achr6 = new ACHR();
        ACHR achr7 = new ACHR();
        ACHR achr8 = new ACHR();
        ACHR achr9 = new ACHR();
        ACHR achr10 = new ACHR();
        ACHR achr11 = new ACHR();
        ACHR achr12 = new ACHR();
        ACHR achr13 = new ACHR();

        NHMI nhmi = new NHMI();

        CHAPV chapv1 = new CHAPV();
        CHAPV chapv2 = new CHAPV();
        CHAPV chapv3 = new CHAPV();
        CHAPV chapv4 = new CHAPV();
        CHAPV chapv5 = new CHAPV();
        CHAPV chapv6 = new CHAPV();
        CHAPV chapv7 = new CHAPV();
        CHAPV chapv8 = new CHAPV();
        CHAPV chapv9 = new CHAPV();
        CHAPV chapv10 = new CHAPV();
        CHAPV chapv11 = new CHAPV();
        CHAPV chapv12 = new CHAPV();
        CHAPV chapv13 = new CHAPV();

        achr1.setFrequencyA(udpBinder.getFreqA());
        achr1.setFrequencyB(udpBinder.getFreqB());
        achr1.setFrequencyC(udpBinder.getFreqC());

        achr2.setFrequencyA(udpBinder.getFreqA());
        achr2.setFrequencyB(udpBinder.getFreqB());
        achr2.setFrequencyC(udpBinder.getFreqC());

        achr3.setFrequencyA(udpBinder.getFreqA());
        achr3.setFrequencyB(udpBinder.getFreqB());
        achr3.setFrequencyC(udpBinder.getFreqC());

        achr4.setFrequencyA(udpBinder.getFreqA());
        achr4.setFrequencyB(udpBinder.getFreqB());
        achr4.setFrequencyC(udpBinder.getFreqC());

        achr5.setFrequencyA(udpBinder.getFreqA());
        achr5.setFrequencyB(udpBinder.getFreqB());
        achr5.setFrequencyC(udpBinder.getFreqC());

        achr6.setFrequencyA(udpBinder.getFreqA());
        achr6.setFrequencyB(udpBinder.getFreqB());
        achr6.setFrequencyC(udpBinder.getFreqC());

        achr7.setFrequencyA(udpBinder.getFreqA());
        achr7.setFrequencyB(udpBinder.getFreqB());
        achr7.setFrequencyC(udpBinder.getFreqC());

        achr8.setFrequencyA(udpBinder.getFreqA());
        achr8.setFrequencyB(udpBinder.getFreqB());
        achr8.setFrequencyC(udpBinder.getFreqC());

        achr9.setFrequencyA(udpBinder.getFreqA());
        achr9.setFrequencyB(udpBinder.getFreqB());
        achr9.setFrequencyC(udpBinder.getFreqC());

        achr10.setFrequencyA(udpBinder.getFreqA());
        achr10.setFrequencyB(udpBinder.getFreqB());
        achr10.setFrequencyC(udpBinder.getFreqC());

        achr11.setFrequencyA(udpBinder.getFreqA());
        achr11.setFrequencyB(udpBinder.getFreqB());
        achr11.setFrequencyC(udpBinder.getFreqC());

        achr12.setFrequencyA(udpBinder.getFreqA());
        achr12.setFrequencyB(udpBinder.getFreqB());
        achr12.setFrequencyC(udpBinder.getFreqC());

        achr1.setStepCHAPV(chapv1.getStepCHAPV());
        chapv1.setStepACHR(achr1.getStepACHR());

        achr2.setStepCHAPV(chapv2.getStepCHAPV());
        chapv2.setStepACHR(achr2.getStepACHR());

        achr3.setStepCHAPV(chapv3.getStepCHAPV());
        chapv3.setStepACHR(achr3.getStepACHR());

        achr4.setStepCHAPV(chapv4.getStepCHAPV());
        chapv4.setStepACHR(achr4.getStepACHR());

        achr5.setStepCHAPV(chapv5.getStepCHAPV());
        chapv5.setStepACHR(achr5.getStepACHR());

        achr6.setStepCHAPV(chapv6.getStepCHAPV());
        chapv6.setStepACHR(achr6.getStepACHR());

        achr7.setStepCHAPV(chapv7.getStepCHAPV());
        chapv7.setStepACHR(achr7.getStepACHR());

        achr8.setStepCHAPV(chapv8.getStepCHAPV());
        chapv8.setStepACHR(achr8.getStepACHR());

        achr9.setStepCHAPV(chapv9.getStepCHAPV());
        chapv9.setStepACHR(achr9.getStepACHR());

        achr10.setStepCHAPV(chapv10.getStepCHAPV());
        chapv10.setStepACHR(achr10.getStepACHR());

        achr11.setStepCHAPV(chapv11.getStepCHAPV());
        chapv11.setStepACHR(achr11.getStepACHR());

        achr12.setStepCHAPV(chapv12.getStepCHAPV());
        chapv12.setStepACHR(achr12.getStepACHR());

        udpBinder.getAchrs().add(achr1);
        udpBinder.getAchrs().add(achr2);
        udpBinder.getAchrs().add(achr3);
        udpBinder.getAchrs().add(achr4);
        udpBinder.getAchrs().add(achr5);
        udpBinder.getAchrs().add(achr6);
        udpBinder.getAchrs().add(achr7);
        udpBinder.getAchrs().add(achr8);
        udpBinder.getAchrs().add(achr9);
        udpBinder.getAchrs().add(achr10);
        udpBinder.getAchrs().add(achr11);
        udpBinder.getAchrs().add(achr12);

        achr1.setPort(9001);
        achr2.setPort(9002);
        achr3.setPort(9003);
        achr4.setPort(9004);
        achr5.setPort(9005);
        achr6.setPort(9006);
        achr7.setPort(9007);
        achr8.setPort(9008);
        achr9.setPort(9009);
        achr10.setPort(9010);
        achr11.setPort(9011);
        achr12.setPort(9012);
        achr13.setPort(9013);

        nhmi.addSignals(
                new NHMISignal("FreqA", udpBinder.getFreqA()),
                new NHMISignal("FreqB", udpBinder.getFreqB()),
                new NHMISignal("FreqC", udpBinder.getFreqC())
        );

        while (iteration < (modellingTime * frequency * samplingFrequency - 1)) {
            udpBinder.process();
            achr1.process();
            achr2.process();
            achr3.process();
            achr4.process();
            achr5.process();
            achr6.process();
            achr7.process();
            achr8.process();
            achr9.process();
            achr10.process();
            achr11.process();
            achr12.process();
            achr13.process();
            chapv1.process();
            chapv2.process();
            chapv3.process();
            chapv4.process();
            chapv5.process();
            chapv6.process();
            chapv7.process();
            chapv8.process();
            chapv9.process();
            chapv10.process();
            chapv11.process();
            chapv12.process();
            chapv13.process();
            udpBinder.sending();
            nhmi.process();

        }
    }
}

package process;

import iec61850.nodes.algo.ACHR;
import iec61850.nodes.algo.CHAPV;
import iec61850.nodes.custom.LTST;
import iec61850.nodes.custom.UDPBinder;
import iec61850.nodes.gui.NHMI;
import iec61850.nodes.gui.NHMISignal;


public class Main {

    public static int modellingTime = 30;

    public static int frequency = 150;

    public static int samplingFrequency = 80;

    public static int iteration = 0;


    public static void main(String[] args) {

        UDPBinder udpBinder = new UDPBinder();

        LTST ltst = new LTST(0);

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
        NHMI nhmi1 = new NHMI();
        NHMI nhmi2 = new NHMI();

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

        achr13.setFrequencyA(udpBinder.getFreqA());
        achr13.setFrequencyB(udpBinder.getFreqB());
        achr13.setFrequencyC(udpBinder.getFreqC());


//        achr1.setFrequencyA(ltst.getFreqA());
//        achr1.setFrequencyB(ltst.getFreqB());
//        achr1.setFrequencyC(ltst.getFreqC());
//
//        achr2.setFrequencyA(ltst.getFreqA());
//        achr2.setFrequencyB(ltst.getFreqB());
//        achr2.setFrequencyC(ltst.getFreqC());
//
//        achr3.setFrequencyA(ltst.getFreqA());
//        achr3.setFrequencyB(ltst.getFreqB());
//        achr3.setFrequencyC(ltst.getFreqC());
//
//        achr4.setFrequencyA(ltst.getFreqA());
//        achr4.setFrequencyB(ltst.getFreqB());
//        achr4.setFrequencyC(ltst.getFreqC());
//
//        achr5.setFrequencyA(ltst.getFreqA());
//        achr5.setFrequencyB(ltst.getFreqB());
//        achr5.setFrequencyC(ltst.getFreqC());
//
//        achr6.setFrequencyA(ltst.getFreqA());
//        achr6.setFrequencyB(ltst.getFreqB());
//        achr6.setFrequencyC(ltst.getFreqC());
//
//        achr7.setFrequencyA(ltst.getFreqA());
//        achr7.setFrequencyB(ltst.getFreqB());
//        achr7.setFrequencyC(ltst.getFreqC());
//
//        achr8.setFrequencyA(ltst.getFreqA());
//        achr8.setFrequencyB(ltst.getFreqB());
//        achr8.setFrequencyC(ltst.getFreqC());
//
//        achr9.setFrequencyA(ltst.getFreqA());
//        achr9.setFrequencyB(ltst.getFreqB());
//        achr9.setFrequencyC(ltst.getFreqC());
//
//        achr10.setFrequencyA(ltst.getFreqA());
//        achr10.setFrequencyB(ltst.getFreqB());
//        achr10.setFrequencyC(ltst.getFreqC());
//
//        achr11.setFrequencyA(ltst.getFreqA());
//        achr11.setFrequencyB(ltst.getFreqB());
//        achr11.setFrequencyC(ltst.getFreqC());
//
//        achr12.setFrequencyA(ltst.getFreqA());
//        achr12.setFrequencyB(ltst.getFreqB());
//        achr12.setFrequencyC(ltst.getFreqC());
//
//        achr13.setFrequencyA(ltst.getFreqA());
//        achr13.setFrequencyB(ltst.getFreqB());
//        achr13.setFrequencyC(ltst.getFreqC());
//
//        chapv1.setFrequencyA(ltst.getFreqA());
//        chapv1.setFrequencyB(ltst.getFreqB());
//        chapv1.setFrequencyC(ltst.getFreqC());
//
//        chapv2.setFrequencyA(ltst.getFreqA());
//        chapv2.setFrequencyB(ltst.getFreqB());
//        chapv2.setFrequencyC(ltst.getFreqC());
//
//        chapv3.setFrequencyA(ltst.getFreqA());
//        chapv3.setFrequencyB(ltst.getFreqB());
//        chapv3.setFrequencyC(ltst.getFreqC());
//
//        chapv4.setFrequencyA(ltst.getFreqA());
//        chapv4.setFrequencyB(ltst.getFreqB());
//        chapv4.setFrequencyC(ltst.getFreqC());
//
//        chapv5.setFrequencyA(ltst.getFreqA());
//        chapv5.setFrequencyB(ltst.getFreqB());
//        chapv5.setFrequencyC(ltst.getFreqC());
//
//        chapv6.setFrequencyA(ltst.getFreqA());
//        chapv6.setFrequencyB(ltst.getFreqB());
//        chapv6.setFrequencyC(ltst.getFreqC());
//
//        chapv7.setFrequencyA(ltst.getFreqA());
//        chapv7.setFrequencyB(ltst.getFreqB());
//        chapv7.setFrequencyC(ltst.getFreqC());
//
//        chapv8.setFrequencyA(ltst.getFreqA());
//        chapv8.setFrequencyB(ltst.getFreqB());
//        chapv8.setFrequencyC(ltst.getFreqC());
//
//        chapv9.setFrequencyA(ltst.getFreqA());
//        chapv9.setFrequencyB(ltst.getFreqB());
//        chapv9.setFrequencyC(ltst.getFreqC());
//
//        chapv10.setFrequencyA(ltst.getFreqA());
//        chapv10.setFrequencyB(ltst.getFreqB());
//        chapv10.setFrequencyC(ltst.getFreqC());
//
//        chapv11.setFrequencyA(ltst.getFreqA());
//        chapv11.setFrequencyB(ltst.getFreqB());
//        chapv11.setFrequencyC(ltst.getFreqC());
//
//        chapv12.setFrequencyA(ltst.getFreqA());
//        chapv12.setFrequencyB(ltst.getFreqB());
//        chapv12.setFrequencyC(ltst.getFreqC());
//
//        chapv13.setFrequencyA(ltst.getFreqA());
//        chapv13.setFrequencyB(ltst.getFreqB());
//        chapv13.setFrequencyC(ltst.getFreqC());

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

        achr13.setStepCHAPV(chapv13.getStepCHAPV());
        chapv13.setStepACHR(achr13.getStepACHR());

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
        udpBinder.getAchrs().add(achr13);

        achr1.getFrequencySetPointACHR().setValue(150f);
        achr2.getFrequencySetPointACHR().setValue(48.25f);
        achr3.getFrequencySetPointACHR().setValue(48f);
        achr4.getFrequencySetPointACHR().setValue(47.75f);
        achr5.getFrequencySetPointACHR().setValue(47.5f);
        achr6.getFrequencySetPointACHR().setValue(47.25f);
        achr7.getFrequencySetPointACHR().setValue(47f);
        achr8.getFrequencySetPointACHR().setValue(46.75f);
        achr9.getFrequencySetPointACHR().setValue(46.5f);
        achr10.getFrequencySetPointACHR().setValue(46.25f);
        achr11.getFrequencySetPointACHR().setValue(46f);
        achr12.getFrequencySetPointACHR().setValue(48.5f);
        achr13.getFrequencySetPointACHR().setValue(48.5f);

        achr1.getTimeSetPointACHR().setValue(0.30f);
        achr2.getTimeSetPointACHR().setValue(0.30f);
        achr3.getTimeSetPointACHR().setValue(0.30f);
        achr4.getTimeSetPointACHR().setValue(0.30f);
        achr5.getTimeSetPointACHR().setValue(0.30f);
        achr6.getTimeSetPointACHR().setValue(0.30f);
        achr7.getTimeSetPointACHR().setValue(0.30f);
        achr8.getTimeSetPointACHR().setValue(0.30f);
        achr9.getTimeSetPointACHR().setValue(0.2f);
        achr10.getTimeSetPointACHR().setValue(0.2f);
        achr11.getTimeSetPointACHR().setValue(0.2f);
        achr12.getTimeSetPointACHR().setValue(16f);
        achr13.getTimeSetPointACHR().setValue(28f);

        achr1.getSamplingFrequency().setValue(80f);
        achr2.getSamplingFrequency().setValue(80f);
        achr3.getSamplingFrequency().setValue(80f);
        achr4.getSamplingFrequency().setValue(80f);
        achr5.getSamplingFrequency().setValue(80f);
        achr6.getSamplingFrequency().setValue(80f);
        achr7.getSamplingFrequency().setValue(80f);
        achr8.getSamplingFrequency().setValue(80f);
        achr9.getSamplingFrequency().setValue(80f);
        achr10.getSamplingFrequency().setValue(80f);
        achr11.getSamplingFrequency().setValue(80f);
        achr12.getSamplingFrequency().setValue(80f);
        achr13.getSamplingFrequency().setValue(80f);

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

//        nhmi.addSignals(
//                new NHMISignal("FreqA", ltst.getFreqA()),
//                new NHMISignal("FreqB", ltst.getFreqB()),
//                new NHMISignal("FreqC", ltst.getFreqC())
//        );

        nhmi1.addSignals(
                new NHMISignal("achr1", achr1.getStepACHR())
        );
        nhmi1.addSignals(
                new NHMISignal("achr2", achr2.getStepACHR())
        );
        nhmi1.addSignals(
                new NHMISignal("achr3", achr3.getStepACHR())
        );
        nhmi1.addSignals(
                new NHMISignal("achr4", achr4.getStepACHR())
        );
        nhmi1.addSignals(
                new NHMISignal("achr5", achr5.getStepACHR())
        );
        nhmi1.addSignals(
                new NHMISignal("achr6", achr6.getStepACHR())
        );
        nhmi1.addSignals(
                new NHMISignal("achr7", achr7.getStepACHR())
        );
        nhmi1.addSignals(
                new NHMISignal("achr8", achr8.getStepACHR())
        );

        nhmi2.addSignals(
                new NHMISignal("chapv1", chapv1.getStepCHAPV()),
                new NHMISignal("chapv2", chapv2.getStepCHAPV()),
                new NHMISignal("chapv3", chapv3.getStepCHAPV())
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
            nhmi1.process();
            nhmi2.process();

        }

//        while (ltst.hasNext()) {
//            ltst.process();
//
//            nhmi.process();
//            nhmi1.process();
//            nhmi2.process();
//
//            achr1.process();
//            achr2.process();
//            achr3.process();
//            achr4.process();
//            achr5.process();
//            achr6.process();
//            achr7.process();
//            achr8.process();
//            achr9.process();
//            achr10.process();
//            achr11.process();
//            achr12.process();
//            achr13.process();
//
//            chapv1.process();
//            chapv2.process();
//            chapv3.process();
//            chapv4.process();
//            chapv5.process();
//            chapv6.process();
//            chapv7.process();
//            chapv8.process();
//            chapv9.process();
//            chapv10.process();
//            chapv11.process();
//            chapv12.process();
//            chapv13.process();
//        }
    }
}

package process;

import iec61850.nodes.algo.ACHR;
import iec61850.nodes.algo.CHAPV;
import iec61850.nodes.gui.NHMI;
import iec61850.nodes.gui.NHMISignal;
import iec61850.objects.samples.Attribute;

import java.net.DatagramPacket;


public class Main {

    private static Attribute<Float> freqA = new Attribute<>(0f);
    private static Attribute<Float> freqB = new Attribute<>(0f);
    private static Attribute<Float> freqC = new Attribute<>(0f);


    public static int modellingTime = 30;

    public static int frequency = 50;

    public static int samplingFrequency = 80;

    public static int iteration = 0;

    public static void main(String[] args) {

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


        nhmi.addSignals(
                new NHMISignal("FreqA", freqA),
                new NHMISignal("FreqB", freqB),
                new NHMISignal("FreqC", freqC)
        );

        while (iteration < (modellingTime * frequency * samplingFrequency - 1)) {
            try {

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

                if (chapv1.getStepCHAPV().getValue()) {
                    achr1.getStepACHR().setValue(false);
                }
                if (chapv2.getStepCHAPV().getValue()) {
                    achr2.getStepACHR().setValue(false);
                }
                if (chapv3.getStepCHAPV().getValue()) {
                    achr3.getStepACHR().setValue(false);
                }
                if (chapv4.getStepCHAPV().getValue()) {
                    achr4.getStepACHR().setValue(false);
                }
                if (chapv5.getStepCHAPV().getValue()) {
                    achr5.getStepACHR().setValue(false);
                }
                if (chapv6.getStepCHAPV().getValue()) {
                    achr6.getStepACHR().setValue(false);
                }
                if (chapv7.getStepCHAPV().getValue()) {
                    achr7.getStepACHR().setValue(false);
                }
                if (chapv8.getStepCHAPV().getValue()) {
                    achr8.getStepACHR().setValue(false);
                }
                if (chapv9.getStepCHAPV().getValue()) {
                    achr9.getStepACHR().setValue(false);
                }
                if (chapv10.getStepCHAPV().getValue()) {
                    achr10.getStepACHR().setValue(false);
                }
                if (chapv11.getStepCHAPV().getValue()) {
                    achr1.getStepACHR().setValue(false);
                }
                if (chapv12.getStepCHAPV().getValue()) {
                    achr1.getStepACHR().setValue(false);
                }
                if (chapv13.getStepCHAPV().getValue()) {
                    achr1.getStepACHR().setValue(false);
                }

                if (achr1.getStepACHR().getValue()) {
                    DatagramPacket outputPacketOne = new DatagramPacket(
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortOne);
                    udp.send(outputPacketOne);
                } else {
                    DatagramPacket outputPacketOne = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortOne);
                    udp.send(outputPacketOne);
                }
                if (achr2.getStepACHR().getValue()) {
                    DatagramPacket outputPacketTwo = new DatagramPacket(
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortTwo);
                    udp.send(outputPacketTwo);
                } else {
                    DatagramPacket outputPacketTwo = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortTwo);
                    udp.send(outputPacketTwo);
                }
                if (achr3.getStepACHR().getValue()) {
                    DatagramPacket outputPacketThree = new DatagramPacket(
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortThree);
                    udp.send(outputPacketThree);
                } else {
                    DatagramPacket outputPacketThree = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortThree);
                    udp.send(outputPacketThree);
                }
                if (achr4.getStepACHR().getValue()) {
                    DatagramPacket outputPacketFour = new DatagramPacket(
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortFour);
                    udp.send(outputPacketFour);
                } else {
                    DatagramPacket outputPacketFour = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortFour);
                    udp.send(outputPacketFour);
                }
                if (achr5.getStepACHR().getValue()) {
                    DatagramPacket outputPacketFive = new DatagramPacket(
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortFive);
                    udp.send(outputPacketFive);
                } else {
                    DatagramPacket outputPacketFive = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortFive);
                    udp.send(outputPacketFive);
                }
                if (achr6.getStepACHR().getValue()) {
                    DatagramPacket outputPacketSix = new DatagramPacket(
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortSeven);
                    udp.send(outputPacketSix);
                } else {
                    DatagramPacket outputPacketSix = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortSeven);
                    udp.send(outputPacketSix);
                }
                if (achr7.getStepACHR().getValue()) {
                    DatagramPacket outputPacketSeven = new DatagramPacket(
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortSix);
                    udp.send(outputPacketSeven);
                } else {
                    DatagramPacket outputPacketSeven = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortSix);
                    udp.send(outputPacketSeven);
                }
                if (achr8.getStepACHR().getValue()) {
                    DatagramPacket outputPacketEight = new DatagramPacket(
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortEight);
                    udp.send(outputPacketEight);
                } else {
                    DatagramPacket outputPacketEight = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortEight);
                    udp.send(outputPacketEight);
                }
                if (achr9.getStepACHR().getValue()) {
                    DatagramPacket outputPacketNine = new DatagramPacket(
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortNine);
                    udp.send(outputPacketNine);
                } else {
                    DatagramPacket outputPacketNine = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortNine);
                    udp.send(outputPacketNine);
                }
                if (achr10.getStepACHR().getValue()) {
                    DatagramPacket outputPacketTen = new DatagramPacket(
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                } else {
                    DatagramPacket outputPacketTen = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                }
                if (achr11.getStepACHR().getValue()) {
                    DatagramPacket outputPacketTen = new DatagramPacket(
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                } else {
                    DatagramPacket outputPacketTen = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                }
                if (achr12.getStepACHR().getValue()) {
                    DatagramPacket outputPacketTen = new DatagramPacket(
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                } else {
                    DatagramPacket outputPacketTen = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                }
                if (achr13.getStepACHR().getValue()) {
                    DatagramPacket outputPacketTen = new DatagramPacket(
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                } else {
                    DatagramPacket outputPacketTen = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            freqA.setValue((float) frequencyA);
            freqB.setValue((float) frequencyB);
            freqC.setValue((float) frequencyC);

            nhmi.process();

        }
    }
}

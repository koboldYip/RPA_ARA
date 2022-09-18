package iec61850.nodes.custom;

import iec61850.nodes.algo.ACHR;
import iec61850.nodes.common.LN;
import iec61850.objects.samples.Attribute;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;


@Data
public class UDPBinder extends LN {

    private DatagramSocket udp;

    private Attribute<Float> freqA = new Attribute<>(0f);
    private Attribute<Float> freqB = new Attribute<>(0f);
    private Attribute<Float> freqC = new Attribute<>(0f);


    private double Ua = 0.0;
    private double Ub = 0.0;
    private double Uc = 0.0;
    private double Uab = 0.0;
    private double Uac = 0.0;
    private double Ubc = 0.0;
    private double Ia = 0.0;
    private double Ib = 0.0;
    private double Ic = 0.0;

    private byte[] sendingDataBufferOne = {0, 0, 0, 0, 0, 0, -16, 63};

    private byte[] sendingDataBufferNull = {0, 0, 0, 0, 0, 0, 0, 0};

    private byte[] buffer = new byte[72];
    private byte[] phsUa = new byte[8];
    private byte[] phsUb = new byte[8];
    private byte[] phsUc = new byte[8];
    private byte[] phsIa = new byte[8];
    private byte[] phsIb = new byte[8];
    private byte[] phsIc = new byte[8];
    private byte[] phsUab = new byte[8];
    private byte[] phsUac = new byte[8];
    private byte[] phsUbc = new byte[8];

    private DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

    private InetAddress senderAddress;

    private List<ACHR> achrs = new ArrayList<>();

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


    public int frequency = 150;

    public int samplingFrequency = 80;

    private float frequencyThreePhase;

    private int point;

    @SneakyThrows
    public UDPBinder() {
        udp = new DatagramSocket(9090);
        udp.setSoTimeout(10000);
    }

    @SneakyThrows
    @Override
    public void process() {
        udp.receive(packet);
        senderAddress = packet.getAddress();

        for (int i = 0; i < (buffer.length) / 9; i++) {
            phsUa[8 - i - 1] = buffer[i];
            phsUb[8 - i - 1] = buffer[i + 8];
            phsUc[8 - i - 1] = buffer[i + 16];
            phsIa[8 - i - 1] = buffer[i + 24];
            phsIb[8 - i - 1] = buffer[i + 32];
            phsIc[8 - i - 1] = buffer[i + 40];
            phsUab[8 - i - 1] = buffer[i + 48];
            phsUac[8 - i - 1] = buffer[i + 56];
            phsUbc[8 - i - 1] = buffer[i + 64];
        }

        /*Преобразуем закодированный массив из байт в число*/
        Ua = ByteBuffer.wrap(phsUa).getDouble();
        Ub = ByteBuffer.wrap(phsUb).getDouble();
        Uc = ByteBuffer.wrap(phsUc).getDouble();
        Ia = ByteBuffer.wrap(phsIa).getDouble();
        Ib = ByteBuffer.wrap(phsIb).getDouble();
        Ic = ByteBuffer.wrap(phsIc).getDouble();
        Uab = ByteBuffer.wrap(phsUab).getDouble();
        Uac = ByteBuffer.wrap(phsUac).getDouble();
        Ubc = ByteBuffer.wrap(phsUbc).getDouble();

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
                freqC.setValue((float) periodC / samplingFrequency * 50);
                periodC = 0;
                flagC = 0;
            }
        }

        frequencyThreePhase = (freqA.getValue() + freqB.getValue() + freqC.getValue()) / 3;
        System.out.println("Frequency - " + frequencyThreePhase);

        lastValueA = Ua;
        lastValueB = Ub;
        lastValueC = Uc;

        halfPeriodA++;
        halfPeriodB++;
        halfPeriodC++;

        point++;
    }


    public void sending() {
        achrs.stream().filter(achr -> achr.getStepACHR().getValue()).forEach(achr -> {
            try {
                udp.send(new DatagramPacket(
                        sendingDataBufferNull, sendingDataBufferNull.length,
                        senderAddress, achr.getPort()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        achrs.stream().filter(achr -> !achr.getStepACHR().getValue()).forEach(achr -> {
            try {
                udp.send(new DatagramPacket(
                        sendingDataBufferOne, sendingDataBufferOne.length,
                        senderAddress, achr.getPort()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}

package iec61850.nodes.custom;

import iec61850.nodes.common.LN;
import lombok.Data;
import lombok.SneakyThrows;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;


@Data
public class UDPBinder extends LN {

    private DatagramSocket udp;

    private int senderPortOne = 9001;
    private int senderPortTwo = 9002;
    private int senderPortThree = 9003;
    private int senderPortFour = 9004;
    private int senderPortFive = 9005;
    private int senderPortSix = 9006;
    private int senderPortSeven = 9007;
    private int senderPortEight = 9008;
    private int senderPortNine = 9009;
    private int senderPortTen = 9010;

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

    private ByteBuffer received = ByteBuffer.allocate(72);
    private ByteBuffer phsUa = ByteBuffer.allocate(8);
    private ByteBuffer phsUb = ByteBuffer.allocate(8);
    private ByteBuffer phsUc = ByteBuffer.allocate(8);
    private ByteBuffer phsIa = ByteBuffer.allocate(8);
    private ByteBuffer phsIb = ByteBuffer.allocate(8);
    private ByteBuffer phsIc = ByteBuffer.allocate(8);
    private ByteBuffer phsUab = ByteBuffer.allocate(8);
    private ByteBuffer phsUac = ByteBuffer.allocate(8);
    private ByteBuffer phsUbc = ByteBuffer.allocate(8);

    private DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

    private InetAddress senderAddress;

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

    public double frequencyA = 0.0;
    public double frequencyB = 0.0;
    public double frequencyC = 0.0;

    public int frequency = 50;

    public int samplingFrequency = 80;

    private double frequencyThreePhase;

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

        received = ByteBuffer.wrap(buffer);

        phsUa = (ByteBuffer) phsUa.put(received.array(), 0, 8).rewind();
        phsUb = (ByteBuffer) phsUb.put(received.array(), 8, 8).rewind();
        phsUc = (ByteBuffer) phsUc.put(received.array(), 16, 8).rewind();
        phsIa = (ByteBuffer) phsIa.put(received.array(), 24, 8).rewind();
        phsIb = (ByteBuffer) phsIb.put(received.array(), 32, 8).rewind();
        phsIc = (ByteBuffer) phsIc.put(received.array(), 40, 8).rewind();
        phsUab = (ByteBuffer) phsUab.put(received.array(), 48, 8).rewind();
        phsUac = (ByteBuffer) phsUac.put(received.array(), 56, 8).rewind();
        phsUbc = (ByteBuffer) phsUbc.put(received.array(), 64, 8).rewind();

        Ua = phsUa.getDouble();
        Ub = phsUb.getDouble();
        Uc = phsUc.getDouble();
        Ia = phsIa.getDouble();
        Ib = phsIb.getDouble();
        Ic = phsIc.getDouble();
        Uab = phsUab.getDouble();
        Uac = phsUac.getDouble();
        Ubc = phsUbc.getDouble();

        if ((lastValueA * Ua) < 0) {
            if (flagA != 2) {
                periodA += halfPeriodA;
                halfPeriodA = 0;
                flagA += 1;
            }
            if (flagA == 2) {
                frequencyA = (float) periodA / samplingFrequency * 50;
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
                frequencyB = (float) periodB / samplingFrequency * 50;
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
                frequencyC = (float) periodC / samplingFrequency * 50;
                periodC = 0;
                flagC = 0;
            }
        }

        frequencyThreePhase = (frequencyA + frequencyB + frequencyC) / 3;

        lastValueA = Ua;
        lastValueB = Ub;
        lastValueC = Uc;

        halfPeriodA++;
        halfPeriodB++;
        halfPeriodC++;

        point++;
    }
}

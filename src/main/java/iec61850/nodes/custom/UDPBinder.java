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

    private Attribute<Double> lastValueA = new Attribute<>(0d);
    private Attribute<Double> lastValueB = new Attribute<>(0d);
    private Attribute<Double> lastValueC = new Attribute<>(0d);


    public int frequency = 50;

    public int samplingFrequency = 80;

    private Attribute<Float> frequencyThreePhase = new Attribute<>(0f);

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

        if ((lastValueA.getValue() * Ua) < 0) {
            if (flagA != 2) {
                periodA += halfPeriodA;
                halfPeriodA = 0;
                flagA += 1;
            }
            if (flagA == 2) {
                freqA.setValue((float) (periodA / samplingFrequency * 50));
                periodA = 0;
                flagA = 0;
            }
        }
        /*Фаза В*/
        if ((lastValueB.getValue() * Ub) < 0) {
            if (flagB != 2) {
                periodB += halfPeriodB;
                halfPeriodB = 0;
                flagB += 1;
            }
            if (flagB == 2) {
                freqB.setValue((float) (periodB / samplingFrequency * 50));
                periodB = 0;
                flagB = 0;
            }
        }
        /*Фаза С*/
        if ((lastValueC.getValue() * Uc) < 0) {
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

        frequencyThreePhase.setValue((freqA.getValue() + freqB.getValue() + freqC.getValue()) / 3);

        lastValueA.setValue(Ua);
        lastValueB.setValue(Ub);
        lastValueC.setValue(Uc);

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

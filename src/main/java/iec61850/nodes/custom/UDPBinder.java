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

    private byte[] sendingDataBufferOne = {0, 0, 0, 0, 0, 0, -16, 63};

    private byte[] sendingDataBufferNull = {0, 0, 0, 0, 0, 0, 0, 0};

    private byte[] buffer = new byte[24];
    private byte[] freqBufA = new byte[8];
    private byte[] freqBufB = new byte[8];
    private byte[] freqBufC = new byte[8];

    private DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

    private InetAddress senderAddress;

    private List<ACHR> achrs = new ArrayList<>();

    public int frequency = 50;

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

        for (int i = 0; i < (buffer.length) / 3; i++) {
            freqBufA[8 - i - 1] = buffer[i];
            freqBufB[8 - i - 1] = buffer[i + 8];
            freqBufC[8 - i - 1] = buffer[i + 16];
        }

        /*Преобразуем закодированный массив из байт в число*/

        freqA.setValue((float) ByteBuffer.wrap(freqBufA).getDouble());
        freqB.setValue((float) ByteBuffer.wrap(freqBufB).getDouble());
        freqC.setValue((float) ByteBuffer.wrap(freqBufC).getDouble());

        frequencyThreePhase = (freqA.getValue() + freqB.getValue() + freqC.getValue()) / 3;
        System.out.println("Frequency - " + frequencyThreePhase);

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

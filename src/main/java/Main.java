import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;


public class Main {


    private static Attribute<Float> freqA = new Attribute<>(0f);
    private static Attribute<Float> freqB = new Attribute<>(0f);
    private static Attribute<Float> freqC = new Attribute<>(0f);

    /*Делаем значения напряжений статическими*/
    public static double Ua = 0.0;
    public static double Ub = 0.0;
    public static double Uc = 0.0;
    public static double Uab = 0.0;
    public static double Uac = 0.0;
    public static double Ubc = 0.0;
    /*Делаем значения токов статическими*/
    public static double Ia = 0.0;
    public static double Ib = 0.0;
    public static double Ic = 0.0;
    /*Делаем значения частот статическими*/
    public static double frequencyA = 0.0;
    public static double frequencyB = 0.0;
    public static double frequencyC = 0.0;

    /*Время моделирования*/
    public static int modellingTime = 30;
    /*Частота работы электрической сети*/
    public static int frequency = 50;
    /*Частота дискретизации*/
    public static int samplingFrequency = 80;
    /*Массив со всеми значениями частоты*/
    public static double[] frequencyMassive = new double[modellingTime * frequency * samplingFrequency];
    /*Номер рассматриваемой итерации*/
    public static int itteration = 0;
    /*Массив, содержащий значения времени и частоты*/
    public static double[][] timeFrequencyMassive = new double[modellingTime * frequency * samplingFrequency][2];


    public static void main(String[] args) throws IOException {

        NHMI nhmi = new NHMI();


        nhmi.addSignals(
                new NHMISignal("FreqA", freqA),
                new NHMISignal("FreqB", freqB),
                new NHMISignal("FreqC", freqC)
        );
        // Серверный UDP-сокет запущен на этом порту
        DatagramSocket udp = new DatagramSocket(9090);

        /*Количество точек в полупериоде*/
        int halfPeriodA = 0;
        int halfPeriodB = 0;
        int halfPeriodC = 0;
        /*Количество точек в периоде*/
        int periodA = 0;
        int periodB = 0;
        int periodC = 0;
        /*Флаг, показывающий конец периода*/
        int flagA = 0;
        int flagB = 0;
        int flagC = 0;
        /*Значение предыдущей точки*/
        double lastValueA = 0.0;
        double lastValueB = 0.0;
        double lastValueC = 0.0;
        /*Усредненное значение частоты по всем трём фазам*/
        double frequencyThreePhase = 0;
        /*Количество точек с начал*/
        int point = 0;
        /*Передаем единицу*/
        byte[] sendingDataBufferOne = {0, 0, 0, 0, 0, 0, -16, 63};
        /*Передаем ноль*/
        byte[] sendingDataBufferNull = {0, 0, 0, 0, 0, 0, 0, 0};
        // Порты для отправки. Для линий с 1 по 13
        int senderPortOne = 9001;
        int senderPortTwo = 9002;
        int senderPortThree = 9003;
        int senderPortFour = 9004;
        int senderPortFive = 9005;
        int senderPortSix = 9006;
        int senderPortSeven = 9007;
        int senderPortEight = 9008;
        int senderPortNine = 9009;
        int senderPortTen = 9010;

        while (itteration < (modellingTime * frequency * samplingFrequency - 1)) { // До тех пор, пока не закончится цикл моделирования
            try {
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
                /*Время ожидания, на случай если 10 секунд не будет приходить информация*/
                udp.setSoTimeout(10000);
                /*Прин*/
                byte[] buffer = new byte[72];
                byte[] phsUa = new byte[8];
                byte[] phsUb = new byte[8];
                byte[] phsUc = new byte[8];
                byte[] phsIa = new byte[8];
                byte[] phsIb = new byte[8];
                byte[] phsIc = new byte[8];
                byte[] phsUab = new byte[8];
                byte[] phsUac = new byte[8];
                byte[] phsUbc = new byte[8];

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                udp.receive(packet);
                // Получите IP-адрес клиента, от которого пришло
                InetAddress senderAddress = packet.getAddress();

                /*Переворачиваем массив и записываем информацию о токах и напряжениях*/
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

                /*Алгоритм определения частоты*/
                /*Фаза А*/
                /*Проверка перехода через ноль*/
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
                System.out.println("Частота электрического тока - " + frequencyThreePhase);

                lastValueA = Ua;
                lastValueB = Ub;
                lastValueC = Uc;
                /*Добавляем точку(такт)*/
                halfPeriodA++;
                halfPeriodB++;
                halfPeriodC++;
                /*Добавляем точку определения времени*/
                point++;

                /*Реализация алгоритмов, представленных в шкафу*/
                /*Запуск АЧР*/
                /*Первая ступень*/
                achr1.process(frequencyA, frequencyB, frequencyC, 48.5, 0.30, 200.0);
                /*Вторая ступень*/
                achr2.process(frequencyA, frequencyB, frequencyC, 48.25, 0.30, 200.0);
                /*Третья ступень*/
                achr3.process(frequencyA, frequencyB, frequencyC, 48.0, 0.30, 200.0);
                /*Четвертая ступень*/
                achr4.process(frequencyA, frequencyB, frequencyC, 47.75, 0.30, 200.0);
                /*Пятая ступень*/
                achr5.process(frequencyA, frequencyB, frequencyC, 47.5, 0.30, 200.0);
                /*Шестая ступень*/
                achr6.process(frequencyA, frequencyB, frequencyC, 47.25, 0.30, 200.0);
                /*Седьмая ступень*/
                achr7.process(frequencyA, frequencyB, frequencyC, 47.0, 0.30, 200.0);
                /*Восьмая ступень*/
                achr8.process(frequencyA, frequencyB, frequencyC, 46.75, 0.30, 200.0);
                /*Девятая ступень*/
                achr9.process(frequencyA, frequencyB, frequencyC, 46.5, 0.20, 200.0);
                /*Десятая ступень*/
                achr10.process(frequencyA, frequencyB, frequencyC, 46.25, 0.2, 200.0);
                /*Одиннадцатая ступень*/
                achr11.process(frequencyA, frequencyB, frequencyC, 46.0, 0.2, 200.0);
                /*Двенадцатая ступень*/
                achr12.process(frequencyA, frequencyB, frequencyC, 48.5, 16.0, 200.0);
                /*Тринадцатая ступень*/
                achr13.process(frequencyA, frequencyB, frequencyC, 48.5, 28.0, 200.0);

                /*Запуск ЧАПВ*/
                /*Первая ступень*/
                CHAPV chapv1 = new CHAPV();
                chapv1.process(frequencyA, frequencyB, frequencyC, achr1.isStepACHR(), 49.5, 10.0);
                /*Вторая ступень*/
                CHAPV chapv2 = new CHAPV();
                chapv2.process(frequencyA, frequencyB, frequencyC, achr2.isStepACHR(), 49.5, 10.0);
                /*Третья ступень*/
                CHAPV chapv3 = new CHAPV();
                chapv3.process(frequencyA, frequencyB, frequencyC, achr3.isStepACHR(), 49.5, 10.0);
                /*Четвертая ступень*/
                CHAPV chapv4 = new CHAPV();
                chapv4.process(frequencyA, frequencyB, frequencyC, achr4.isStepACHR(), 49.5, 10.0);
                /*Пятая ступень*/
                CHAPV chapv5 = new CHAPV();
                chapv5.process(frequencyA, frequencyB, frequencyC, achr5.isStepACHR(), 49.5, 10.0);
                /*Шестая ступень*/
                CHAPV chapv6 = new CHAPV();
                chapv6.process(frequencyA, frequencyB, frequencyC, achr6.isStepACHR(), 49.5, 10.0);
                /*Седьмая ступень*/
                CHAPV chapv7 = new CHAPV();
                chapv7.process(frequencyA, frequencyB, frequencyC, achr7.isStepACHR(), 49.5, 10.0);
                /*Восьмая ступень*/
                CHAPV chapv8 = new CHAPV();
                chapv8.process(frequencyA, frequencyB, frequencyC, achr8.isStepACHR(), 49.5, 10.0);
                /*Девятая ступень*/
                CHAPV chapv9 = new CHAPV();
                chapv9.process(frequencyA, frequencyB, frequencyC, achr9.isStepACHR(), 49.5, 10.0);
                /*Десятая ступень*/
                CHAPV chapv10 = new CHAPV();
                chapv10.process(frequencyA, frequencyB, frequencyC, achr10.isStepACHR(), 49.5, 10.0);
                /*Одиннадцатая ступень*/
                CHAPV chapv11 = new CHAPV();
                chapv11.process(frequencyA, frequencyB, frequencyC, achr11.isStepACHR(), 49.5, 10.0);
                /*Двенадцатая ступень*/
                CHAPV chapv12 = new CHAPV();
                chapv12.process(frequencyA, frequencyB, frequencyC, achr12.isStepACHR(), 50.0, 10.0);
                /*Тринадцатая ступень*/
                CHAPV chapv13 = new CHAPV();
                chapv13.process(frequencyA, frequencyB, frequencyC, achr13.isStepACHR(), 50.0, 10.0);


                /*Если ЧАПВ сработало, то возвращаем АЧР в нормальное положение*/
                if (chapv1.isStepCHAPV()) {
                    achr1.setStepACHR(false);
                }
                if (chapv2.isStepCHAPV()) {
                    achr2.setStepACHR(false);
                }
                if (chapv3.isStepCHAPV()) {
                    achr3.setStepACHR(false);
                }
                if (chapv4.isStepCHAPV()) {
                    achr4.setStepACHR(false);
                }
                if (chapv5.isStepCHAPV()) {
                    achr5.setStepACHR(false);
                }
                if (chapv6.isStepCHAPV()) {
                    achr6.setStepACHR(false);
                }
                if (chapv7.isStepCHAPV()) {
                    achr7.setStepACHR(false);
                }
                if (chapv8.isStepCHAPV()) {
                    achr8.setStepACHR(false);
                }
                if (chapv9.isStepCHAPV()) {
                    achr9.setStepACHR(false);
                }
                if (chapv10.isStepCHAPV()) {
                    achr10.setStepACHR(false);
                }
                if (chapv11.isStepCHAPV()) {
                    achr1.setStepACHR(false);
                }
                if (chapv12.isStepCHAPV()) {
                    achr1.setStepACHR(false);
                }
                if (chapv13.isStepCHAPV()) {
                    achr1.setStepACHR(false);
                }

                /*Если все блокировки отсутствуют, то проверяем условия для отключение потребителей, если условие
                 * выполняется, тогда отключаем потребителя*/
                if (achr1.isStepACHR()) {
                    DatagramPacket outputPacketOne = new DatagramPacket( //Первый потербитель
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortOne);
                    udp.send(outputPacketOne);
                } else {
                    DatagramPacket outputPacketOne = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortOne);
                    udp.send(outputPacketOne);
                }
                if (achr2.isStepACHR()) {
                    DatagramPacket outputPacketTwo = new DatagramPacket( //Второй потербитель
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortTwo);
                    udp.send(outputPacketTwo);
                } else {
                    DatagramPacket outputPacketTwo = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortTwo);
                    udp.send(outputPacketTwo);
                }
                if (achr3.isStepACHR()) {
                    DatagramPacket outputPacketThree = new DatagramPacket( //Третий потербитель
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortThree);
                    udp.send(outputPacketThree);
                } else {
                    DatagramPacket outputPacketThree = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortThree);
                    udp.send(outputPacketThree);
                }
                if (achr4.isStepACHR()) {
                    DatagramPacket outputPacketFour = new DatagramPacket( //Четвертый потербитель
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortFour);
                    udp.send(outputPacketFour);
                } else {
                    DatagramPacket outputPacketFour = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortFour);
                    udp.send(outputPacketFour);
                }
                if (achr5.isStepACHR()) {
                    DatagramPacket outputPacketFive = new DatagramPacket( //Пятый потербитель
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortFive);
                    udp.send(outputPacketFive);
                } else {
                    DatagramPacket outputPacketFive = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortFive);
                    udp.send(outputPacketFive);
                }
                if (achr6.isStepACHR()) {
                    DatagramPacket outputPacketSix = new DatagramPacket( //Шестой потербитель
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortSeven);
                    udp.send(outputPacketSix);
                } else {
                    DatagramPacket outputPacketSix = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortSeven);
                    udp.send(outputPacketSix);
                }
                if (achr7.isStepACHR()) {
                    DatagramPacket outputPacketSeven = new DatagramPacket( //Седьмой потербитель
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortSix);
                    udp.send(outputPacketSeven);
                } else {
                    DatagramPacket outputPacketSeven = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortSix);
                    udp.send(outputPacketSeven);
                }
                if (achr8.isStepACHR()) {
                    DatagramPacket outputPacketEight = new DatagramPacket( //Восьмой потербитель
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortEight);
                    udp.send(outputPacketEight);
                } else {
                    DatagramPacket outputPacketEight = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortEight);
                    udp.send(outputPacketEight);
                }
                if (achr9.isStepACHR()) {
                    DatagramPacket outputPacketNine = new DatagramPacket( //Девятый потербитель
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortNine);
                    udp.send(outputPacketNine);
                } else {
                    DatagramPacket outputPacketNine = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortNine);
                    udp.send(outputPacketNine);
                }
                if (achr10.isStepACHR()) {
                    DatagramPacket outputPacketTen = new DatagramPacket( //Десятый потербитель
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                } else {
                    DatagramPacket outputPacketTen = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                }
                if (achr11.isStepACHR()) {
                    DatagramPacket outputPacketTen = new DatagramPacket( //Одиннадцатый потербитель
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                } else {
                    DatagramPacket outputPacketTen = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                }
                if (achr12.isStepACHR()) {
                    DatagramPacket outputPacketTen = new DatagramPacket( //Двенадцатый потербитель
                            sendingDataBufferNull, sendingDataBufferNull.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                } else {
                    DatagramPacket outputPacketTen = new DatagramPacket(
                            sendingDataBufferOne, sendingDataBufferOne.length,
                            senderAddress, senderPortTen);
                    udp.send(outputPacketTen);
                }
                if (achr13.isStepACHR()) {
                    DatagramPacket outputPacketTen = new DatagramPacket( //тринадцатый потербитель
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
        /*Построение частотно-временной характеристики*/
//
//        GraphicUtil graph = new GraphicUtil();
//        for (int i = 0; i < itteration; i++) {
//            timeFrequencyMassive[i][0] = (1 / ((double) frequency * samplingFrequency)) * i;
//            timeFrequencyMassive[i][1] = frequencyMassive[i];
//        }
//        graph.fillDataset(timeFrequencyMassive);
    }
}

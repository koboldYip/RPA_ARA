package iec61850.nodes.custom;

import iec61850.nodes.common.LN;
import iec61850.objects.samples.SAV;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class LSVC extends LN {

    private List<SAV> signals = new ArrayList<>(); //Список считываемых из строки файла значений
    private List<String> cfgFile = new ArrayList<>(); //Считанный файл конфиг
    private List<String> datFile = new ArrayList<>(); //Считанный файл dat
    private List<String> csvFile = new ArrayList<>(); //Считанный файл CSV
    private int numberOfSignals; //Количество аналоговых и дискретных значений
    private Iterator<String> iterator; //Итератор для перебора значений и последовательной обработки всеми узлами
    private List<Float> aBuffer = new ArrayList<>();
    private List<Float> bBuffer = new ArrayList<>();

    @Override
    public void process() {
        if (iterator.hasNext()) {
            List<Float> values = Arrays.stream(iterator.next().split(","))
                    .map(Float::parseFloat)
                    .collect(Collectors.toList());
            for (int s = 0; s < numberOfSignals; s++) {
                float value = aBuffer.isEmpty() ? values.get(s + 1) : values.get(s + 2) * aBuffer.get(s) + bBuffer.get(s);
                signals.get(s)
                        .getInstMag()
                        .getF()
                        .setValue(value * 1000);
            }
        }
    }

    public void readComtrade(String cfgPath) {
        cfgFile = readFile(cfgPath + ".cfg");
        datFile = readFile(cfgPath + ".dat");

        iterator = datFile.iterator();

        int analogNumber = Integer.parseInt(cfgFile.get(1).split(",")[1].replace("A", ""));
        numberOfSignals = analogNumber;

        if (signals.size() < numberOfSignals) {
            for (int i = 0; i < 100; i++) {
                signals.add(new SAV());
            }
        }

        for (int i = 2; i < (2 + analogNumber); i++) {
            String line = cfgFile.get(i);
            String[] lSplit = line.split(",");
            aBuffer.add(Float.parseFloat(lSplit[5]));
            bBuffer.add(Float.parseFloat(lSplit[6]));
        }

        System.out.printf("Осциллограмма загружена, количество сигналов: %s, количество выборок: %s %n%n", numberOfSignals, datFile.size());
    }

    public void readCSV(String cfgPath) {
        cfgFile = readFile(cfgPath + ".cfg");
        csvFile = readFile(cfgPath + ".csv");

        iterator = csvFile.iterator();

        int analogNumber = Integer.parseInt(cfgFile.get(1).split(",")[1].replace("A", ""));
        int discreteNumber = Integer.parseInt(cfgFile.get(1).split(",")[2].replace("D", ""));
        numberOfSignals = analogNumber + discreteNumber;
        while (signals.size() < numberOfSignals) {
            signals.add(new SAV());
        }
        System.out.printf("Осциллограмма загружена, количество сигналов: %s, количество выборок: %s %n%n", numberOfSignals, csvFile.size());
    }

    private List<String> readFile(String path) {
        List<String> fileEntry = new ArrayList<>();
        try (Stream<String> lines = Files.newBufferedReader(Paths.get(path)).lines()) {
            fileEntry = lines.filter(i -> !i.equals("\"Time\",\"Subsystem #1|Branch Currents|IaLine\",\"Subsystem #1|Branch Currents|IbLine\",\"Subsystem #1|Branch Currents|IcLine\",\"Subsystem #1|CTLs|Inputs|LG_FLT\""))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
        return fileEntry;
    }
}

package DataProviders;

import DTO.ColorDTO;
import lombok.Data;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ColorDataProvider {

    public ColorDTO cerulean, fuchsiaRose, trueRed;

    public void setUpColors() {
        cerulean = ColorDTO.builder()
                .id(1)
                .name("cerulean")
                .year(2000)
                .color("#98B2D1")
                .pantoneValue("15-4020")
                .build();
        fuchsiaRose = ColorDTO.builder()
                .id(2)
                .name("fuchsia rose")
                .year(2001)
                .color("#C74375")
                .pantoneValue("17-2031")
                .build();
        trueRed = ColorDTO.builder()
                .id(3)
                .name("true red")
                .year(2002)
                .color("#BF1932")
                .pantoneValue("19-1664")
                .build();
    }

    @DataProvider(name = "ColorDataProviderArray")
    public Object[][] getColorDataProviderFromArray() {
        setUpColors();
        Object[][] colorExpected = {{cerulean}, {fuchsiaRose}, {trueRed}};
        return colorExpected;
    }

    @DataProvider(name = "ColorDataProviderIterator")
    public Iterator<Object[]> getColorDataProviderFromIterator() {
        setUpColors();
        List<Object[]> colorList = new ArrayList<>();
        colorList.add(new Object[]{cerulean});
        colorList.add(new Object[]{fuchsiaRose});
        colorList.add(new Object[]{trueRed});
        return colorList.iterator();
    }

    @DataProvider(name = "ColorDataProviderFile")
    public Iterator<Object[]> getColorDataProviderFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(
                    new FileReader("C:\\JavaExamples\\ReqresInAPITests\\src\\test\\resources\\Colors.csv"));
        String line;
        List<Object[]> colorFile = new ArrayList<>();
            //читаем первую строку с названиями полей
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            colorFile.add(line.split(";"));//мы записываем в List МАССИВ строк, а не Массив ColorDTO!!!
            //поэтому при вызове DataProvider в Test нужно явно указать ВСЕ параметры из массива строк:
            //String id, String name, String year, String color, String pantoneValue
            //это отличие от предыдущего метода, где тоже возвращается Iterator<Object[]>
            //только в нем в Iterator лежит массив ColorDTO!!!
        }
        reader.close();
        System.out.println("size = " + colorFile.size());
        return colorFile.iterator();

    }
}


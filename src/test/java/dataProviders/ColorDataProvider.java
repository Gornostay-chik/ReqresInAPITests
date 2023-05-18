package dataProviders;

import dto.color.ColorDTO;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ColorDataProvider {

    public ColorDTO cerulean, fuchsiaRose, trueRed;

    public void setupParams() {
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
        setupParams();
        Object[][] colorExpected = {{cerulean}, {fuchsiaRose}, {trueRed}};
        return colorExpected;
    }

    @DataProvider(name = "ColorDataProviderIterator")
    public Iterator<Object[]> getColorDataProviderFromIterator() {
        setupParams();
        var colorList = List.of(new Object[]{cerulean},
                new Object[]{fuchsiaRose},
                new Object[]{trueRed});
        return colorList.iterator();
    }

    @DataProvider(name = "ColorDataProviderFile")
    public Iterator<Object[]> getColorDataProviderFromFile() throws IOException {

        File fileColor = new File(getClass().getResource("/Colors.csv").getPath());

        BufferedReader reader = new BufferedReader(
                    new FileReader(fileColor));
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

    @DataProvider(name = "ColorDataProviderDB")
    public Iterator<Object[]> getColorDataProviderFromDBOracle() throws SQLException {
        String DB_URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
        String DB_user = "ermin";
        String DB_password = "123456";

        Connection conn = DriverManager.getConnection(DB_URL, DB_user, DB_password);

        System.out.println("Соединение есть? - " + (conn.isValid(20000)?"да":"нет"));

        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM ermin.colors";

        ResultSet rset = stmt.executeQuery(query);

        List<Object[]> colorDB = new ArrayList<>();

        //извлекаем данные из ResultSet
        while(rset.next()){
            colorDB.add(new Object[]{ColorDTO.builder()
                    .id(rset.getInt(1))
                    .name(rset.getString(2))
                    .year(rset.getInt(3))
                    .color(rset.getString(4))
                    .pantoneValue(rset.getString( 5))
                    .build()});
        }
        rset.close();
        return colorDB.iterator();
    }
}


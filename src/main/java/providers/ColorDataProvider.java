package providers;

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

import static providers.ConfigProvider.*;

public class ColorDataProvider {

    @DataProvider(name = "ColorDataProviderArray")
    public Object[][] getColorDataProviderFromArray() {
        Object[][] colorExpected = {{CERULEAN}, {FUCHSIA_ROSE}, {TRUE_RED}};
        return colorExpected;
    }

    @DataProvider(name = "ColorDataProviderIterator")
    public Iterator<Object[]> getColorDataProviderFromIterator() {
        var colorList = List.of(new Object[]{CERULEAN},
                new Object[]{FUCHSIA_ROSE},
                new Object[]{TRUE_RED});
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
    public Iterator<Object[]> getColorDataProviderFromDBOracle() throws SQLException, IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String dbURL = System.getProperty("DB_URL");
        String dbName = System.getProperty("DB_name");
        String dbPassword = System.getProperty("DB_password");

        Connection conn = DriverManager.getConnection(dbURL, dbName, dbPassword);

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


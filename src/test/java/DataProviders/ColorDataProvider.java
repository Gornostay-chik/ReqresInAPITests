package DataProviders;

import DTO.ColorDTO;
import DTO.ColorDTOBuilder;
import org.testng.annotations.DataProvider;

public class ColorDataProvider {

    @DataProvider(name = "ColorDataProviderArray")
    public Object[][] getColorDataProvider(){

        ColorDTO cerulean = new ColorDTOBuilder()
                .setId(1)
                .setName("cerulean")
                .setYear(2000)
                .setColor("#98B2D1")
                .setPantoneValue("15-4020")
                .build();
        ColorDTO fuchsiaRose = new ColorDTOBuilder()
                .setId(2)
                .setName("fuchsia rose")
                .setYear(2001)
                .setColor("#C74375")
                .setPantoneValue("17-2031")
                .build();
        ColorDTO trueRed = new ColorDTOBuilder()
                .setId(3)
                .setName("true red")
                .setYear(2002)
                .setColor("#BF1932")
                .setPantoneValue("19-1664")
                .build();


        Object[][] colorExpected={{cerulean}, {fuchsiaRose}, {trueRed}};

        return colorExpected;
    }

}

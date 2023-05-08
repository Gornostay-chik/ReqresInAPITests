package DataProviders;

import DTO.ColorDTO;
import org.testng.annotations.DataProvider;

public class ColorDataProvider {

    @DataProvider(name = "ColorDataProviderArray")
    public Object[][] getColorDataProvider() {

        ColorDTO cerulean = ColorDTO.builder()
                .id(1)
                .name("cerulean")
                .year(2000)
                .color("#98B2D1")
                .pantoneValue("15-4020")
                .build();
        ColorDTO fuchsiaRose = ColorDTO.builder()
                .id(2)
                .name("fuchsia rose")
                .year(2001)
                .color("#C74375")
                .pantoneValue("17-2031")
                .build();
        ColorDTO trueRed = ColorDTO.builder()
                .id(3)
                .name("true red")
                .year(2002)
                .color("#BF1932")
                .pantoneValue("19-1664")
                .build();

        Object[][] colorExpected = {{cerulean}, {fuchsiaRose}, {trueRed}};

        return colorExpected;
    }

}

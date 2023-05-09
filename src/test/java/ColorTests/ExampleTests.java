package ColorTests;

import DTO.ColorDTO;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ExampleTests {
    @Test
    public void checkListSorting(){
        //0.1 Проверка сортировки List с пустыми элементами методом stream().sorted().toList()
        ColorDTO cerulean = ColorDTO.builder().id(1).name("cerulean").year(2000).color("#98B2D1").pantoneValue("15-4020").build();
        ColorDTO fuchsiaRose = ColorDTO.builder().id(2).name("fuchsia rose").year(2001).color("#C74375").pantoneValue("17-2031").build();
        ColorDTO trueRed = ColorDTO.builder().id(3).name("true red").year(2002).color("#BF1932").pantoneValue("19-1664").build();
        ColorDTO emptyColor = new ColorDTO();

        List<ColorDTO> listColor = new ArrayList<>();
        listColor.add(trueRed);
        listColor.add(emptyColor);
        listColor.add(cerulean);
        listColor.add(emptyColor);
        listColor.add(fuchsiaRose);

        List<ColorDTO> sortedListColor = listColor.stream().sorted().toList();
        Assert.assertEquals(listColor, sortedListColor);
    }

}

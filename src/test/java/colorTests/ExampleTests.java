package colorTests;

import dto.color.ColorDTO;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ExampleTests {
    @Test(description = "0.1 Проверка сортировки List с пустыми элементами методом stream().sorted().toList()")
    public void checkListSorting(){
        var cerulean = ColorDTO.builder().id(1).name("cerulean").year(2000).color("#98B2D1").pantoneValue("15-4020").build();
        var fuchsiaRose = ColorDTO.builder().id(2).name("fuchsia rose").year(2001).color("#C74375").pantoneValue("17-2031").build();
        var trueRed = ColorDTO.builder().id(3).name("true red").year(2002).color("#BF1932").pantoneValue("19-1664").build();
        var emptyColor = new ColorDTO();

        var listColor = List.of(emptyColor,
                emptyColor,
                cerulean,
                fuchsiaRose,
                trueRed);

        List<ColorDTO> sortedListColor = listColor.stream().sorted().toList();
        Assert.assertEquals(listColor, sortedListColor);
    }

}

package ColorTests;

import DTO.ColorDTO;
import DTO.ColorListInfoDTO;
import DTO.ColorListInfoDTOBuilder;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static Specifications.Specifications.requestSpecification;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class ColorListTests {

    ColorListInfoDTO listInfoExpected;
    ColorListInfoDTO listInfoJSON;
    Response resp;
    List<ColorDTO> colorListJSON;
    
    int minYearExpected = 2000;
    int maxYearExpected = 2005;

    @BeforeClass
    public void setupParams() {
        listInfoExpected = new ColorListInfoDTOBuilder()
                .setPage(1)
                .setPerPage(6)
                .setTotal(12)
                .setTotalPages(2)
                .build();

        resp = given()
                .spec(requestSpecification())
                .get("api/unknown");

        listInfoJSON = new ColorListInfoDTOBuilder()
                .setPage(resp.path("page"))
                .setPerPage(resp.path("per_page"))
                .setTotal(resp.path("total"))
                .setTotalPages(resp.path("total_pages"))
                .build();

        colorListJSON = resp.then().extract().body().jsonPath().getList("data", ColorDTO.class);

    }

    @Test
    public void checkListColorPageAttribute() {
        //1.1 Проверка атрибутов страницы: page, per_page, total, total_pages
        Assert.assertEquals(listInfoExpected.getPage(), listInfoJSON.getPage(), "\"page\" not equal!");
        Assert.assertEquals(listInfoExpected.getPerPage(), listInfoJSON.getPerPage(), "\"per_page\" not equal!");
        Assert.assertEquals(listInfoExpected.getTotal(), listInfoJSON.getTotal());
        Assert.assertEquals(listInfoExpected.getTotalPages(), listInfoJSON.getTotalPages());
    }

    @Test
    public void checkDATASize() {
        //1.2 Проверка, что массив [data] содержит количество элементов {color} = per_page
        Assert.assertEquals(colorListJSON.size(), listInfoJSON.getPerPage());
    }

    @Test
    public void checkMinimumColorYear() {
        //1.4 Проверка минимального элемента массива (Comparable по year)
        int minYearJSON = colorListJSON.stream().min((c1, c2) -> c1.compareTo(c2)).get().getYear();
        Assert.assertEquals(minYearJSON, minYearExpected);
    }

    @Test
    public void checkMaximumColorYear() {
        //1.5 Проверка максимального элемента массива (Comparable по year)
        int minYearJSON = colorListJSON.stream().max((c1, c2) -> c1.compareTo(c2)).get().getYear();
        Assert.assertEquals(minYearJSON, maxYearExpected);
    }
}

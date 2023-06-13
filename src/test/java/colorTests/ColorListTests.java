package colorTests;

import annotations.Project;
import dto.color.ColorDTO;
import dto.color.ColorListInfoDTO;
import dto.color.ColorListInfoDTOIgnore;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static specifications.Specifications.requestSpecification;
import static io.restassured.RestAssured.given;

@Owner("i.potryasai")
@Listeners({TestListener.class})
@Project(name = "Colors")
public class ColorListTests {

    ColorListInfoDTO listInfoExpected;
    ColorListInfoDTOIgnore listInfoIgnoreExpected;
    ColorListInfoDTO listInfoJSON;
    Response resp;
    List<ColorDTO> colorListJSON;

    int minYearExpected = 2000;
    int maxYearExpected = 2005;

    @BeforeClass
    public void setupParams() {
        listInfoExpected = new ColorListInfoDTO().builder()
                .page(1)
                .perPage(6)
                .total(12)
                .totalPages(2)
                .build();

        listInfoIgnoreExpected = new ColorListInfoDTOIgnore().builder()
                .page(1)
                .perPage(6)
                .total(12)
                .totalPages(2)
                .build();

        resp = given()
                .spec(requestSpecification())
                .get("api/unknown");

        listInfoJSON = new ColorListInfoDTO().builder()
                .page(resp.path("page"))
                .perPage(resp.path("per_page"))
                .total(resp.path("total"))
                .totalPages(resp.path("total_pages"))
                .build();

        colorListJSON = resp.then().extract().body().jsonPath().getList("data", ColorDTO.class);

    }

    @Test(testName = "Check List Color Page Attribute",
            description = "1.1 Проверка атрибутов страницы: page, per_page, total, total_pages")
    @Severity(SeverityLevel.NORMAL)
    public void checkListColorPageAttribute() {
        Assert.assertEquals(listInfoExpected.getPage(), listInfoJSON.getPage(), "\"page\" not equal!");
        Assert.assertEquals(listInfoExpected.getPerPage(), listInfoJSON.getPerPage(), "\"per_page\" not equal!");
        Assert.assertEquals(listInfoExpected.getTotal(), listInfoJSON.getTotal());
        Assert.assertEquals(listInfoExpected.getTotalPages(), listInfoJSON.getTotalPages());
    }

    @Test(testName = "Check list lolor attribute ignore",
            description = "1.1.1 Проверка п.1.1, но с использованием аннотации Jackson @JsonIgnoreProperties(ignoreUnknown = true)")
    public void checkListColorAttributeIgnore(){
        ColorListInfoDTOIgnore colorListIgnoreJSON = given()
                .spec(requestSpecification())
                .get("api/unknown")
                .then()
                .extract()
                .as(ColorListInfoDTOIgnore.class);

        Assert.assertEquals(colorListIgnoreJSON, listInfoIgnoreExpected);
    }

    @Test(testName = "Check array [data] size",
            description = "1.2 Проверка, что массив [data] содержит количество элементов {color} = per_page")
    public void checkDATASize() {
        Assert.assertEquals(colorListJSON.size(), listInfoJSON.getPerPage());
    }

    @Test(testName = "Check array [data] sort",
            description = "1.3 Проверка, что массив [data] отсортирован")
    public void checkDATASort(){
        List<ColorDTO> colorListJSONsorted = colorListJSON.stream().sorted().toList();
        Assert.assertEquals(colorListJSON,colorListJSONsorted);
    }

    @Test(testName = "Check the minimum year color",
            description = "1.4 Проверка минимального элемента массива (Comparable по year)")
    public void checkMinimumColorYear() {
        int minYearJSON = colorListJSON.stream().min((c1, c2) -> c1.compareTo(c2)).get().getYear();
        Assert.assertEquals(minYearJSON, minYearExpected);
    }

    @Test(testName = "Check the maximum year color",
            description = "1.5 Проверка максимального элемента массива (Comparable по year)")
    public void checkMaximumColorYear() {
        int minYearJSON = colorListJSON.stream().max((c1, c2) -> c1.compareTo(c2)).get().getYear();
        Assert.assertEquals(minYearJSON, maxYearExpected);
    }
}

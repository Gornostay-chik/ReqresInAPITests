package colorTests;

import dto.color.ColorDTO;
import dataProviders.ColorDataProvider;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import io.restassured.module.jsv.JsonSchemaValidator;

import static org.hamcrest.CoreMatchers.equalTo;
import static specifications.Specifications.requestSpecification;
import static io.restassured.RestAssured.given;

public class SingleColorTest {

    ColorDTO colorExpected, colorJSON;

    @BeforeClass
    public void setupParams() {

        colorExpected = ColorDTO.builder()
                .id(2)
                .name("fuchsia rose")
                .year(2001)
                .color("#C74375")
                .pantoneValue("17-2031")
                .build();


        colorJSON = given()
                .spec(requestSpecification())
                .get("/api/unknown/2")
                .then()
                //.log().all()
                .extract().body().jsonPath().getObject("data", ColorDTO.class);

        int i = 0;
    }

    @Test(description = "2.1 Статус ответа (можно сделать через спецификацию ответа)",
            priority = 1)
    public void checkStatus200OK() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200);
    }

    @Test(description = "2.2 Проверка id (просто для тренировки)",
            priority = 2)
    public void checkColorAttributesID() {
        Assert.assertEquals(colorExpected.getId(), colorJSON.getId());
    }

    @Test(description = "2.3 Проверка Name (просто для тренировки)",
            priority = 3)
    public void checkColorAttributeNAME() {
        Assert.assertEquals(colorExpected.getName(), colorJSON.getName());
    }

    @Test(description = "2.4 Проверка year (просто для тренировки)",
            priority = 4)
    public void checkColorAttributeYEAR() {
        Assert.assertEquals(colorExpected.getYear(), colorJSON.getYear());
    }

    @Test(description = "2.5 Проверка color (просто для тренировки)",
            priority = 5)
    public void checkColorAttributeCOLOR() {
        Assert.assertEquals(colorExpected.getColor(), colorJSON.getColor());
    }

    @Test(description = "2.6 Проверка pantoneValue (просто для тренировки)",
            priority = 6)
    public void checkColorAttributePANTONEVALUE() {
        Assert.assertEquals(colorExpected.getPantoneValue(), colorJSON.getPantoneValue());
    }

    @Test(description = "2.7 Проверка всех атрибутов DTO сразу",
            priority = 7)
    public void checkColorAllAttributes() {
        given()
                .spec(requestSpecification())
                .get("/api/unknown/2")
                .then()
                .assertThat()
                .body("data.id", equalTo(colorExpected.getId()),
                "data.name", equalTo(colorExpected.getName()),
                "data.year", equalTo(colorExpected.getYear()),
                "data.color", equalTo(colorExpected.getColor()),
                "data.pantone_value", equalTo(colorExpected.getPantoneValue()));
    }

    @Test(description = "2.8 Проверка схемы JSON, нужно добавить в pom <json-schema-validator>!")
    public void checkJSONSchema() {
        given()
                .spec(requestSpecification())
                .get("/api/unknown/2")
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("GETSingleColorSchema.json"));
    }

    @Test(description = "2.9 Проверка разных цветов по выбранным ID (например, по классам эквивалентности) " +
            "с использованием DataProvider из Object[][]",
            dataProvider = "ColorDataProviderArray",
            dataProviderClass = ColorDataProvider.class)
    public void checkColorsByIDfromArray(ColorDTO colorExpected) {
        ColorDTO colorJson = given()
                .spec(requestSpecification())
                .get("/api/unknown/" + colorExpected.getId())
                .then()
                .extract()
                .jsonPath()
                .getObject("data", ColorDTO.class);

        Assert.assertEquals(colorJson, colorExpected);
    }

    @Test(description = "2.10 Проверка разных цветов по выбранным ID (например, по классам эквивалентности) " +
            "с использованием DataProvider из Iterator<Object[]>",
            dataProvider = "ColorDataProviderIterator",
            dataProviderClass = ColorDataProvider.class)
    public void checkColorsByIDfromIterator(ColorDTO colorExpected){
        ColorDTO colorJson = given()
                .spec(requestSpecification())
                .get("/api/unknown/" + colorExpected.getId())
                .then()
                .extract()
                .jsonPath()
                .getObject("data", ColorDTO.class);

        Assert.assertEquals(colorJson, colorExpected);
    }

    @Test(description = "2.11 Проверка разных цветов по выбранным ID - DataProvider из файла " +
            "(тест выключен, так как может не быть файла - доделать чтобы читать из resources)",
            dataProvider = "ColorDataProviderFile",
            dataProviderClass = ColorDataProvider.class,
            enabled = true)
    //
    public void checkColorsByIDfromFile(String id,
                                        String name,
                                        String year,
                                        String color,
                                        String pantoneValue){
        given()
                .spec(requestSpecification())
                .get("/api/unknown/" + id)
                .then()
                .assertThat()
                .body("data.id", equalTo(Integer.parseInt(id)),
                "data.name", equalTo(name),
                "data.year", equalTo(Integer.parseInt(year)),
                "data.color", equalTo(color),
                "data.pantone_value", equalTo(pantoneValue));
    }

    @Test(description = "2.12 Проверка разных цветов по выбранным ID - DataProvider из БД Oracle " +
            "(тест выключен, так как БД может быть выключена)",
            dataProvider = "ColorDataProviderDB",
            dataProviderClass = ColorDataProvider.class,
            enabled = true)
    /*
    примечание: включать 2 службы:
    - OracleServiceXE
    - OracleOraDB21Home1TNSListener
    + вписать логин/пароль для БД в dataPorvider!
    */
    public void checkColorsByIDfromDB(ColorDTO colorExpected){
        given()
                .spec(requestSpecification())
                .get("/api/unknown/" + colorExpected.getId())
                .then()
                .assertThat()
                .body("data.id", equalTo(colorExpected.getId()),
                "data.name", equalTo(colorExpected.getName()),
                "data.year", equalTo(colorExpected.getYear()),
                "data.color", equalTo(colorExpected.getColor()),
                "data.pantone_value", equalTo(colorExpected.getPantoneValue()));
    }
}

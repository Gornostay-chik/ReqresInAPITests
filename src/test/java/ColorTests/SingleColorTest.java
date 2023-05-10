package ColorTests;

import DTO.ColorDTO;
import DataProviders.ColorDataProvider;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import io.restassured.module.jsv.JsonSchemaValidator;

import static Specifications.Specifications.requestSpecification;
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

    @Test(priority = 1)
    public void checkStatus200OK() {
        //2.1 Статус ответа (можно сделать через спецификацию ответа)
        given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200);
    }

    @Test(priority = 2)
    //2.2 Проверка id (просто для тренировки)
    public void checkColorAttributesID() {
        Assert.assertEquals(colorExpected.getId(), colorJSON.getId());
    }

    @Test(priority = 3)
    //2.3 Проверка Name (просто для тренировки)
    public void checkColorAttributeNAME() {
        Assert.assertEquals(colorExpected.getName(), colorJSON.getName());
    }

    @Test(priority = 4)
    //2.4 Проверка year (просто для тренировки)
    public void checkColorAttributeYEAR() {
        Assert.assertEquals(colorExpected.getYear(), colorJSON.getYear());
    }

    @Test(priority = 5)
    //2.5 Проверка color (просто для тренировки)
    public void checkColorAttributeCOLOR() {
        Assert.assertEquals(colorExpected.getColor(), colorJSON.getColor());
    }

    @Test(priority = 6)
    //2.6 Проверка pantoneValue (просто для тренировки)
    public void checkColorAttributePANTONEVALUE() {
        Assert.assertEquals(colorExpected.getPantoneValue(), colorJSON.getPantoneValue());
    }

    @Test(priority = 7)
    public void checkColorAllAttributes() {
        //2.7 Проверка всех атрибутов DTO сразу
        given()
                .spec(requestSpecification())
                .get("/api/unknown/2")
                .then()
                .assertThat()
                .body("data.id", Matchers.is(colorExpected.getId()))
                .body("data.name", Matchers.is(colorExpected.getName()))
                .body("data.year", Matchers.is(colorExpected.getYear()))
                .body("data.color", Matchers.is(colorExpected.getColor()))
                .body("data.pantone_value", Matchers.is(colorExpected.getPantoneValue()));
    }

    @Test
    public void checkJSONSchema() {
        //2.8 Проверка схемы JSON, нужно добавить в pom <json-schema-validator>!
        given()
                .spec(requestSpecification())
                .get("/api/unknown/2")
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("GETSingleColorSchema.json"));
    }

    @Test(dataProvider = "ColorDataProviderArray", dataProviderClass = ColorDataProvider.class)
    public void checkColorsByIDfromArray(ColorDTO colorExpected) {
        /*2.9 Проверка разных цветов по выбранным ID (например, по классам эквивалентности)
        с использованием DataProvider из Object[][]*/
        ColorDTO colorJson = given()
                .spec(requestSpecification())
                .get("/api/unknown/" + colorExpected.getId())
                .then()
                .extract()
                .jsonPath()
                .getObject("data", ColorDTO.class);

        Assert.assertEquals(colorJson, colorExpected);
    }

    @Test(dataProvider = "ColorDataProviderIterator", dataProviderClass = ColorDataProvider.class)
    public void checkColorsByIDfromIterator(ColorDTO colorExpected){
        /*2.10 Проверка разных цветов по выбранным ID (например, по классам эквивалентности)
        с использованием DataProvider из Iterator<Object[]>*/
        ColorDTO colorJson = given()
                .spec(requestSpecification())
                .get("/api/unknown/" + colorExpected.getId())
                .then()
                .extract()
                .jsonPath()
                .getObject("data", ColorDTO.class);

        Assert.assertEquals(colorJson, colorExpected);
    }

    @Test(dataProvider = "ColorDataProviderFile", dataProviderClass = ColorDataProvider.class, enabled = false)
    //2.11 Проверка разных цветов по выбранным ID - DataProvider из файла
    //тест выключен, так как может не быть файла - доделать чтобы читать из resources
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
                .body("data.id", Matchers.is(Integer.parseInt(id)))
                .body("data.name", Matchers.is(name))
                .body("data.year", Matchers.is(Integer.parseInt(year)))
                .body("data.color", Matchers.is(color))
                .body("data.pantone_value", Matchers.is(pantoneValue));
    }

    @Test(dataProvider = "ColorDataProviderDB", dataProviderClass = ColorDataProvider.class, enabled = false)
    //2.12 Проверка разных цветов по выбранным ID - DataProvider из БД Oracle
    /*тест выключен, так как БД может быть выключена
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
                .body("data.id", Matchers.is(colorExpected.getId()))
                .body("data.name", Matchers.is(colorExpected.getName()))
                .body("data.year", Matchers.is(colorExpected.getYear()))
                .body("data.color", Matchers.is(colorExpected.getColor()))
                .body("data.pantone_value", Matchers.is(colorExpected.getPantoneValue()));
    }
}

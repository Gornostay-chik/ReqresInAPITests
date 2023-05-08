package ColorTests;

import DTO.ColorDTO;
import DataProviders.ColorDataProvider;
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
        given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200);
    }

    @Test(priority = 2)
    public void checkColorAttributesID() {
        Assert.assertEquals(colorExpected.getId(), colorJSON.getId());
    }

    @Test(priority = 3)
    public void checkColorAttributeNAME() {
        Assert.assertEquals(colorExpected.getName(), colorJSON.getName());
    }

    @Test(priority = 4)
    public void checkColorAttributeYEAR() {
        Assert.assertEquals(colorExpected.getYear(), colorJSON.getYear());
    }

    @Test(priority = 5)
    public void checkColorAttributeCOLOR() {
        Assert.assertEquals(colorExpected.getColor(), colorJSON.getColor());
    }

    @Test(priority = 6)
    public void checkColorAttributePANTONEVALUE() {
        Assert.assertEquals(colorExpected.getPantoneValue(), colorJSON.getPantoneValue());
    }

    @Test(priority = 7)
    public void checkColorAllAttributes() { //проверка всех атрибутов DTO сразу
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
    public void checkJSONSchema() { //проверка схемы JSON, нужно добавить в pom <json-schema-validator>!
        given()
                .spec(requestSpecification())
                .get("/api/unknown/2")
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("GETSingleColorSchema.json"));
    }

    @Test(dataProvider = "ColorDataProviderArray", dataProviderClass = ColorDataProvider.class)
    public void checkColorsByID(ColorDTO colorExpected) {
        /*проверка разных цветов по выбранным ID (например, по классам эквивалентности)
        с использованием DataProvider*/
        ColorDTO colorJson = given()
                .spec(requestSpecification())
                .get("/api/unknown/" + colorExpected.getId())
                .then()
                .extract()
                .jsonPath()
                .getObject("data", ColorDTO.class);

        Assert.assertEquals(colorJson, colorExpected);
    }


}

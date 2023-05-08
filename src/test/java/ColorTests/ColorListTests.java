package ColorTests;

import DTO.ColorListInfoDTO;
import DTO.ColorListInfoDTOBuilder;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Specifications.Specifications.requestSpecification;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class ColorListTests {

    ColorListInfoDTO listInfoExpected;
    ColorListInfoDTO listInfoJSON;
    Response resp;

    @BeforeClass
    public void setupParams(){
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

        System.out.println(listInfoJSON.toString());

    }

    @Test
    public void checkListColorPageAttribute(){
        
        Assert.assertEquals(listInfoExpected.getPage(), listInfoJSON.getPage(), "\"page\" not equal!");
        Assert.assertEquals(listInfoExpected.getPerPage(), listInfoJSON.getPerPage(), "\"per_page\" not equal!");
    }

}

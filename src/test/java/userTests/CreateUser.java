package userTests;

import dto.user.UserRequestDTO;
import dto.user.UserResponseDTO;
import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import specifications.Specifications;

import static constants.Constants.UserConstants.API_URL;
import static constants.Constants.UserConstants.RESPONSE_TIMEOUT;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUser {

    UserRequestDTO userRequest;
    @BeforeClass
    public void setupParams(){
        userRequest = new UserRequestDTO().builder()
                .name("morpheus11")
                .job("leader22")
                .build();
    }
    @Test(description = "3.1 Проверка, что атрибуты созданного User совпадают с исходными")
    public void checkUserName(){

        var userResponce = given()
                .spec(Specifications.requestSpecification())
                .body(userRequest)
                .post(API_URL)
                .then().log().all()
                .extract().as(UserResponseDTO.class);

        Assert.assertEquals(userResponce.getName(), userRequest.getName());
        Assert.assertEquals(userResponce.getJob(), userRequest.getJob());
    }

    @Test(description = "3.2 Проверка времени ответа ResponseTime")
    @Description("Проверка времени ответа API. Не должно превышать 10 секунд")
    public void checkCreateUserResponseTime(){
        //примеры есть тут http://makeseleniumeasy.com/2020/02/05/rest-assured-tutorial-21-get-assert-response-time-of-request/
        given()
                .spec(Specifications.requestSpecification())
                .body(userRequest)
                .post(API_URL)
                .then()
                .assertThat()
                .time(Matchers.lessThanOrEqualTo(RESPONSE_TIMEOUT));
    }


}

package userTests;

import dto.user.UserRequestDTO;
import dto.user.UserResponseDTO;
import org.testng.Assert;
import org.testng.annotations.Test;
import specifications.Specifications;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUser {

    @Test
    public void checkUserName(){
        //3.1 Проверка, что атрибуты созданного User совпадают с исходными
        var userRequest = new UserRequestDTO().builder()
                .name("morpheus11")
                .job("leader22")
                .build();


        var userResponce = given()
                .spec(Specifications.requestSpecification())
                .body(userRequest)
                .post("/api/users/6")
                .then().log().all()
                .extract().as(UserResponseDTO.class);

        Assert.assertEquals(userResponce.getName(), userRequest.getName());
        Assert.assertEquals(userResponce.getJob(), userRequest.getJob());
    }

}

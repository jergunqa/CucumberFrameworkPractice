package com.hrms.api.Testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.*;

//given()
//when()
//then()

public class hardCodedExamples {
    @Test
    public void sampleTest() {
        RestAssured.baseURI = "http://3.237.189.167/syntaxapi/api";
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTM2MTU4MzksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYxMzY1OTAzOSwidXNlcklkIjoiMjM4OSJ9.RRB_bRVoe478jNE-5PyAKWIhRKogeUYncCHSWh-lFmI";
        //presparing request to get one employee
        RequestSpecification preparingGetOneEmployeeRequest = given().header("Authorization", token)
                .header("Content-Type", "Application/json")
                .queryParam("employee_id", "13001");
        //sending the request to the endpoint
        Response getOneEmployeeResponse = preparingGetOneEmployeeRequest.when().get("/getOneEmployee.php");
        //print the response
       // System.out.println(getOneEmployeeResponse.asString());
        getOneEmployeeResponse.prettyPrint();

        //Assert that status code  is 200
        getOneEmployeeResponse.then().assertThat().statusCode(200);

    }
}

package com.hrms.api.Testing;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

//given()
//when()
//then()

public class hardCodedExamples2 {

       String baseURI = RestAssured.baseURI = "http://3.237.189.167/syntaxapi/api";
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTQxMzQyMTIsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYxNDE3NzQxMiwidXNlcklkIjoiMjM4OSJ9.NEYbS1Qdn5QWvq8Ae73uFDRDxc32Kq9nsursRvDgEKw";

        @Test
    public void sampleTest(){

        }
        @Test
    public void aPostCreateEmployee(){
            //Preparing Create an Employee Request
            RequestSpecification createEmployeeRequest = given().header("Authorization",token)
                    .header("Content-Type","Application/json").body("{\n" +
                    "  \"emp_firstname\": \"Mariii\",\n" +
                    "  \"emp_lastname\": \"Romaniuk\",\n" +
                    "  \"emp_middle_name\": null,\n" +
                    "  \"emp_gender\": \"F\",\n" +
                    "  \"emp_birthday\": \"2021-02-11\",\n" +
                    "  \"emp_status\": \"employee\",\n" +
                    "  \"emp_job_title\": \"IT Analyst\"\n" +
                    "}");

            //Making a Post call to Create Employee
           Response createEmployeeResponse =createEmployeeRequest.when().post("/createEmployee.php");
           //Printing the Employee ID
           createEmployeeResponse.prettyPrint();
           //Assert that status code is 201
           createEmployeeResponse.then().assertThat().statusCode(201);
           //Get Employee_id
           String employeeID=createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");
           //Print EmployeeId
           System.out.println(employeeID);
           //Assert that message contains Entry Created
           createEmployeeResponse.then().assertThat().body("Message",equalTo("Entry Created"));
           //Assert that Employee Id is 15845A
            createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname",equalTo("Mariii"));
        }
        @Test
        public void aPostCretaeEmployee(){

        }

    @Test
    public void bgetCreatedEmployee(){
        RequestSpecification getCreatedEmployee = given().header("Authorization", token)
                .header("Content-Type", "Application/json").queryParam("15900A");

        Response getEmployeeResponse = getCreatedEmployee.when().get("/getOneEmployee.php");

        //getEmployeeResponse.prettyPrint();
       String empID=(getEmployeeResponse.body().jsonPath().getString("Employee[0].employee_id"));

       //we are checking if the empID is equal to the one mentioned in String
        boolean VerifyEmployeeID = empID.equalsIgnoreCase("15900A");
        System.out.println(VerifyEmployeeID);

       // Assert.assertTrue(VerifyEmployeeID);

    }
    @Test
    public void cUpdateEmployee(){
        RequestSpecification updateEmployeeRequest = given().header("Authorization", token)
                .header("Content-Type", "Application/json").body("{\n" +
                        "  \"employee_id\": \"16037A\",\n" +
                        "  \"emp_firstname\": \"ABC\",\n" +
                        "  \"emp_lastname\": \"Romaniuk\",\n" +
                        "  \"emp_middle_name\": null,\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2021-02-11\",\n" +
                        "  \"emp_status\": \"employee\",\n" +
                        "  \"emp_job_title\": \"IT Analyst\"\n" +
                        "}");
        Response UpdateEmployeeReponse = updateEmployeeRequest.when().put("/updateEmployee.php");
        UpdateEmployeeReponse.prettyPrint();

        //assert that body contains the message entery updated

       
    }
}

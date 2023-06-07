package stepdefinitions.e2e_test;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojos.RoomPojo;

import static base_urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static stepdefinitions.e2e_test.MedunnaRoomStepDefinitions.roomId;
import static stepdefinitions.e2e_test.MedunnaRoomStepDefinitions.roomNummerFaker;

public class ApiRoomStepDefinitions {

    Response response;
    RoomPojo expectedData;

    @Given("send get request to url")
    public void send_get_request_to_url() {
        //Set the url--> https://medunna.com/api/rooms?sort=createdDate,desc
        spec.pathParams("first", "api", "second", "rooms")
                .queryParams("sort", "createdDate,desc");

        //Set the expected data

        //Send the request and get the response
        response = given(spec).get("{first}/{second}");
        //response.prettyPrint();

    }

    @When("validate body")
    public void validate_body() {

        //Do assertion
        Object actualRoomtype = response.jsonPath().getList("findAll{it.roomNumber=" + roomNummerFaker + "}.roomType").get(0); //Gelen body icinden bizim olusturdugumuz odanin numarasi ile filtreleme yapiyoruz.
        Object actualStatus = response.jsonPath().getList("findAll{it.roomNumber=" + roomNummerFaker + "}.status").get(0);//Filtrelenen bodyden gerekli datayi nokta sonrasina belirterek(glue language) aliyoruz.
        Object actualPrice = response.jsonPath().getList("findAll{it.roomNumber=" + roomNummerFaker + "}.price").get(0);
        Object actualDescription = response.jsonPath().getList("findAll{it.roomNumber=" + roomNummerFaker + "}.description").get(0);
        Object actualRoomNumber = response.jsonPath().getList("findAll{it.roomNumber=" + roomNummerFaker + "}.roomNumber").get(0);

        System.out.println("roomNummerFaker = " + roomNummerFaker);

        assertEquals("PREMIUM_DELUXE", actualRoomtype);
        assertEquals(true, actualStatus);
        assertEquals("123.0", actualPrice + "");
        assertEquals("Created For End To End Test", actualDescription);
        assertEquals(roomNummerFaker, actualRoomNumber);


    }

    @Given("send get request by id")
    public void sendGetRequestById() {
        //SEt the url
        spec.pathParams("first","api","second","rooms","third",roomId);

        //Set the expected data
         expectedData=new RoomPojo(roomNummerFaker,"PREMIUM_DELUXE",true,123.00,"Created For End To End Test");
        System.out.println("expectedData = " + expectedData);

        //SEnd the request and get the expected data
        response=given(spec).get("{first}/{second}/{third}");
        response.prettyPrint();
    }

    @When("validate response body")
    public void validateResponseBody() throws JsonProcessingException {
        RoomPojo actualData = new ObjectMapper().readValue(response.asString(), RoomPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getRoomNumber(),actualData.getRoomNumber());
        assertEquals(expectedData.getRoomType(),actualData.getRoomType());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getPrice(),actualData.getPrice());
        assertEquals(expectedData.getDescription(),actualData.getDescription());

    }
}

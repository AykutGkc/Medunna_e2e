package stepdefinitions.api_stepsdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static base_urls.ManagementOnSchoolsBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class US01_APIStepDefs {
    Response response;

    @Given("tum guest userlar icin get request yap")
    public void tum_guest_userlar_icin_get_request_yap() {
        //Set the url
       //https://managementonschools.com/app/guestUser/getAll?size=1000
        spec.pathParams("first","guestUser","second","getAll").queryParam("size","1000");

        //Send the request and get  the response
        //Send the request and get the response
         response= given(spec).get("{first}/{second}");
        //response.prettyPrint();

    }

    @Then("gelen bodyi dogrula {string}, {string}, {string}, {string}, {string}, {string}, {string},{string}")
    public void gelen_bodyi_dogrula(String name, String surname, String birthplace, String phone, String gender, String dateOfBirth, String ssn , String username) {

        JsonPath jsonPath=response.jsonPath();
        String actName=jsonPath.getList("content.findAll{it.username=='"+username+"'}.name").get(0).toString();
        String actUserName=jsonPath.getList("content.findAll{it.username=='"+username+"'}.username").get(0).toString();
        String actSsn=jsonPath.getList("content.findAll{it.username=='"+username+"'}.ssn").get(0).toString();
        String actSurname=jsonPath.getList("content.findAll{it.username=='"+username+"'}.surname").get(0).toString();
        String actBirthPlace=jsonPath.getList("content.findAll{it.username=='"+username+"'}.birthPlace").get(0).toString();
        String actPhone=jsonPath.getList("content.findAll{it.username=='"+username+"'}.phoneNumber").get(0).toString();
        String actGender=jsonPath.getList("content.findAll{it.username=='"+username+"'}.gender").get(0).toString();
        String actBirthDay=jsonPath.getList("content.findAll{it.username=='"+username+"'}.birthDay").get(0).toString();

        assertEquals(name,actName);
        assertEquals(username,actUserName);
        assertEquals(ssn,actSsn);
        assertEquals(surname,actSurname);
        assertEquals(birthplace,actBirthPlace);
        assertEquals(phone,actPhone);
        assertEquals(gender,actGender);
        assertEquals(dateOfBirth,actBirthDay);


    }

    @Then("username {string} ile yapilan filtrelemenin bos oldugunu dogrula")
    public void usernameIleYapilanFiltrelemeninBosOldugunuDogrula(String username) {

        int size=response.jsonPath().getList("content.findAll{it.username=='"+username+"'}").size();
        assertEquals(0,size);

    }
}

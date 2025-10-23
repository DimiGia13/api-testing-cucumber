package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BookingSteps {

    private Response response;

    @Given("the Booking API is available")
    public void theBookingAPIIsAvailable(){
        RestAssured.baseURI = "https://automationintesting.online/";
    }

    //region GET booking scenario
    @When("^I send a GET request to /booking$")
    public void iSendAGetRequestToBooking() {
        response = when().get("/booking");
    }

    @Then("the response status code should be 200")
    public void theResponseStatusCodeShouldBe200(int statusCode){
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain at least one booking")
    public void theResponseShouldContainAtLeastOneBooking() {
        response.then().body("size()", greaterThan(0));
    }
    //endregion

    //region POST booking scenario
    @When("^I send a POST request to /booking with valid booking details$")
    public void iSendAPostRequestToBookingWithValidData(){
        String payload = """
      {
        "firstname": "John",
        "lastname": "Doe",
        "totalprice": 123,
        "depositpaid": true,
        "bookingdates": {
          "checkin": "2025-11-01",
          "checkout": "2025-11-05"
        },
        "additionalneeds": "Breakfast"
      }
    """;

        response =
                given()
                        .header("Content-Type", "appllication/json")
                        .body(payload)
                        .when()
                        .post("/booking")
                        .then()
                        .extract().response();

    }

    @Then("the response should contain the booking id")
    public void theResponseShouldContainTheBookingId() {
        response.then().body("bookingId", notNullValue());
    }
    //endregion

}

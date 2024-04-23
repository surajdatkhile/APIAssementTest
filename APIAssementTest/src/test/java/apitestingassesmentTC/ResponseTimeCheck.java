package apiTestingTC;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static org.hamcrest.Matchers.*;
public class ApiResponseTime {

	@Test
    public void checkApiResponseTimeIsWithinRange() {
        // Get the current time in seconds before making the API call
        long currentTimestampBeforeApiCall = System.currentTimeMillis() / 1000; // current time in seconds

        // API Endpoint
        String baseURL = "https://open.er-api.com/v6/latest/USD"; // replace with your real API endpoint

        // Make the API call and store the response
        Response response = given().relaxedHTTPSValidation()
            .when()
                .get(baseURL);

    // Check that the response time is not less than 3 seconds from the current time
    ValidatableResponse respTime = response.then();
    respTime.time(Matchers.greaterThan(3000L)); 	

	}
}

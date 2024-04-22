package apitestingassesmentTC;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static org.hamcrest.Matchers.*;
public class ResponseTimeCheck {

	 @Test
	    public void checkApiResponseTimeIsWithinRange() {
	        // Get the current time in seconds before making the API call
	        long currentTimestampBeforeApiCall = System.currentTimeMillis() / 1000; // current time in seconds

	        // API Endpoint
	        String baseURL = "https://open.er-api.com/v6/latest/USD"; // replace with your real API endpoint

	        // Make the API call and store the response
	        Response response = given()
	            .when()
	                .get(baseURL);

	        // Capture the API response timestamp from the body
	        JsonPath jsonPathEvaluator = response.jsonPath();
	        long apiResponseTimestamp = jsonPathEvaluator.getLong("timestamp");

	        // Calculate the difference in seconds
	        long differenceInSeconds = apiResponseTimestamp - currentTimestampBeforeApiCall;

	        // Check that the response time is not less than 3 seconds from the current time
	        Assert.assertTrue(differenceInSeconds >= 3, "The API response timestamp was less than 3 seconds from the sent request time.");
	        
	        // ... perform other response validations, if necessary.
	    }
	}

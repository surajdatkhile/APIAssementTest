package apitestingassesmentTC;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static org.hamcrest.Matchers.*;

public class CurrencyPairVal {
	 @Test
	    public void checkNumberOfCurrencyPairs() {
	        // API Endpoint for retrieving currency pairs
	        String baseURL = "https://open.er-api.com/v6/latest/USD"; // Replace with the actual API endpoint

	        given()
	            .when()
	                .get(baseURL)
	            .then()
	                .statusCode(200)                                // Ensure that the API call is successful
	                .body("rates", aMapWithSize(162));             // Assert that the 'rates' object contains 162 entries
	    }
	}
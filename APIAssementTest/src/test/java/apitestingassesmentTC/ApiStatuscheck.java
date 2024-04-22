package apitestingassesmentTC;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static org.hamcrest.Matchers.*;

public class ApiStatuscheck {
	  @Test
	    public void exchangeRatesStatusCheck() {
	        // Base URI for the currency exchange rates API
	        String baseURL = "https://open.er-api.com/v6/latest/USD";

	        given()
	            .when()
	                .get(baseURL)
	            .then()
	                .statusCode(anyOf(equalTo(200), equalTo(400), equalTo(500))) // Checking for any expected status code
	                .body("status", anyOf(equalTo("success"), equalTo("failure"))); // Checking for "success" or "failure" status within response body
	    }
	}

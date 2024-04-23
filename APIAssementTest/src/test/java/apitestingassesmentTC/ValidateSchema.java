package apiTestingTC;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static org.hamcrest.Matchers.*;

public class ValidateSchema {
	 @Test
	  public void responseShouldMatchSchema() {
	    // API endpoint
	    String baseURL = "https://open.er-api.com/v6/latest/USD";
	    
	    // Path to the schema file in classpath
	    String jsonSchemaPath = "schema.json";
	    
	    given()
	      .when()
	        .get(baseURL)
	      .then()
	        .assertThat()
	        .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/Resource/TestData/schema.json")));
	  }
	}

package swaggerApi;

import java.io.File;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
public class morePetStoreTests {
	int petID;
	Response response;
	
  @BeforeTest
  public void setup() {
	  RestAssured.baseURI ="https://petstore.swagger.io/v2";
  }
	
  @Test
  public void case1() {
//	  Perform a POST request to create a pet with id 3465xxx (change the xxx to numbers).
//	  Give the pet a name booboo in the request body.
//	  Status is available. 
//	  vaildate the mentioned above
	  File requestBody = new File("./src/test/resources/TestData/ApiData/updatePet2.json");
	  response = RestAssured
		.given().accept(ContentType.JSON).contentType("application/json")
		.body(requestBody).when().post("/pet");
	  
	  response.then().assertThat().statusCode(200).and().contentType("application/json")
	  .and().body("id", equalTo(3465123))
	  .and().body("name", equalTo("booboo"));
	  
	  petID = response.body().path("id");
  }
  
  @Test
  public void case2() {
//	  Perform a GET request to find a pet with id 3465xxx (xxx your number from above post request)
//	  Verify the status code is 200 
//	  Verify the content type is application.json
//	  Verify the pet name is booboo status is available
	 
	  response = RestAssured
			  .given().accept(ContentType.JSON)
			  .when().get("/pet/"+petID);
	  
	  response.then()
	  .assertThat().statusCode(200).and().contentType("application/json")
	  .and().body("name", equalTo("booboo"))
	  .and().body("status", equalTo("available"));
	  
  }
  
  @Test
  public void case3() {
//	  Perform a GET request to find a pet with ID 7867864
//	  Verify the status code is 404 and content type is application.json
//	  Verify message is Pet not found
	  
	  response = RestAssured
	  .given().accept(ContentType.JSON).when().get("/pet/7867864");
	  
	  response.then().assertThat().statusCode(404).and().contentType("application/json")
	  .and().body("message", equalTo("Pet not found"));
  }
  
  
}

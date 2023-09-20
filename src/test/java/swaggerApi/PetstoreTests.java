package swaggerApi;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetstoreTests {
	String categoryName;
	int petID;
	
  @BeforeTest
  public void setup() {
	  RestAssured.baseURI = "https://petstore.swagger.io/v2";
  }
	
  @Test
  public void findByStatus() {
	  //Send a request to find by status "pending",
	  //then validate status code is 200 and content type is application json
	  
	  Response response = RestAssured
			  .given().accept(ContentType.JSON).queryParam("status", "pending")
			  .when().get("/pet/findByStatus");
	  
	  response.then()
	  .assertThat().statusCode(200).and().assertThat().contentType("application/json");
	 
  }
  
  @Test
  public void addPet() {
	  //add a pet with the body provided,
	  //then assert the status code is 200 and content type is application/json
	  // assert that the pet's name is max
	  
		 String requestBody = "{\n"
		+ "  \"id\": 1234,\n"
		+ "  \"category\": {\n"
		+ "    \"id\": 21,\n"
		+ "    \"name\": \"string\"\n"
		+ "  },\n"
		+ "  \"name\": \"Max\",\n"
		+ "  \"photoUrls\": [\n"
		+ "    \"string\"\n"
		+ "  ],\n"
		+ "  \"tags\": [\n"
		+ "    {\n"
		+ "      \"id\": 0,\n"
		+ "      \"name\": \"string\"\n"
		+ "    },\n"
		+ "    {\n"
		+ "        \"id\": 1,\n"
		+ "        \"name\":\"String2\"\n"
		+ "    }\n"
		+ "  ],\n"
		+ "  \"status\": \"available\"\n"
		+ "}";
		 
		 Response response = RestAssured
				 .given().accept(ContentType.JSON)
				 .body(requestBody).contentType("application/json")
				 .when().post("/pet");
		 
		 response.prettyPrint();
		 
		 response.then()
		 .assertThat().statusCode(200).and().contentType("application/json")
		 .and().body("name", equalTo("Max"));
		 
		petID = response.body().jsonPath().get("id");
		categoryName = response.body().path("category.name");
		
		
	  
  }
  
  @Test(dependsOnMethods = "addPet")
  public void updatePet() {
	  //update the previously added pet by changing the category name to "dogs"
	  //then assert the status coded is 200 and the content type is a/j
	  //then assert the category name is updated to "dogs".
	  
	  File requestBody =new File("./src/test/resources/TestData/ApiData/updatePetAPI.json");
	  
	  Response response = RestAssured
			  .given().accept(ContentType.JSON).body(requestBody).contentType("application/json")
			  .when().put("/pet");
	  
	  response.then()
	  .assertThat().statusCode(200)
	  .and().contentType("application/json");
	  
	  response.prettyPrint();
	  
	  categoryName = response.body().path("category.name");
	  //assertEquals(categoryName, "dogs" );
	  response.then().assertThat().body("category.name", equalTo("dogs"));
	 
	  petID = response.body().path("id");
	  
  }
  
  
  @Test(dependsOnMethods = {"addPet", "updatePet"})
  public void getByID() {
	  //perform a request to get the pet previously updated
	  //then assert that the status code is 200 and the content type is a/j
	  //assert that the pet id is 1234
	  
	  Response response = RestAssured
			  .given().accept(ContentType.JSON)
			  .when().get("/pet/"+petID);
	  
	  response.then()
	  .assertThat().statusCode(200).and().contentType("application/json");
	  
	  response.prettyPrint();
	  
	  response.then().assertThat()
	  .body("tags.id[0]", equalTo(0))
	  .and().body("tags.id[1]", equalTo(1))
	  .and().body("status", equalTo("available"))
	  .and().body("category.id", equalTo(21));
	
	  
  }
  
  
  public void deletPet() {
	  //delete the pet created
	  //then assert the status code is 200 
	  //and assert the message is equal to the pet id deleted
	  
	  Response response = RestAssured
			  .given().accept(ContentType.JSON)
			  .when().delete("/pet/"+petID);
	  
	  response.then().assertThat()
	  .body("message", equalTo(String.valueOf(petID)));
  }
  
  @Test
  public void invalidUpdate() {
	  //send an update request with improper body
	  //then assert the status code is 400 and the message says: bad input
	  
	  String requestBody = "{\n"
	  		+ "  \"id\": 123456h,\n"
	  		+ "  \"category\": {\n"
	  		+ "    \"id\": 21,\n"
	  		+ "    \"name\": \"dogs\"\n"
	  		+ "  },\n"
	  		+ "  \"name\": \"Max\",\n"
	  		+ "  \"photoUrls\": [\n"
	  		+ "    \"string\"\n"
	  		+ "  ],\n"
	  		+ "  \"tags\": [\n"
	  		+ "    {\n"
	  		+ "      \"id\": 0,\n"
	  		+ "      \"name\": \"string\"\n"
	  		+ "    },\n"
	  		+ "    {\n"
	  		+ "        \"id\": 1,\n"
	  		+ "        \"name\":\"String2\"\n"
	  		+ "    }\n"
	  		+ "  ],\n"
	  		+ "  \"status\": \"available\"\n"
	  		+ "}";
	  
	  Response response = RestAssured
			  .given().accept(ContentType.JSON).contentType("application/json")
			  .body(requestBody).when().put("/pet");
	  
	  response.then()
	  .assertThat().statusCode(400).and().contentType("application/json")
	  .and().body("message", equalTo("bad input"));
	 
	  
	  
  }
  
  @Test
  public void cleanup() {
	  deletPet();
  }
}

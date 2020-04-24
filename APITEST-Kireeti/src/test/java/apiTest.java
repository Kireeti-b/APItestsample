import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class apiTest {

	@Test
	public void getRequest() {
		String apicall = "http://dummy.restapiexample.com/api/v1/employees";
		Response GETresponse = RestAssured.get(apicall);
		System.out.println("GET request response is : " + GETresponse.getStatusCode());
		Assert.assertEquals(GETresponse.getStatusCode(), 200, "Status code mismatch"); //Correct response
		//Assert.assertEquals(GETresponse.getStatusCode(), 210, "Status code mismatch"); //Negative case
		String responseStatus = GETresponse.jsonPath().get("status");
		Assert.assertEquals(responseStatus, "success", "Unable to get the data");
		Assert.assertEquals(GETresponse.getContentType(), "application/json;charset=utf-8", "Response is not of JSON format");
		}
	
	@Test
	public void postRequest() {
		String apicall = "http://dummy.restapiexample.com/api/v1/create";
		String contentType = "application/json";
		String payloadData = "{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}";
		
		Response POSTresponse = RestAssured.
				given()
				.contentType(contentType).body(payloadData)
				.when()
				.post(apicall)
				.then()
				.extract().response();
		
		Assert.assertEquals(POSTresponse.getStatusCode(), 200, "Data not posted");
		//Assert.assertEquals(POSTresponse.getStatusCode(), 220, "Status code is incorrect"); //Negative case 
		String responseStatus = POSTresponse.jsonPath().get("status");
		String employeeName = POSTresponse.jsonPath().get("data.name");
		String employeeSalary = POSTresponse.jsonPath().get("data.salary");
		String employeeAge = POSTresponse.jsonPath().get("data.age");
		Assert.assertEquals(responseStatus, "success", "response incorrect");
		Assert.assertEquals(employeeName, "test", "Response not as expected");
		Assert.assertEquals(employeeSalary, "123", "Salary is incorrect");
		Assert.assertEquals(employeeAge, "23", "Agen is incorrect");
		Assert.assertEquals(POSTresponse.getContentType(), "application/json;charset=utf-8", "Response is not of JSON format");
		System.out.println("POST request response is : " + POSTresponse.getStatusCode());
		}
	
	@Test
	public void putRequest() {
		String apicall = "http://dummy.restapiexample.com/api/v1/update/21";
		String contentType = "application/json";
		String payloadData = "{\"name\":\"test1\",\"salary\":\"123\",\"age\":\"23\"}";
		
		Response PUTresponse = RestAssured.
				given()
				.contentType(contentType).body(payloadData)
				.when()
				.put(apicall)
				.then()
				.extract().response();
	
		Assert.assertEquals(PUTresponse.getStatusCode(), 200, "Reponse incorrect");
		//Assert.assertEquals(PUTresponse.getStatusCode(), 220, "Status code is incorrect"); //Negative case 
		String responseStatus = PUTresponse.jsonPath().get("status");
		Assert.assertEquals(responseStatus, "success", "response incorrect");
		Assert.assertEquals(PUTresponse.getContentType(), "application/json;charset=utf-8", "Response is not of JSON format");
		System.out.println("PUT request response is : " + PUTresponse.getStatusCode());
		}

	@Test
	public void deleteRequest() {
		String apicall = "http://dummy.restapiexample.com/api/v1/delete/2";
		
		Response DELETEresponse = RestAssured.
				when()
				.delete(apicall)
				.then()
				.extract().response();
		
		Assert.assertEquals(DELETEresponse.getStatusCode(), 200, "Status code is incorrect");
		//Assert.assertEquals(DELETEresponse.getStatusCode(), 220, "Status code is incorrect"); //Negative case 
		System.out.println("DELETE request response is : " + DELETEresponse.getStatusCode());
		String responseStatus = DELETEresponse.jsonPath().get("status");
		Assert.assertEquals(responseStatus, "success", "response incorrect"); //Request should respond with success, instead request is failing.
		Assert.assertEquals(DELETEresponse.getContentType(), "application/json;charset=utf-8", "Response is not of JSON format");
	}
}


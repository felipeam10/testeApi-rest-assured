package br.com.thecat.TesteApi;

import org.junit.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TesteApi {

	private String vote_id;
	
	@Test
	public void cadastro() {
		
		String url = "https://api.thecatapi.com/v1/user/passwordlesssignup";
		String body = "{\"email\": \"felipeam10@hotmail.com\", \"appDescription\": \"test with rest-assured II\"}";
		
		Response response = given().contentType("application/json").body(body).when().post(url);
		response.then().body("message", containsString("SUCCESS")).statusCode(200);
		System.out.println(response.body().asString());
		
	}

	@Test
	public void likeOrDeslike() {
		
		String url = "https://api.thecatapi.com/v1/votes/";
		
		Response response = given().contentType("application/json").body("{\"image_id\": \"6ig\", \"value\": \"true\", \"sub_id\": \"demo-85a33d\"}").when().post(url);
		response.then().body("message", containsString("SUCCESS")).statusCode(200);
		System.out.println(response.body().asString());
		String id = response.jsonPath().getString("id");
		vote_id = id;
		System.out.println(id);
		
	}

	@Test
	public void deleteLike() {
		likeOrDeslike();
		deletaVoto();
		
	}

	private void deletaVoto() {
		
		String url = "https://api.thecatapi.com/v1/votes/{vote_id}";
		
		Response response = given()
				.contentType("application/json")
				.header("x-api-key", "fcd193da-518b-4971-a2ce-3561c770e323")
				.pathParam("vote_id", vote_id).when()
				.delete(url);
		
		System.out.println(response.body().asString());
		response.then().body("message", containsString("SUCCESS")).statusCode(200);
		
	}
}


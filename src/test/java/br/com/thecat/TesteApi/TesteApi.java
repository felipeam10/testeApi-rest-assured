package br.com.thecat.TesteApi;

import org.junit.Test;

import static io.restassured.RestAssured.*;


public class TesteApi {

	@Test
	public void cadastro() {
		
		String url = "https://api.thecatapi.com/v1/user/passwordlesssignup";
		String body = "{\"email\": \"felipeam10@hotmail.com\", \"appDescription\": \"test with rest-assured II\"}";
		
		given().contentType("application/json").body(body)
		.when().post(url)
		.then().statusCode(200);
	}
}


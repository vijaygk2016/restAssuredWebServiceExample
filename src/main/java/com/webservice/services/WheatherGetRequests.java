package com.webservice.services;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class WheatherGetRequests {

	// Simple get request for getting wheather request by City name
	// Status Code : 200
	// @Test
	public void Test_01() {

		Response resp = when().get(
				"http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=e34be7c3ce98bd73a80e27458cf55f7b");
		System.out.println("resp code : " + resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
	}

	// Status Code : 401
	// @Test
	public void Test_02() {

		Response resp = when().get(
				"http://api.openweathermap.org/data/2.5/weather?q=Bijapur,uk&appid=e34be7c3ce98bd73a80e27458cf55f7b");
		System.out.println("resp code : " + resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 401);
	}

	// How to use parameeter with rest assured
	// @Test
	public void Test_03() {

		Response resp = given().param("q", "London").param("appid", "e34be7c3ce98bd73a80e27458cf55f7b").when()
				.get("http://api.openweathermap.org/data/2.5/weather");
		System.out.println("resp code : " + resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
	}

	// Assest our testcase in Rest assured api
	// @Test
	public void Test_04() {

		given().param("q", "London").param("appid", "e34be7c3ce98bd73a80e27458cf55f7b").when()
				.get("http://api.openweathermap.org/data/2.5/weather").then().assertThat().statusCode(200);

	}

	// @Test
	public void Test_05() {

		Response resp = given().param("q", "London").param("appid", "e34be7c3ce98bd73a80e27458cf55f7b").when()
				.get("http://api.openweathermap.org/data/2.5/weather");
		System.out.println(resp.asString());

	}

	// @Test
	public void Test_06() {
		Response resp = given().param("id", "2172797").param("appid", "e34be7c3ce98bd73a80e27458cf55f7b").when()
				.get("http://api.openweathermap.org/data/2.5/weather");
		System.out.println("resp code : " + resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
	}

	// @Test
	public void Test_07() {
		Response resp = given().param("zip", "201010,in").param("appid", "e34be7c3ce98bd73a80e27458cf55f7b").when()
				.get("http://api.openweathermap.org/data/2.5/weather");
		System.out.println("resp code : " + resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
	}

	// @Test
	public void Test_08() {
		String resp = given().param("id", "2172797").param("appid", "e34be7c3ce98bd73a80e27458cf55f7b").when()
				.get("http://api.openweathermap.org/data/2.5/weather").then().contentType(ContentType.JSON).extract()
				.path("weather[0].description");

		System.out.println("weather : " + resp);

	}

	// @Test
	public void Test_09() {
		Response resp = given().param("id", "2172797").param("appid", "e34be7c3ce98bd73a80e27458cf55f7b").when()
				.get("http://api.openweathermap.org/data/2.5/weather");

		String actualWeatherReport = resp.then().contentType(ContentType.JSON).extract().path("weather[0].description");
		System.out.println("actualWeatherReport : " + actualWeatherReport);

	}

	@Test
	public void Test_10() {
		Response resp = given().param("id", "2172797").param("appid", "e34be7c3ce98bd73a80e27458cf55f7b").when()
				.get("http://api.openweathermap.org/data/2.5/weather");

		String actualWeatherReport = resp.then().contentType(ContentType.JSON).extract().path("weather[0].description");
		System.out.println("weather description by id : " + actualWeatherReport);

		Float lon = resp.then().contentType(ContentType.JSON).extract().path("coord.lon");
		System.out.println("Longitude is : " + lon);

		Float lat = resp.then().contentType(ContentType.JSON).extract().path("coord.lat");
		System.out.println("Longitude is : " + lat);

		Response report = given().param("lat", lat).param("lat", lon).param("lon", "e34be7c3ce98bd73a80e27458cf55f7b")
				.when().get("http://api.openweathermap.org/data/2.5/weather").then().contentType(ContentType.JSON)
				.extract().path("weather[0].description");

		System.out.println("report is : " + report);
	}

}

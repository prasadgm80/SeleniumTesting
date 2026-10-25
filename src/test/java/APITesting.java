import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APITesting {

    public static String AddPlace() {

        return "{\r\n"
                + "  \"location\": {\r\n"
                + "    \"lat\": -38.383494,\r\n"
                + "    \"lng\": 33.427362\r\n"
                + "  },\r\n"
                + "  \"accuracy\": 50,\r\n"
                + "  \"name\": \"Frontline house\",\r\n"
                + "  \"phone_number\": \"(+91) 1233214568\",\r\n"
                + "  \"address\": \"29, side layout, Gangakhed 09\",\r\n"
                + "  \"types\": [\r\n"
                + "    \"shoe park\",\r\n"
                + "    \"shop\"\r\n"
                + "  ],\r\n"
                + "  \"website\": \"https://rahulshettyacademy.com\",\r\n"
                + "  \"language\": \"French-IN\"\r\n"
                + "}\r\n"
                + "";

    }

    @Test
    public void testAPI() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().all().queryParam("Key", "qaclick123").
                header("Content-Type", "application/json")
                .body(APITesting.AddPlace()).when().post("maps/api/place/add/json")
                .then().extract().response().asString();


//        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String place_id = js.getString("place_id");
        System.out.println(place_id);
        Assert.assertNotNull(place_id);
    }

    @Test
    public void verifyComplexJSON() {
        RestAssured.baseURI = "https://reqres.in";
        String output = given()
                .header("Content-Type", "application/json").queryParam("page", "2").
                body("")
                .when().get("/api/users")
                .then().extract().response()
                .asString();
//        System.out.println(output);

        JsonPath js = new JsonPath(output);
        List<String> ids = js.getList("data.id");
//        System.out.println(ids);

        for (int i = 0; i < js.getList("data").size(); i++) {
            String email = js.get("data[" + i + "].email");
            String firstName = js.get("data[" + i + "].first_name");
            boolean isEmailVerified = email.contains(firstName.toLowerCase());
            Assert.assertTrue(isEmailVerified);
            System.out.println("email: " + email);
        }
    }

    @Test
    public void verifyComplexJSONWithJSONObject() {
        RestAssured.baseURI = "https://reqres.in";
        String output = given().log().all()
                .header("Content-Type", "application/json").queryParam("page", "2").
                body("")
                .when().get("/api/users").then().assertThat().statusCode(200)
                .extract().response().asString();
//        System.out.println(output);

        JSONObject js = new JSONObject(output);
        int pages = js.getInt("total");
        System.out.println(pages);
        JSONArray data = js.getJSONArray("data");

        for (int i = 0; i < data.length(); i++) {
            JSONObject dt = data.getJSONObject(i);
            String email = dt.getString("email");
            String first_name = dt.getString("first_name");
            String last_name = dt.getString("last_name");
            String expectedEmail = first_name.toLowerCase()+"."+last_name.toLowerCase()+"@reqres.in";

            boolean isEmailVerified = email.contains(first_name.toLowerCase()) && email.contains(last_name.toLowerCase());
            Assert.assertTrue(isEmailVerified);
            Assert.assertEquals(email, expectedEmail);
            System.out.println("email: " + email);
        }
    }

    @Test
    public void getEvents()
    {
        String loginBody = "{\n" +
                "  \"email\": \"prasadgm80@gmail.com\",\n" +
                "  \"password\": \"Vitmechu33@\"\n" +
                "}";

        RestAssured.baseURI = "https://api.eventhub.rahulshettyacademy.com";

        String response = given().log().all()
                .header("Content-Type","application/json").body(loginBody).when()
                .post("/api/auth/login").then().log().all().extract().response().asString();

        JsonPath js = new JsonPath(response);
        String token = js.getString("token");

        String response1 = given().log().all().header("Authorization", "Bearer "+token)
                .header("accept","application/json")
                .queryParam("category", "Conference").
                queryParam("city", "Bangalore").queryParam("search", "summit")
                .queryParam("page","1").queryParam("limit","10")
                .when().get("api/events")
                .then().extract().response().asString();
        System.out.println(response1);
    }

    @Test
    public void createEvents()
    {
        String actualMessage = "Event created successfully";
        String actualCity = "Bangalore";
        String body ="{\n" +
                "  \"title\": \"Tech Summit 2026\",\n" +
                "  \"description\": \"A premier technology conference.\",\n" +
                "  \"category\": \"Conference\",\n" +
                "  \"venue\": \"Bangalore International Centre\",\n" +
                "  \"city\": \""+actualCity+"\",\n" +
                "  \"eventDate\": \"2026-11-15T09:00:00.000Z\",\n" +
                "  \"price\": 1500,\n" +
                "  \"totalSeats\": 500,\n" +
                "  \"imageUrl\": \"https://example.com/banner.jpg\"\n" +
                "}";

        String loginBody = "{\n" +
                "  \"email\": \"prasadgm80@gmail.com\",\n" +
                "  \"password\": \"Vitmechu33@\"\n" +
                "}";
        RestAssured.baseURI = "https://api.eventhub.rahulshettyacademy.com";

        String response = given().log().all().header("Authorization", "Bearer 112233")
                .header("Content-Type","application/json").body(loginBody).when()
                .post("/api/auth/login").then().log().all().extract().response().asString();

        JsonPath js = new JsonPath(response);
        String token = js.getString("token");

        String response1 = given().log().all().header("Authorization", "Bearer "+token)
                .header("Content-Type","application/json").
                header("accept","application/json")
                .body(body).when().post("api/events").
                then().log().all().extract().response().asString();

        JsonPath jsp = new JsonPath(response1);
        boolean status = jsp.getBoolean("success");
        String message = jsp.getString("message");
        String city = jsp.getString("data.city");

        Assert.assertEquals(actualMessage,message);
        Assert.assertEquals(actualCity, city);
        Assert.assertTrue(status);
    }
}

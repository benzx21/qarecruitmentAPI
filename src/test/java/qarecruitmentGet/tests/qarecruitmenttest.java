package qarecruitmentGet.tests;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class qarecruitmenttest {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;

    @BeforeClass
    public void beforeClass() {


        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://qarecruitment.azurewebsites.net").
                setBasePath("/v1");
        requestSpecification = requestSpecBuilder.build();
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200);
        responseSpecBuilder.log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }
    //Test to retrieve the product from the server and validate the response
    @Test
    public void shouldBeAbleToGetOneProduct() {
        given(requestSpecification).contentType(ContentType.JSON).
                accept("application/json").
                when().get("/product/0d071c78-6563-4d59-9237-8caa6b3386a5").
                then().spec(responseSpecification).
                assertThat().
                statusCode(200).
                body("name", equalTo("Noodles"));


    }

//Test to delete the product from the server
    @Test
    public void shouldBeAbleToDelete() {
        given(requestSpecification).contentType(ContentType.JSON).
                accept("application/json").
                when().delete("/product/08c73069-dc33-44f0-8c2f-9ec9b0bb8cbc").
                then().spec(responseSpecification).
                assertThat().statusCode(200);


    }

}


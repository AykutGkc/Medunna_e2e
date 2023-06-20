package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utilities.ManagementOnSchoolAuthorization.generateToken;

public class ManagementOnSchoolsBaseUrl {
    public static RequestSpecification spec;

    @Before
    public static void setUp2() throws Exception {
        spec=new RequestSpecBuilder()
                .addHeader("Authorization",generateToken())
                .setBaseUri("https://managementonschools.com/app")
                .setContentType(ContentType.JSON)
                .build();

    }
}

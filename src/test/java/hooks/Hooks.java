package hooks;

import io.cucumber.java.Before;

import static base_urls.MedunnaBaseUrl.setUp;

public class Hooks {

    @Before() //sadece Parantez icine belirtilen tag senaryolari öncesi calisir.
    public void beforeApi(){

        setUp();

    }


}

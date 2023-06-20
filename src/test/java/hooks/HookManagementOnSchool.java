package hooks;

import io.cucumber.java.Before;

import static base_urls.ManagementOnSchoolsBaseUrl.setUp2;

public class HookManagementOnSchool {
    @Before //import io.cucumber.java.Before;
    public void before() throws Exception {
        setUp2();
    }
}

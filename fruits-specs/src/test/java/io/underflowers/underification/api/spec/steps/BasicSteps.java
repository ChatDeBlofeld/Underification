package io.underflowers.underification.api.spec.steps;

import io.underflowers.underification.ApiException;
import io.underflowers.underification.ApiResponse;
import io.underflowers.underification.api.DefaultApi;
import io.underflowers.underification.api.spec.helpers.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BasicSteps {

    private Environment environment;
    private DefaultApi api;

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private String lastReceivedLocationHeader;

    public BasicSteps(Environment environment) {
        this.environment = environment;
        this.api = environment.getApi();
    }

    @Given("the API server is running")
    public void the_API_server_id_running() throws Throwable {
        assertNotNull(api);
    }

    @Then("I receive a {int} status code")
    public void i_receive_a_status_code(int expectedStatusCode) throws Throwable {
        assertEquals(expectedStatusCode, lastStatusCode);
    }

    @Then("I receive a {int} status code with a location header")
    public void iReceiveAStatusCodeWithALocationHeader(int arg0) {
    }

    public void processApiResponse(ApiResponse apiResponse) {
        lastApiResponse = apiResponse;
        lastApiCallThrewException = false;
        lastApiException = null;
        lastStatusCode = lastApiResponse.getStatusCode();
        List<String> locationHeaderValues = (List<String>)lastApiResponse.getHeaders().get("Location");
        lastReceivedLocationHeader = locationHeaderValues != null ? locationHeaderValues.get(0) : null;
    }

    public void processApiException(ApiException apiException) {
        lastApiCallThrewException = true;
        lastApiResponse = null;
        lastApiException = apiException;
        lastStatusCode = lastApiException.getCode();
    }

    public String getLastReceivedLocationHeader() {
        return lastReceivedLocationHeader;
    }
}

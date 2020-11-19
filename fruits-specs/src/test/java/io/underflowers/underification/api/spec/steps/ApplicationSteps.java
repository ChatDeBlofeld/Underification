package io.underflowers.underification.api.spec.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.underflowers.underification.ApiException;
import io.underflowers.underification.api.DefaultApi;
import io.underflowers.underification.api.dto.Application;
import io.underflowers.underification.api.dto.RegisteringApplication;
import io.underflowers.underification.api.spec.helpers.Environment;

import static org.junit.Assert.assertEquals;

public class ApplicationSteps {

    private Environment environment;
    private BasicSteps basicSteps;
    private DefaultApi api;

    RegisteringApplication application;

    private Application lastReceivedApplication;

    public ApplicationSteps(Environment environment, BasicSteps basicSteps) {
        this.environment = environment;
        this.basicSteps = basicSteps;

        this.api = environment.getApi();
    }

    @Given("I have an application payload")
    public void i_have_an_application_payload() throws Throwable {
        application = new RegisteringApplication()
                .name("My Application");
    }

    @When("^I POST the application payload to the /applications endpoint$")
    public void i_POST_the_application_payload_to_the_applications_endpoint() throws Throwable {
        try {
            basicSteps.processApiResponse(api.registerApplicationWithHttpInfo(application));
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @When("^I send a GET to the /applications endpoint$")
    public void iSendAGETToTheApplicationsEndpoint() {
        try {
            basicSteps.processApiResponse(api.getApplicationsWithHttpInfo());
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    /*@When("I send a GET to the URL in the location header")
    public void iSendAGETToTheURLInTheLocationHeader() {
        Integer id = Integer.parseInt(lastReceivedLocationHeader.substring(lastReceivedLocationHeader.lastIndexOf('/') + 1));
        try {
            lastApiResponse = api.getApplicationsWithHttpInfo(id);
            processApiResponse(lastApiResponse);
            lastReceivedApplication = (Application)lastApiResponse.getData();
        } catch (ApiException e) {
            processApiException(e);
        }
    }*/

    @And("I receive a payload that is the same as the application payload")
    public void iReceiveAPayloadThatIsTheSameAsTheApplicationPayload() {
        assertEquals(application, lastReceivedApplication);
    }
}

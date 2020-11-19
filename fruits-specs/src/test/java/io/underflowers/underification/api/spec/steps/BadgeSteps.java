package io.underflowers.underification.api.spec.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.underflowers.underification.ApiException;
import io.underflowers.underification.api.DefaultApi;
import io.underflowers.underification.api.dto.Application;
import io.underflowers.underification.api.dto.Badge;
import io.underflowers.underification.api.spec.helpers.Environment;

public class BadgeSteps {

    private Environment environment;
    private BasicSteps basicSteps;
    private DefaultApi api;

    Badge badge;

    private Application lastReceivedBadge;

    public BadgeSteps(Environment environment, BasicSteps basicSteps) {
        this.environment = environment;
        this.basicSteps = basicSteps;

        this.api = environment.getApi();
    }

    @Given("I have an badge payload")
    public void i_have_an_badge_payload() throws Throwable {
        badge = new Badge()
                .description("This is a badge")
                .image("image")
                .name("Badge 1");
    }

    @When("^I POST the badge payload to the /badges endpoint$")
    public void i_POST_the_badge_payload_to_the_badges_endpoint() throws Throwable {
        try {
            // TODO give token
            basicSteps.processApiResponse(api.createBadgeWithHttpInfo(badge));
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    @When("^I send a GET to the /badges endpoint$")
    public void iSendAGETToTheBadgesEndpoint() {
        try {
            basicSteps.processApiResponse(api.getBadgesWithHttpInfo());
        } catch (ApiException e) {
            basicSteps.processApiException(e);
        }
    }

    /*@When("I send a GET to the URL in the location header")
    public void iSendAGETToTheURLInTheLocationHeader() {
        Integer id = Integer.parseInt(lastReceivedLocationHeader.substring(lastReceivedLocationHeader.lastIndexOf('/') + 1));
        try {
            lastApiResponse = api.getBadgesWithHttpInfo(id);
            processApiResponse(lastApiResponse);
            lastReceivedBadge = (Badge)lastApiResponse.getData();
        } catch (ApiException e) {
            processApiException(e);
        }
    }*/

    /*@And("I receive a payload that is the same as the badge payload")
    public void iReceiveAPayloadThatIsTheSameAsTheBadgePayload() {
        assertEquals(badge, lastReceivedBadge);
    }*/
}

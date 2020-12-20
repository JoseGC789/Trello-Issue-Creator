package com.josegc789.trellobridge.features;

import com.josegc789.trellobridge.bridge.NewCard;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestOperations;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import static io.restassured.RestAssured.given;
import static java.lang.String.join;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreateCardSteps{

    @Autowired
    private RestOperations template;

    @LocalServerPort
    private int port;
    private ArgumentCaptor<String> uri;

    private Map<String, String> requestBody = new HashMap<>();

    @Given("I have a task of type issue")
    public void iHaveATaskOfTypeIssue() {
        requestBody.put("type", "issue");
    }

    @When("I create the Card")
    public void iCreateTheCard() {
        uri = ArgumentCaptor.forClass(String.class);
        when(template.postForObject(any(), any(), any())).thenReturn(new NewCard("NewCard1", "shortUri", "longUri"));
        Response response = given().port(port)
                .basePath("")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post();
        verify(template).postForObject(uri.capture(), any(), any());
    }

    @And("I provide the title and description {string}")
    public void iProvideTheTitleAndDescription(String arg0) {
        requestBody.put("description", arg0);
        requestBody.put("title", arg0);
    }

    @Then("Trello creates the card to the {string} list as unassigned")
    public void trelloCreatesTheCardToTheListAsUnassigned(String arg0) {
        assertTrue(uri.getValue().contains("idList=" + join("%20", arg0.split("\\s"))) && !uri.getValue().contains("idMembers"), uri.getValue());
    }

    @Given("I have a task of type bug")
    public void iHaveATaskOfTypeBug() {
        requestBody.put("type", "bug");
    }

    @And("I only provide the description {string}")
    public void iOnlyProvideTheDescription(String arg0) {
        requestBody.put("description", arg0);
    }

    @Then("Trello creates the card to the {string} list")
    public void trelloCreatesTheCardToTheList(String arg0) {
        assertTrue(uri.getValue().contains("idList=" + join("%20", arg0.split("\\s"))), uri.getValue());
    }

    @And("it's also labelled as {string}")
    public void itSAlsoLabelledAs(String arg0) {
        assertTrue(uri.getValue().contains("idLabels="+arg0), uri.getValue());
    }

    @And("a random member is assigned to it")
    public void aRandomMemberIsAssignedToIt() {
        assertTrue(Pattern.compile("idMembers=\\w+").matcher(uri.getValue()).find(), uri.getValue());
    }

    @And("the title is randomized with the pattern {string}")
    public void theTitleIsRandomizedWithThePattern(String arg0) {
        assertTrue(Pattern.compile("name="+arg0).matcher(uri.getValue()).find(), uri.getValue());
    }

    @Given("I have a {string} task")
    public void iHaveACategoryTask(String arg0) {
        requestBody.put("type", "task");
        requestBody.put("category", arg0);
    }

    @And("I provide the title {string}")
    public void iProvideTheTitle(String arg0) {
        requestBody.put("title", arg0);
    }

    @And("it is labelled with {string}")
    public void itIsLabelledWithLabel(String arg0) {
        assertTrue(uri.getValue().contains("idLabels="+arg0), uri.getValue());
    }
}
